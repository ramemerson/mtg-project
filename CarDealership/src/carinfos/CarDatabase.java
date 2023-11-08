package carinfos;

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

public class CarDatabase {

	private static String combustionCarFilePath = "src/CombustionCarsFile.csv";
	private static String electricCarFilePath = "src/ElectricCarsFile.csv";

	public static String getCarDatabaseFilePath() {
		return combustionCarFilePath;
	}

	public static void setCarDatabaseFilePath(String combustionCarFilePath) {
		CarDatabase.combustionCarFilePath = combustionCarFilePath;
	}

	public static String getElectricCarFilePath() {
		return electricCarFilePath;
	}

	public static void setElectricCarFilePath(String electricCarFilePath) {
		CarDatabase.electricCarFilePath = electricCarFilePath;
	}

	public boolean isCombustion(int number) {
		if (number == 1) {
			return true;
		} else {
			return false;
		}
	}

	// creates a new instance of the combustion car class and checks if that car is
	// already in
	// the file, if not it creates a new car
	public CombustionCar createNewCombustionCar(String carSerialID, String carBrand, String carModel, int horsePower,
			int maxCarSpeed, int tankMaxVolume, int averageGasUsage) {
		if (!carExistsElectric(carSerialID)) {
			CombustionCar newCombustionCar = new CombustionCar(carSerialID, carBrand, carModel, horsePower, maxCarSpeed,
					tankMaxVolume, averageGasUsage);
			addCombustionToDatabase(carSerialID, carBrand, carModel, horsePower, maxCarSpeed, tankMaxVolume, averageGasUsage);
			return newCombustionCar;
		} else {
			System.out.println("Car is already in the system!");
			return null;
		}
	}
	
	public ElectricCar createNewElectricCar(String carSerialID, String carBrand, String carModel, int horsePower,
			int maxCarSpeed, int batteryMax, int averageBatteryUsage) {
		if (!carExistsElectric(carSerialID)) {
			ElectricCar newElectricCar = new ElectricCar(carSerialID, carBrand, carModel, horsePower, maxCarSpeed,
					batteryMax, averageBatteryUsage);
			addElectricToDatabase(carSerialID, carBrand, carModel, horsePower, maxCarSpeed, batteryMax, averageBatteryUsage);
			return newElectricCar;
		} else {
			System.out.println("Car is already in the system!");
			return null;
		}
	}

	// adds a car to the file as a new line
	public void addCombustionToDatabase(String carSerialID, String carBrand, String carModel, int horsePower,
			int maxCarSpeed, int tankMaxVolume, int averageUsage) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(combustionCarFilePath, true))) {
			writer.write(carSerialID + "," + carBrand + "," + carModel + "," + horsePower + "," + maxCarSpeed + ","
					+ tankMaxVolume + "," + averageUsage);
			writer.newLine();
			System.out.println("Car added to database!");
		} catch (IOException e) {
			System.err.println("An error occurred while adding the car to the database: " + e.getMessage());
		}
	}
	
	public void addElectricToDatabase(String carSerialID, String carBrand, String carModel, int horsePower,
			int maxCarSpeed, int batteryMax, int averageBatteryUsage) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(electricCarFilePath, true))) {
			writer.write(carSerialID + "," + carBrand + "," + carModel + "," + horsePower + "," + maxCarSpeed + ","
					+ batteryMax + "," + averageBatteryUsage);
			writer.newLine();
			System.out.println("Car added to database!");
		} catch (IOException e) {
			System.err.println("An error occurred while adding the car to the database: " + e.getMessage());
		}
	}

	// searches for a car in the list based on the car ID and returns the car with
	// all the details
	public String findCombustionCarInDatabase(String carID) {
		String foundCarString = "";
		try (BufferedReader reader = new BufferedReader(new FileReader(combustionCarFilePath))) {
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

	public String findElectricCarInDatabase(String carID) {
		String foundCarString = "";
		try (BufferedReader reader = new BufferedReader(new FileReader(electricCarFilePath))) {
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
	public void printAllCombustionsFromFile() {
		try (BufferedReader reader = new BufferedReader(new FileReader(combustionCarFilePath))) {
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

	public void printAllElectricsFromFile() {
		try (BufferedReader reader = new BufferedReader(new FileReader(electricCarFilePath))) {
			String line;
			String fmt = "%1$10s %2$20s %3$20s %4$20s %5$20s %6$25s %7$25s";
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
	
	//see if a car is in the file already and if yes remove it 
	public void removeCombustionCar(String carID)  {
		List<String> lines = new ArrayList<>();
		boolean carFound = false;
		try (BufferedReader reader = new BufferedReader(new FileReader(combustionCarFilePath))) {
			String line;
			while ((line = reader.readLine()) != null) {
				if (line.startsWith(carID + ",") || line.startsWith(carID.toLowerCase() + ",")) {
					carFound = true;
				} else {
					lines.add(line);
				}
			}
		} catch (IOException e) {
			System.err.println("An error occurred while deleting the car: " + e.getMessage());
		}

		if (carFound) {
			try (BufferedWriter writer = new BufferedWriter(new FileWriter(combustionCarFilePath))) {
				for (String line : lines) {
					writer.write(line);
					writer.newLine();
				}
				System.out.println("CAR DELETED!");
			} catch (IOException e) {
				System.err.println("An error occurred while writing to the car: " + e.getMessage());
			}
		} else {
			System.out.println("Car not found in the file.");
		}
	}
	
	public void removeElectricCar(String carID)  {
		List<String> lines = new ArrayList<>();
		boolean carFound = false;
		try (BufferedReader reader = new BufferedReader(new FileReader(electricCarFilePath))) {
			String line;
			while ((line = reader.readLine()) != null) {
				if (line.startsWith(carID + ",") || line.startsWith(carID.toLowerCase() + ",")) {
					carFound = true;
				} else {
					lines.add(line);
				}
			}
		} catch (IOException e) {
			System.err.println("An error occurred while deleting the car: " + e.getMessage());
		}

		if (carFound) {
			try (BufferedWriter writer = new BufferedWriter(new FileWriter(electricCarFilePath))) {
				for (String line : lines) {
					writer.write(line);
					writer.newLine();
				}
				System.out.println("CAR DELETED!");
			} catch (IOException e) {
				System.err.println("An error occurred while writing to the car: " + e.getMessage());
			}
		} else {
			System.out.println("Car not found in the file.");
		}
	}

	// searches for a car based on its car ID and return true if found, and false if
	// not found
	public boolean carExistsCombustion(String carSerialID) {
		try {
			return Files.lines(Paths.get(combustionCarFilePath)).anyMatch(line -> line.startsWith(carSerialID + ","));
		} catch (IOException e) {
			System.err.println("An error occurred while checking if the car exists: " + e.getMessage());
			return false;
		}
	}

	public boolean carExistsElectric(String carSerialID) {
		try {
			return Files.lines(Paths.get(electricCarFilePath)).anyMatch(line -> line.startsWith(carSerialID + ","));
		} catch (IOException e) {
			System.err.println("An error occurred while checking if the car exists: " + e.getMessage());
			return false;
		}
	}

	// checks if the file is empty or not
	public boolean isFileEmptyCombustion() {
		File tasksFile = new File(combustionCarFilePath);

		if (tasksFile.length() == 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isFileEmptyElectric() {
		File tasksFile = new File(electricCarFilePath);

		if (tasksFile.length() == 0) {
			return true;
		} else {
			return false;
		}
	}
}
