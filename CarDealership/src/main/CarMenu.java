package main;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import carinfos.CarDatabase;

public class CarMenu {
	
	public static int waitTimeShort = 0;
	public static int waitTimeLong = 0;
	
	//the first welcome menu when you enter the console, with staggered welcome text
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
	
	//the first menu where you can select what you want to do/see
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
					CarDatabase.printCarsFromDatabase();
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
		
		
		
	}
	
	public static void seeDetailsOfACar() {
		
	}
	
	
	
	

}
