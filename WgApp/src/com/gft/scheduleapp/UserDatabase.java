package com.gft.scheduleapp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class UserDatabase {

	private String usersDatabaseFilePath = "C:\\Dev\\test_files\\usersDatabase.txt";

	public User createUser(String name, String password) {
		if (!userExists(name)) {
			User user = new User(name, password);
			writeUserToFile(name, password);
			return user;
		} else {
			System.out.println("User already exists.");
			return null;
		}
	}

	public boolean userExists(String name) {
		try {
			return Files.lines(Paths.get(usersDatabaseFilePath)).anyMatch(line -> line.startsWith(name + ","));
		} catch (IOException e) {
			System.err.println("An error occurred while checking if the user exists: " + e.getMessage());
			return false;
		}
	}

	public String findUser(String username) {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader("C:\\Dev\\test_files\\usersDatabase.txt"));
			String line;
			while ((line = reader.readLine()) != null) {
				if (line.contains(username)) {
					return line;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null; // return null if the user is not found
	}

	public void deleteUserFromFile(String name) {
		List<String> lines = new ArrayList<>();
		boolean userFound = false;
		try (BufferedReader reader = new BufferedReader(new FileReader(usersDatabaseFilePath))) {
			String line;
			while ((line = reader.readLine()) != null) {
				if (line.startsWith(name + ",")) {
					userFound = true;
				} else {
					lines.add(line);
				}
			}
		} catch (IOException e) {
			System.err.println("An error occurred while deleting the user: " + e.getMessage());
		}

		if (userFound) {
			try (BufferedWriter writer = new BufferedWriter(new FileWriter(usersDatabaseFilePath))) {
				for (String line : lines) {
					writer.write(line);
					writer.newLine();
				}
				System.out.println("USER DELETED!");
			} catch (IOException e) {
				System.err.println("An error occurred while writing to the file: " + e.getMessage());
			}
		} else {
			System.out.println("User not found in the file.");
		}
	}

	public void loadUsersFromFile(String filePath) {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(filePath));
			String line;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void writeUserToFile(String name, String password) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(usersDatabaseFilePath, true))) {
			writer.write(name + "," + password);
			writer.newLine();
			System.out.println("USER CREATED!");
		} catch (IOException e) {
			System.err.println("An error occurred while writing the user to the file: " + e.getMessage());
		}
	}

	public boolean isFileEmpty() {
		File usersFile = new File("C:\\Dev\\test_files\\usersDatabase.txt");

		if (usersFile.length() == 0) {
			return true;
		} else {
			return false;
		}

	}

}
