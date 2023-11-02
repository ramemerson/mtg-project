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

	private static String carDatabaseFilePath = "src/CarDealership.csv";

	public static String getCarDatabaseFilePath() {
		return carDatabaseFilePath;
	}

	public static void setCarDatabaseFilePath(String carDatabaseFilePath) {
		CarDatabase.carDatabaseFilePath = carDatabaseFilePath;
	}

	// creates a new instance of the car class and checks if that car is already in
	// the file, if not it creates a new car
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

	// adds a car to the file as a new line
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

	// searches for a car in the list based on the car ID and returns the car with
	// all the details
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

	// prints all cars which are in the file
	public void printAllCarsFromFile() {
		try (BufferedReader reader = new BufferedReader(new FileReader(carDatabaseFilePath))) {
			String line;
			String fmt = "%1$10s %2$20s %3$20s %4$20s %5$20s %6$20s %7$20s";
			while ((line = reader.readLine()) != null) {
				String[] valuesOfCarInLine = line.split(",");
				String output = String.format(fmt, valuesOfCarInLine[0], valuesOfCarInLine[1], valuesOfCarInLine[2],
						valuesOfCarInLine[3], valuesOfCarInLine[4], valuesOfCarInLine[5], valuesOfCarInLine[6]);
				System.out.println(output);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// searches for a car based on its car ID and return true if found, and false if
	// not found
	public boolean carExists(String carSerialID) {
		try {
			return Files.lines(Paths.get(carDatabaseFilePath)).anyMatch(line -> line.startsWith(carSerialID + ","));
		} catch (IOException e) {
			System.err.println("An error occurred while checking if the car exists: " + e.getMessage());
			return false;
		}
	}

	// checks if the file is empty or not
	public boolean isFileEmpty() {
		File tasksFile = new File(carDatabaseFilePath);

		if (tasksFile.length() == 0) {
			return true;
		} else {
			return false;
		}
	}
}
