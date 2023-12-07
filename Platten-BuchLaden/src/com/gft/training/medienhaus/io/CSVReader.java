package com.gft.training.medienhaus.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.gft.training.medienhaus.data.Mediums;
import com.gft.training.medienhaus.validate.CheckLineForItem;
import com.gft.training.medienhaus.validate.FileValidation;

public class CSVReader {

	public static List<String> returnListOfAllFilesInFolder() {
		List<String> temporarySave = new ArrayList<String>();
		List<String> allFileNames = new ArrayList<String>();
		File[] files = new File(FileValidation.getFile().toString()).listFiles();

		for (File file : files) {
			if (file.isFile()) {
				temporarySave.add(file.getName());
			}
		}

		for (String file : temporarySave) {
			String fileWithoutExt = file.substring(0, file.indexOf("."));
			allFileNames.add(fileWithoutExt);
		}
		return allFileNames;
	}

	public static ArrayList<Mediums> readFileAndCreateProductArrayList(String mediaType) throws IOException {

		ArrayList<Mediums> currentProductList = new ArrayList<Mediums>();
		BufferedReader reader = new BufferedReader(
				new FileReader(FileValidation.getFile().toString() + "\\" + mediaType + ".csv"));
		reader.readLine();
		String line = reader.readLine();

		while (line != null) {
			String[] vetor = line.split(",");
			String iD = vetor[0];
			String mediumType = vetor[1];
			String mediumTittle = vetor[2];
			String artist = vetor[3];
			double price = Double.parseDouble(vetor[4]);
			Mediums currentMedium = new Mediums(iD, mediumType, mediumTittle, artist, price);
			currentProductList.add(currentMedium);
			line = reader.readLine();
		}
		reader.close();
		return currentProductList;
	}

	public static String searchInFiles(String searchByIdOrNull, String searchByArtistOrNull, String searchByTitleOrNull)
			throws IOException {

		String itemToSearchBy = null;
		int indexOfColumnToSearch = 0;

		if (searchByIdOrNull != null && searchByArtistOrNull == null && searchByTitleOrNull == null) {
			itemToSearchBy = searchByIdOrNull;
			indexOfColumnToSearch = 0;
		}
		if (searchByIdOrNull == null && searchByArtistOrNull != null && searchByTitleOrNull == null) {
			itemToSearchBy = searchByArtistOrNull;
			indexOfColumnToSearch = 3;
		}
		if (searchByIdOrNull == null && searchByArtistOrNull == null && searchByTitleOrNull != null) {
			itemToSearchBy = searchByTitleOrNull;
			indexOfColumnToSearch = 2;
		}

		boolean itemFound = false;
		for (File files : FileValidation.getFile().listFiles()) {
			if (files.isDirectory()) {
				return files.toString();
			} else {
				ArrayList<String> lines = new ArrayList<String>();
				try (BufferedReader br = new BufferedReader(new FileReader(files))) {
					String foundLine;
					while ((foundLine = br.readLine()) != null) {
						if (CheckLineForItem.returnTrueIfItemIsFoundInLine(foundLine, itemToSearchBy,
								indexOfColumnToSearch)) {
							lines.add(foundLine);
							itemFound = true;
						}
					}
				}
				if (!lines.isEmpty()) {
					System.out.println(files.getName().replace(".csv", "") + ":");
					for (String line : lines) {
						System.out.println("  " + line);
					}
				}
			}
		}
		if (!itemFound) {
			System.err.println("Item not found!");
		}
		return null;
	}

	public static String printAllContentFromMedia(String mediaType) throws IOException {
		for (File file : FileValidation.getFile().listFiles()) {
			String fileName = file.getName().replace(".csv", "");
			if (fileName.toLowerCase().equals(mediaType.toLowerCase())) {
				try (BufferedReader br = new BufferedReader(new FileReader(file))) {
					String line;
					String fmt = "%1$10s %2$20s %3$20s %4$20s %5$20s";
					while ((line = br.readLine()) != null) {
						String[] valuesOfCarInLine = line.split(",");
						String output = String.format(fmt, valuesOfCarInLine[0], valuesOfCarInLine[1],
								valuesOfCarInLine[2], valuesOfCarInLine[3], valuesOfCarInLine[4]);
						System.out.println(output);
					}
				}
			}
		}
		return mediaType;
	}

}
