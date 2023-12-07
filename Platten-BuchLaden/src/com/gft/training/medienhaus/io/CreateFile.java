package com.gft.training.medienhaus.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.gft.training.medienhaus.validate.FileValidation;

public class CreateFile {
	
	public static void createNewMediaTypeFile(String typeOfMedium) {

		File fileFolder = FileValidation.getFile();
		
		File fileToSearch = new File( fileFolder.getPath() + "\\" + typeOfMedium + ".csv");

		if (!fileToSearch.exists()) {
			try {
				fileToSearch.createNewFile();
				try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileToSearch, true))) {
					writer.write("Media ID, Type Of Media, Title, Artist, Price");
					writer.newLine();
					System.out.println("Media File added!");
				} catch (Exception e) {
					System.err.println("An error occurred while adding the item to the file: " + e.getMessage());
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			System.err.println("File already exists.");
		}
	}

}
