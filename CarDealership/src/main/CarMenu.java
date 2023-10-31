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
			System.out.println("\n[1] Add car to the database\n[2] See details of a car\n[3] See all cars in database");
			Scanner scanner = new Scanner(System.in);
			int userSelection = scanner.nextInt();
			boolean trueCheck = true;
			while (trueCheck == true) {
				if (userSelection == 1) {
					CarMenu.addCarToDatabase();
				} else if (userSelection == 2) {
					CarMenu.seeDetailsOfACar();
				} else if (userSelection == 3) {
					CarMenu.seeAllCars();
					trueCheck = false;
					firstSelectionMenu();
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

	public static void addCarToDatabase() {
		try {
			Scanner scanner = new Scanner(System.in);
			boolean trueCheck = true;
			while (trueCheck == true) {
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
						+ ", HP: " + scannerHP + ", Max Speed: " + scannerMaxSpeed + ", Tank Size: " + scannerTankAmount
						+ ", Average Usage: " + scannerAverageUsage);
				System.out.println("\nIs the entered information correct?");
				System.out.println("[1] yes [2] no");
				int scannerCheck1Or2 = scanner.nextInt();
				if (scannerCheck1Or2 == 1) {
					carDatabase.createNewCar(scannerID, scannerBrand, scannerModel, scannerHP, scannerMaxSpeed,
							scannerTankAmount, scannerAverageUsage);
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
			scanner.close();
			trueCheck = false;
		} catch (InputMismatchException e) {
			System.out.println("wrong input, please input a number!");
			CarMenu.firstSelectionMenu();
		}

	}

	public static void seeDetailsOfACar() {
		try (Scanner scanner = new Scanner(System.in)) {
			boolean trueCheck = true;
			while (trueCheck == true) {
				System.out.println("Enter the car ID of the car details you want to see:");
				String scannerID = scanner.next();
				if (scanner != null) {
					carDatabase.findCar(scannerID);
				}		
			
			}
			trueCheck = false;
		} catch (InputMismatchException e) {
			System.out.println("wrong input, please input a number!");
		}

	}

	public static void seeAllCars() {
		carDatabase.printAllCarsFromDatabase();
	}

}
