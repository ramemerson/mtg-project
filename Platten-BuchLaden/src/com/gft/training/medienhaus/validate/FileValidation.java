package com.gft.training.medienhaus.validate;

import java.io.File;

public class FileValidation {
	
	private static final File FILE = new File("C:\\Dev\\test_files\\Plattenhaus");

	public static File checkFilePath(File filePath) {
		if (filePath.equals(FILE)) {
			return FILE;
		} else {
			return null;
		}
	}

	public static File getFile() {
		return FILE;
	}
	
}
