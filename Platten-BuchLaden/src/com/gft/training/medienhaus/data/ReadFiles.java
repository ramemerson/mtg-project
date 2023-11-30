package com.gft.training.medienhaus.data;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.gft.training.medienhaus.validate.FileValidation;

public class ReadFiles {

	private static File filePath = new File("C:\\Dev\\test_files\\Plattenhaus");
	
	public static boolean itemIsFoundInLine (String line, String itemToSearchBy, int columnToSearchBy) throws IOException {
		
		String[] splitLine = line.split(",");
		
		if (line != null) {
			if (line.toLowerCase().contains(itemToSearchBy.toLowerCase())) {
				if (splitLine[columnToSearchBy].toLowerCase().equals(itemToSearchBy.toLowerCase())) {
					return true;
				}
			}
		}
		return false;
	}
	
	public static void fileReader(String mediaFileToRead) throws IOException {

		String id;
		String typeOfMedium;
		String title;
		String artist;
		double price;

		try (BufferedReader br = new BufferedReader(new FileReader(FileValidation.getFile()))) {
			for (File files : FileValidation.getFile().listFiles()) {
				if (mediaFileToRead.equalsIgnoreCase(files.getName())) {
					String foundLine;
					while ((foundLine = br.readLine()) != null) {
						String[] splitFoundLine = foundLine.split(",");
						String mediaId;

					}
				}
			}
		}
	}
}
