package com.gft.newmagicplatform.io;

import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.gft.newmagicplatform.pojo.Card;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import lombok.Getter;

@Getter
public class JsonDataLoader {

    private List<Card> cardList;
    private Map<String, Card> idIndex;
    private Map<String, List<Card>> nameIndex;

   
    public JsonDataLoader(String filePath) throws IOException {
        loadJsonData(filePath);
        buildIndexes();
    }

    private void loadJsonData(String filePath) throws IOException {
        Gson gson = new Gson();
        JsonReader reader = new JsonReader(new FileReader(filePath));
        cardList = gson.fromJson(reader, new TypeToken<List<Card>>() {
        }.getType());
        reader.close();
    }

    private void buildIndexes() {
        idIndex = new HashMap<>();
        nameIndex = new HashMap<>();

        for (Card card : cardList) {
            idIndex.put(card.getId(), card);

            List<Card> nameList = nameIndex.getOrDefault(card.getName(), new ArrayList<>());
            nameList.add(card);
            nameIndex.put(card.getName(), nameList);
        }
    }

    public Card findCardByIdParrallel(String id) {
        int numThreads = Runtime.getRuntime().availableProcessors();
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);

        try {
            List<Callable<Card>> tasks = new ArrayList<>();
            int chunkSize = Math.max(1, cardList.size() / numThreads);
            for (int i = 0; i < cardList.size(); i += chunkSize) {
                int endIndex = Math.min(i + chunkSize, cardList.size());
                List<Card> chunk = cardList.subList(i, endIndex);
                tasks.add(() -> chunk.stream()
                        .filter(card -> card.getId().equals(id))
                        .findFirst().orElse(null));
            }

            List<Future<Card>> futures = executor.invokeAll(tasks);

            for (Future<Card> future : futures) {
                Card card = future.get();
                if (card != null) {
                    return card;
                }
            }
            return null;
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return null;
        } finally {
            executor.shutdown();
        }
    }

    public List<Card> findCardsByNameParralel(String name) {
        Pattern pattern = Pattern.compile(Pattern.quote(name), Pattern.CASE_INSENSITIVE);
        int numThreads = Runtime.getRuntime().availableProcessors();
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);
        
        try {
            List<Callable<List<Card>>> tasks = new ArrayList<>();
            int chunkSize = Math.max(1, cardList.size() / numThreads);
            for (int i = 0; i < cardList.size(); i += chunkSize) {
                int endIndex = Math.min(i + chunkSize, cardList.size());
                List<Card> chunk = cardList.subList(i, endIndex);
                tasks.add(() -> chunk.stream()
                        .filter(card -> pattern.matcher(card.getName()).find())
                        .collect(Collectors.toList()));
            }

            List<Future<List<Card>>> futures = executor.invokeAll(tasks);

            List<Card> matchingCards = new ArrayList<>();
            List<Card> exactMatches = new ArrayList<>();
            for (Future<List<Card>> future : futures) {
                List<Card> result = future.get();
                for (Card card : result) {
                    if (card.getName().equalsIgnoreCase(name)) {
                        exactMatches.add(card);
                    } else {
                        matchingCards.add(card);
                    }
                }
            }
            matchingCards.sort(Comparator.comparingInt(card -> levenshteinDistance(name, card.getName())));
            exactMatches.addAll(matchingCards);
            return exactMatches;
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return Collections.emptyList();
        } finally {
            executor.shutdown();
        }
    }

    private int levenshteinDistance(String s1, String s2) {
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];
        for (int i = 0; i <= s1.length(); i++) {
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0) {
                    dp[i][j] = j;
                } else if (j == 0) {
                    dp[i][j] = i;
                } else {
                    dp[i][j] = s1.charAt(i - 1) == s2.charAt(j - 1) ? dp[i - 1][j - 1]
                            : 1 + Math.min(dp[i - 1][j - 1], Math.min(dp[i][j - 1], dp[i - 1][j]));
                }
            }
        }
        return dp[s1.length()][s2.length()];
    }

}
