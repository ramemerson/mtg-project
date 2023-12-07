package com.gft.training.medienhaus.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;

import com.gft.training.medienhaus.data.Mediums;
import com.gft.training.medienhaus.validate.FileValidation;

public class CSVWriter {
	
	public static Mediums createMediaItems(String IdNumber, String typeOfMedium, String titleOfMedium, String artist,
			int price) {
		List<String> allFileNames = CSVReader.returnListOfAllFilesInFolder();

		if (!allFileNames.contains(typeOfMedium)) {
			CreateFile.createNewMediaTypeFile(typeOfMedium);
			Mediums newMedium = new Mediums(IdNumber, typeOfMedium, titleOfMedium, artist, price);
			File foundFile = new File(FileValidation.getFile().getPath() + "\\" + typeOfMedium + ".csv");
			try (BufferedWriter writer = new BufferedWriter(new FileWriter(foundFile, true))) {
				writer.write(IdNumber + "," + typeOfMedium + "," + titleOfMedium + "," + artist + "," + price);
				writer.newLine();
				System.out.println("Item added!");
				return newMedium;
			} catch (Exception e) {
				System.err.println("An error occurred while adding the item to the file: " + e.getMessage());
			}
		} else {
			Mediums newMedium = new Mediums(IdNumber, typeOfMedium, titleOfMedium, artist, price);
			File foundFile = new File(FileValidation.getFile().getPath() + "\\" + typeOfMedium + ".csv");
			try (BufferedWriter writer = new BufferedWriter(new FileWriter(foundFile, true))) {
				writer.write(IdNumber + "," + typeOfMedium + "," + titleOfMedium + "," + artist + "," + price);
				writer.newLine();
				System.out.println("Item added!");
				return newMedium;
			} catch (Exception e) {
				System.err.println("An error occurred while adding the item to the file: " + e.getMessage());
			}
		}
		return null;
	}

}
