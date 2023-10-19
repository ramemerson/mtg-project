package com.gft.scheduleapp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

public class TaskDatabase {

	private String tasksDatabaseFilePath = "C:\\Dev\\test_files\\TaskDatabase.txt";
	private UserDatabase userDatabase = new UserDatabase();

	public Task createTask(String taskTodo, String whoDoesTask, String detailsOfTask, DayOfWeek dayOfWeek) {

		if (userDatabase.userExists(whoDoesTask) && !taskExists(taskTodo)) {
			Task task = new Task(taskTodo, whoDoesTask, detailsOfTask, dayOfWeek);
			writeTaskToFile(taskTodo, whoDoesTask, detailsOfTask, dayOfWeek);
			return task;
		} else {
			System.out.println("The user you want to assign that task doesnt exist or the Task name already exists, try again.");
			return null;
		}
	}

	public boolean taskExists(String task) {
		try {
			return Files.lines(Paths.get(tasksDatabaseFilePath)).anyMatch(line -> line.startsWith(task + ","));
		} catch (IOException e) {
			System.err.println("An error occurred while checking if the task exists: " + e.getMessage());
			return false;
		}
	}

	public void writeTaskToFile(String taskTodo, String whoDoesTask, String detailsOfTask, DayOfWeek dayOfWeek) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(tasksDatabaseFilePath, true))) {
			writer.write(taskTodo + "," + whoDoesTask + "," + detailsOfTask + "," + dayOfWeek);
			writer.newLine();
			System.out.println("TASK CREATED!");
		} catch (IOException e) {
			System.err.println("An error occurred while writing the task to the file: " + e.getMessage());
		}
	}

	public String findTask(String task) {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader("C:\\Dev\\test_files\\TaskDatabase.txt"));
			String line;
			while ((line = reader.readLine()) != null) {
				if (line.contains(task)) {
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

	public void deleteTaskFromFile(String task) {
		List<String> lines = new ArrayList<>();
		boolean userFound = false;
		try (BufferedReader reader = new BufferedReader(new FileReader(tasksDatabaseFilePath))) {
			String line;
			while ((line = reader.readLine()) != null) {
				if (line.startsWith(task + ",")) {
					userFound = true;
				} else {
					lines.add(line);
				}
			}
		} catch (IOException e) {
			System.err.println("An error occurred while deleting the task: " + e.getMessage());
		}

		if (userFound) {
			try (BufferedWriter writer = new BufferedWriter(new FileWriter(tasksDatabaseFilePath))) {
				for (String line : lines) {
					writer.write(line);
					writer.newLine();
				}
				System.out.println("TASK DELETED!");
			} catch (IOException e) {
				System.err.println("An error occurred while writing to the task: " + e.getMessage());
			}
		} else {
			System.out.println("Task not found in the file.");
		}
	}

	public void loadTaskFromFile(String filePath) {
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
	
	public List<Task> getTasksByUser(String username) {
	    List<Task> tasks = new ArrayList<>();
	    try (BufferedReader reader = new BufferedReader(new FileReader(tasksDatabaseFilePath))) {
	        String line;
	        while ((line = reader.readLine()) != null) {
	            String[] taskDetails = line.split(",");
	            if (taskDetails[1].equals(username)) {
	                Task task = new Task(taskDetails[0], taskDetails[1], taskDetails[2], DayOfWeek.valueOf(taskDetails[3]));
	                tasks.add(task);
	            }
	        }
	    } catch (IOException e) {
	        System.err.println("An error occurred while getting tasks by user: " + e.getMessage());
	    }
	    return tasks;
	}

	public boolean isFileEmpty() {
		File tasksFile = new File("C:\\Dev\\test_files\\TaskDatabase.txt");

		if (tasksFile.length() == 0) {
			return true;
		} else {
			return false;
		}

	}

}
