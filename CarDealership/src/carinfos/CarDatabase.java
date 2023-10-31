package carinfos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CarDatabase {

	private static String carDatabaseFilePath = "src/CarDealership.txt";

	public Car createNewCar(String carSerialID, String carBrand, String carModel, int horsePower, int maxCarSpeed,
			int tankMaxVolume, int averageUsage) {
		if (!carExists(carSerialID)) {
			Car newCar = new Car(carSerialID, carBrand, carModel, horsePower, maxCarSpeed, tankMaxVolume, averageUsage);
			addCarsToDatabase(carSerialID, carBrand, carModel, horsePower, maxCarSpeed, tankMaxVolume, averageUsage);
			return newCar;
		} else {
			System.out.println("Car is already in the system!");
			return null;
		}
	}

	public void addCarsToDatabase(String carSerialID, String carBrand, String carModel, int horsePower, int maxCarSpeed,
			int tankMaxVolume, int averageUsage) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(carDatabaseFilePath, true))) {
			writer.write(carSerialID + "," + carBrand + "," + carModel + "," + horsePower + "," + maxCarSpeed + ","
					+ tankMaxVolume + "," + averageUsage);
			writer.newLine();
			System.out.println("Car added to database!");
		} catch (IOException e) {
			System.err.println("An error occurred while adding the car to the database: " + e.getMessage());
		}
	}

	public String findCarInDatabase(String carID) {
		String foundCarString = "";
		try (BufferedReader reader = new BufferedReader(new FileReader(carDatabaseFilePath))) {		
			for (String line; (line = reader.readLine()) != null;) {
				if (line.toLowerCase().contains(carID) || line.contains(carID)) {
					foundCarString = line;
				} 
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return foundCarString;
	}

	public void printAllCarsFromDatabase() {
		try (BufferedReader reader = new BufferedReader(new FileReader(carDatabaseFilePath))) {
			String line;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean carExists(String carSerialID) {
		try {
			return Files.lines(Paths.get(carDatabaseFilePath)).anyMatch(line -> line.startsWith(carSerialID + ","));
		} catch (IOException e) {
			System.err.println("An error occurred while checking if the car exists: " + e.getMessage());
			return false;
		}
	}

	public boolean isFileEmpty() {
		File tasksFile = new File(carDatabaseFilePath);

		if (tasksFile.length() == 0) {
			return true;
		} else {
			return false;
		}
	}
}
