package com.gft.training.medienhaus.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.gft.training.medienhaus.validate.FileValidation;

public class PrintData {

	public static String printDataFromFile(String searchByIdOrNull, String searchByArtistOrNull,
			String searchByTitleOrNull) throws IOException {
		
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
						if (ReadFiles.itemIsFoundInLine(foundLine, itemToSearchBy, indexOfColumnToSearch)) {
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
			System.out.println("Item not found!");
		}
		return null;
	}
}
