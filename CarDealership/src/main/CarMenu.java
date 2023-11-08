package main;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import carinfos.CarDatabase;

public class CarMenu {

	public static int waitTimeShort = 0;
	public static int waitTimeLong = 0;

	private static CarDatabase carDatabase = new CarDatabase();

	// the first welcome menu when you enter the console, with staggered welcome
	// text
	public static void dealershipWelcomeText() {
		System.out.print("welcome to");
		try {
			TimeUnit.MILLISECONDS.sleep(waitTimeShort);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.print(".");
		try {
			TimeUnit.MILLISECONDS.sleep(waitTimeShort);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.print(".");
		try {
			TimeUnit.MILLISECONDS.sleep(waitTimeShort);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(".");
		try {
			TimeUnit.SECONDS.sleep(waitTimeLong);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.print("you are entering");
		try {
			TimeUnit.MILLISECONDS.sleep(waitTimeShort);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.print(".");
		try {
			TimeUnit.MILLISECONDS.sleep(waitTimeShort);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.print(".");
		try {
			TimeUnit.MILLISECONDS.sleep(waitTimeShort);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(".");
		try {
			TimeUnit.SECONDS.sleep(waitTimeLong);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(" -- THE RUSTY CAR DEALERSHIP --");
	}

	// the first menu where you can select what you want to do/see
	public static void firstSelectionMenu() throws InputMismatchException {
		try {
			System.out.println(
					"\n[1] Add car to the database\n[2] See details of a car\n[3] See all cars in database\n[4] Remove cars");
			Scanner scanner = new Scanner(System.in);
			int userSelection = scanner.nextInt();
			boolean trueCheck = true;
			while (trueCheck == true) {
				if (userSelection == 1) {
					CarMenu.addCarToDatabase();
				} else if (userSelection == 2) {
					System.out.println("\nSee details of [1] Gas [2] Electric car");
					int selection = scanner.nextInt();
					if (selection == 1) {
						CarMenu.seeDetailsOfCombustionCar();
					} else if (selection == 2) {
						CarMenu.seeDetailsOfElectricCar();
					}
				} else if (userSelection == 3) {
					CarMenu.combustionOrElectricMenu();
					trueCheck = false;
					firstSelectionMenu();
				} else if (userSelection == 4) {
					System.out.println("\nRemove [1] Gas [2] Electric car");
					int selection = scanner.nextInt();
					if (selection == 1) {
						CarMenu.removeCombustioncar();
					} else if (selection == 2) {
						CarMenu.removeElectricCar();
					}
				} else {
					System.out.println("Please input a number from the menu above!");
					trueCheck = false;
					firstSelectionMenu();
				}
			}
			scanner.close();
		} catch (InputMismatchException e) {
			System.out.println("wrong input, please input a number!");
			firstSelectionMenu();
		}

	}

	// see all electric cars or combustion cars
	public static void combustionOrElectricMenu() throws InputMismatchException {
		try {
			System.out.println("[1] Gas\n[2] Electric");
			Scanner scanner1Or2 = new Scanner(System.in);
			int selection = scanner1Or2.nextInt();
			boolean trueCheck = true;
			while (trueCheck == true) {
				if (selection == 1) {
					carDatabase.printAllCombustionsFromFile();
					firstSelectionMenu();
				} else if (selection == 2) {
					carDatabase.printAllElectricsFromFile();
					firstSelectionMenu();
				} else {
					System.out.println("Please input a number from the menu above");
					trueCheck = false;
					combustionOrElectricMenu();
				}
			}
			scanner1Or2.close();
		} catch (InputMismatchException e) {
			System.out.println("wrong input, please input a number!");
			combustionOrElectricMenu();
		}
	}

	// menu prompt to let you add cars to the car list
	public static void addCarToDatabase() {
		try {
			Scanner scanner = new Scanner(System.in);
			boolean trueCheck = true;
			while (trueCheck == true) {
				System.out.println("\nIs the car you are adding Gas or Electric? [1] Gas [2] Electric");
				int yesOrNo = scanner.nextInt();
				if (yesOrNo == 1) {
					System.out.println("\nAdd new car to the database - \nEnter car ID(unique ID for each car): ");
					String scannerID = scanner.next();
					System.out.println("\nCar brand:");
					String scannerBrand = scanner.next();
					System.out.println("\nCar Model:");
					String scannerModel = scanner.next();
					System.out.println("\nHorse Power of car:");
					int scannerHP = scanner.nextInt();
					System.out.println("\nMax speed of car:");
					int scannerMaxSpeed = scanner.nextInt();
					System.out.println("\nHow much gas can the car tank hold:");
					int scannerTankAmount = scanner.nextInt();
					System.out.println("\nWhat is the average gas usage per 100km:");
					int scannerAverageUsage = scanner.nextInt();
					System.out.println("ID: " + scannerID + ", Brand: " + scannerBrand + ", Model: " + scannerModel
							+ ", HP: " + scannerHP + ", Max Speed: " + scannerMaxSpeed + ", Tank Size: "
							+ scannerTankAmount + ", Average Usage: " + scannerAverageUsage);
					System.out.println("\nIs the entered information correct?");
					System.out.println("[1] yes [2] no");
					int scannerCheck1Or2 = scanner.nextInt();
					if (scannerCheck1Or2 == 1) {
						carDatabase.createNewCombustionCar(scannerID, scannerBrand, scannerModel, scannerMaxSpeed,
								scannerTankAmount, scannerAverageUsage, scannerAverageUsage);
						CarMenu.firstSelectionMenu();
					} else {
						System.out.println("\n[1] Input information again [2] Go back to main menu ");
						int userSelection = scanner.nextInt();
						if (userSelection == 1) {
							CarMenu.addCarToDatabase();
						} else {
							CarMenu.firstSelectionMenu();
						}
					}
				} else if (yesOrNo == 2) {
					System.out.println("\nAdd new car to the database - \nEnter car ID(unique ID for each car): ");
					String scannerID = scanner.next();
					System.out.println("\nCar brand:");
					String scannerBrand = scanner.next();
					System.out.println("\nCar Model:");
					String scannerModel = scanner.next();
					System.out.println("\nHorse Power of car:");
					int scannerHP = scanner.nextInt();
					System.out.println("\nMax speed of car:");
					int scannerMaxSpeed = scanner.nextInt();
					System.out.println("\nWhats the battery capacity(kw/h):");
					int scannerBatteryCapacity = scanner.nextInt();
					System.out.println("\nWhat is the average battery usage(kw/h) per 100km:");
					int scannerBatteryUsage = scanner.nextInt();
					System.out.println("ID: " + scannerID + ", Brand: " + scannerBrand + ", Model: " + scannerModel
							+ ", HP: " + scannerHP + ", Max Speed: " + scannerMaxSpeed + ", Battery Capacity: "
							+ scannerBatteryCapacity + ", Average Usage: " + scannerBatteryUsage);
					System.out.println("\nIs the entered information correct?");
					System.out.println("[1] yes [2] no");
					int scannerCheck1Or2 = scanner.nextInt();
					if (scannerCheck1Or2 == 1) {
						carDatabase.createNewElectricCar(scannerID, scannerBrand, scannerModel, scannerHP,
								scannerMaxSpeed, scannerBatteryCapacity, scannerBatteryUsage);
						CarMenu.firstSelectionMenu();
					} else {
						System.out.println("\n[1] Input information again [2] Go back to main menu ");
						int userSelection = scanner.nextInt();
						if (userSelection == 1) {
							CarMenu.addCarToDatabase();
						} else {
							CarMenu.firstSelectionMenu();
						}
					}
				}
			}
			scanner.close();
			trueCheck = false;
		} catch (InputMismatchException e) {
			System.out.println("wrong input, please input a number!");
			CarMenu.firstSelectionMenu();
		}
	}

	// search for a cared based on its ID
	public static void seeDetailsOfElectricCar() {
		try (Scanner scanner = new Scanner(System.in)) {
			boolean trueCheck = true;
			while (trueCheck == true) {
				System.out.println("Enter the car ID of the car details you want to see:");
				String scannerID = scanner.next();
				String line;
				String fmt = "%1$10s %2$20s %3$20s %4$20s %5$20s %6$20s %7$20s";
				if (carDatabase.findElectricCarInDatabase(scannerID).length() != 0) {
					line = carDatabase.findElectricCarInDatabase(scannerID);
					String[] valuesOfCarInLine = line.split(",");
					String[] header = "CarID,Car Brand,Car Model,Horse Power,Max Speed,Tank Max Capacity,Usage l/100km"
							.split(",");
					String outputHeader = String.format(fmt, header[0], header[1], header[2], header[3], header[4],
							header[5], header[6]);
					String output = String.format(fmt, valuesOfCarInLine[0], valuesOfCarInLine[1], valuesOfCarInLine[2],
							valuesOfCarInLine[3], valuesOfCarInLine[4], valuesOfCarInLine[5], valuesOfCarInLine[6]);
					System.out.println(outputHeader);
					System.out.println(output);
					firstSelectionMenu();
				} else if (carDatabase.findElectricCarInDatabase(scannerID).length() == 0) {
					System.out.println("car not found!\n[1] Search again\n[2] See list of all cars");
					int scanNextInt = scanner.nextInt();
					if (scanNextInt == 1) {
						seeDetailsOfElectricCar();
					} else if (scanNextInt == 2) {
						combustionOrElectricMenu();
						firstSelectionMenu();
					}
				}
			}
			trueCheck = false;
		} catch (InputMismatchException e) {
			System.out.println("wrong input, please input a car ID!");
			seeDetailsOfElectricCar();
		}
	}

	public static void seeDetailsOfCombustionCar() {
		try (Scanner scanner = new Scanner(System.in)) {
			boolean trueCheck = true;
			while (trueCheck == true) {
				System.out.println("Enter the car ID of the car details you want to see:");
				String scannerID = scanner.next();
				String line;
				String fmt = "%1$10s %2$20s %3$20s %4$20s %5$20s %6$20s %7$20s";
				if (carDatabase.findCombustionCarInDatabase(scannerID).length() != 0) {
					line = carDatabase.findCombustionCarInDatabase(scannerID);
					String[] valuesOfCarInLine = line.split(",");
					String[] header = "CarID,Car Brand,Car Model,Horse Power,Max Speed,Tank Max Capacity,Usage l/100km"
							.split(",");
					String outputHeader = String.format(fmt, header[0], header[1], header[2], header[3], header[4],
							header[5], header[6]);
					String output = String.format(fmt, valuesOfCarInLine[0], valuesOfCarInLine[1], valuesOfCarInLine[2],
							valuesOfCarInLine[3], valuesOfCarInLine[4], valuesOfCarInLine[5], valuesOfCarInLine[6]);
					System.out.println(outputHeader);
					System.out.println(output);
					firstSelectionMenu();
				} else if (carDatabase.findCombustionCarInDatabase(scannerID).length() == 0) {
					System.out.println("car not found!\n[1] Search again\n[2] See list of all cars");
					int scanNextInt = scanner.nextInt();
					if (scanNextInt == 1) {
						seeDetailsOfCombustionCar();
					} else if (scanNextInt == 2) {
						combustionOrElectricMenu();
						firstSelectionMenu();
					}
				}
			}
			trueCheck = false;
		} catch (InputMismatchException e) {
			System.out.println("wrong input, please input a car ID!");
			seeDetailsOfCombustionCar();
		}
	}

	public static void removeCombustioncar() {
		try (Scanner scanner = new Scanner(System.in)){
			boolean trueCheck = true;
			while (trueCheck == true) {
				System.out.println("Enter the car ID of the car you want to remove:");
				String scannerID = scanner.next();
				carDatabase.removeCombustionCar(scannerID);
				firstSelectionMenu();
			}
			trueCheck = false;
		} catch (InputMismatchException e) {
			System.out.println("wrong input, please input a car ID!");
			removeCombustioncar();
		}
	}

	public static void removeElectricCar() {
		try (Scanner scanner = new Scanner(System.in)){
			boolean trueCheck = true;
			while (trueCheck == true) {
				System.out.println("Enter the car ID of the car you want to remove:");
				String scannerID = scanner.next();
				carDatabase.removeElectricCar(scannerID);
				firstSelectionMenu();
			}
			trueCheck = false;
		} catch (InputMismatchException e) {
			System.out.println("wrong input, please input a car ID!");
			removeElectricCar();
		}
	}
}
