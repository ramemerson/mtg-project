package com.gft.scheduleapp;

import java.time.DayOfWeek;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuItems {

	private static UserDatabase userDatabase = new UserDatabase();
	private static TaskDatabase taskDatabase = new TaskDatabase();

	public static void welcomeText() {
		System.out.println("Welcome to the WG cleaning app!");
	}

	public static void welcomeMenu() throws InputMismatchException { // the main function of the program starts here,
																		// every the menu selection
																		// process is complete or
		try {
			System.out.println("\n1. Users\n2. Tasks\n3. Schedule");
			Scanner scanner = new Scanner(System.in);
			int userSelection = scanner.nextInt();
			boolean trueCheck = true;
			while (trueCheck == true) {
				if (userSelection == 1) {
					MenuItems.usersMenu();
				} else if (userSelection == 2) {
					MenuItems.tasksMenu();
				} else if (userSelection == 3) {
					MenuItems.scheduleMenu();
				} else {
					System.out.println("Please input a number from the menu above!");
					trueCheck = false;
					welcomeMenu();
				}
			}
			scanner.close();
		} catch (InputMismatchException e) {
			System.out.println("wrong input, please input a number!");
			welcomeMenu();
		}
	}

	public static void usersMenu() throws InputMismatchException {
		try {
			System.out.println("\nUSERS - \n1. Create User\n2. Delete User\n3. See All Users\n4. Main Menu");
			Scanner scanner = new Scanner(System.in);
			boolean trueCheck = true;
			while (trueCheck == true) {
				int userSelection = scanner.nextInt();
				if (userSelection == 1) {
					MenuItems.usersMenuCreateUser();
				} else if (userSelection == 2) {
					MenuItems.usersMenuDeleteUser();
				} else if (userSelection == 3) {
					MenuItems.usersMenuPrintUsers();
				} else if (userSelection == 4) {
					MenuItems.welcomeMenu();
				} else {
					System.out.println("Please input a number from the menu above!");
					trueCheck = false;
					MenuItems.usersMenu();
				}
			}
			scanner.close();
		} catch (InputMismatchException e) {
			System.out.println("wrong input, please input a number!");
			MenuItems.usersMenu();
		}
	}

	public static void usersMenuCreateUser() throws InputMismatchException {
		try {
			Scanner scanner = new Scanner(System.in);
			boolean trueCheck = true;
			while (trueCheck == true) {
				System.out.println("\nCreate User - \nEnter: your name..");
				String scannerName = scanner.next();
				System.out.println("\npassword..");
				String scannerPassword = scanner.next();
				userDatabase.createUser(scannerName, scannerPassword);
				System.out.println("Would you like to go back to:\n1. Users\n2. Main Menu");
				int userSelection = scanner.nextInt();
				if (userSelection == 1) {
					MenuItems.usersMenu();
				} else if (userSelection == 2) {
					MenuItems.welcomeMenu();
				} else {
					System.out.println("Please input a number from the menu above!");
					trueCheck = false;
					MenuItems.usersMenu();
				}
			}
			scanner.close();
		} catch (InputMismatchException e) {
			System.out.println("wrong input, please input a number!");
			MenuItems.usersMenuCreateUser();
		}
	}

	public static void usersMenuDeleteUser() throws InputMismatchException {
		try {
			Scanner scanner = new Scanner(System.in);
			boolean trueCheck = true;
			while (trueCheck == true) {
				System.out.println("\nDelete User - \nEnter a username to delete");
				String scannerNextText = scanner.next();
				userDatabase.deleteUserFromFile(scannerNextText);
				System.out.println("would you like to go back to:\n1. Users\n2. Main Menu");
				int userSelection = scanner.nextInt();
				if (userSelection == 1) {
					MenuItems.usersMenu();
				} else if (userSelection == 2) {
					MenuItems.welcomeMenu();
				} else {
					System.out.println("Please input a number from the menu above!");
					trueCheck = false;
					MenuItems.usersMenu();
				}
			}
			scanner.close();			
		} catch (InputMismatchException e) {
			System.out.println("wrong input, please input a number!");
			MenuItems.usersMenuDeleteUser();
		}	
	}

	public static void usersMenuPrintUsers() throws InputMismatchException {
		try {
			Scanner scanner = new Scanner(System.in);
			boolean trueCheck = true;
			while (trueCheck == true) {
				if (userDatabase.isFileEmpty() == true) {
					System.out.println("There are no users!");
				} else {
					userDatabase.loadUsersFromFile("C:\\Dev\\test_files\\usersDatabase.txt");
				}
				System.out.println("would you like to go back to:\n1. Users\n2. Main Menu");
				int userSelection = scanner.nextInt();
				if (userSelection == 1) {
					MenuItems.usersMenu();
				} else if (userSelection == 2) {
					MenuItems.welcomeMenu();
				} else {
					System.out.println("Please input a number from the menu above!");
					trueCheck = false;
					MenuItems.usersMenu();
				}
			}
			scanner.close();		
		} catch (InputMismatchException e) {
			System.out.println("wrong input, please input a number!");
			MenuItems.usersMenuPrintUsers();			
		}		
	}
	
	public static void tasksMenu() throws InputMismatchException {
		try {
			Scanner scanner = new Scanner(System.in);
			boolean trueCheck = true;
			while (trueCheck == true) {
				System.out.println("TASKS - \n1. Create Task\n2. Delete Tasks\n3. See all Tasks\n4. Main Menu");
				int userSelection = scanner.nextInt();
				if (userSelection == 1) {
					MenuItems.tasksMenuCreateMenu();
				} else if (userSelection == 2) {
					MenuItems.tasksMenuDeleteMenu();
				} else if (userSelection == 3) {
					MenuItems.tasksMenuPrintTasks();
				} else if (userSelection == 4) {
					MenuItems.welcomeMenu();
				} else {
					System.out.println("Please input a number from the menu above!");
					trueCheck = false;
					MenuItems.tasksMenu();
				}
			}
			scanner.close();			
		} catch (InputMismatchException e) {
			System.out.println("wrong input, please input a number!");
			MenuItems.tasksMenu();
		}	
	}

	public static void tasksMenuCreateMenu() throws InputMismatchException {
		try {
			Scanner scanner = new Scanner(System.in);
			Scanner scanner2 = new Scanner(System.in);
			Scanner scanner3 = new Scanner(System.in);
			Scanner scanner4 = new Scanner(System.in);
			boolean trueCheck = true;
			while (trueCheck == true) {
				System.out.println("\nAdd a new task to the list, enter task name..");
				String scannerTaskName = scanner.next();
				System.out.println("\nAssign this task to a user! enter name:");
				String scannerTaskUser = scanner2.next();
				System.out.println("\nWhat details would you like to be added to this task?");
				String scannerTaskDetails = scanner4.next();
				System.out.println("\nOn what day of the week should it be done?");
				String scannerTaskDay = scanner3.next();
				DayOfWeek dayOfWeek;
				switch (scannerTaskDay.toLowerCase()) {
				case "monday":
					dayOfWeek = DayOfWeek.MONDAY;
					break;
				case "tuesday":
					dayOfWeek = DayOfWeek.TUESDAY;
					break;
				case "wednesday":
					dayOfWeek = DayOfWeek.WEDNESDAY;
					break;
				case "thursday":
					dayOfWeek = DayOfWeek.THURSDAY;
					break;
				case "friday":
					dayOfWeek = DayOfWeek.FRIDAY;
					break;
				case "saturday":
					dayOfWeek = DayOfWeek.SATURDAY;
					break;
				case "sunday":
					dayOfWeek = DayOfWeek.SUNDAY;
					break;
				default:
					System.out.println("Invalid day of the week input");
					return; // Exit the method if the input is invalid
				}
				taskDatabase.createTask(scannerTaskName, scannerTaskUser, scannerTaskDetails, dayOfWeek);
				System.out.println("Would you like to go back to:\n1. Tasks\n2. Main Menu");
				int userSelection = scanner.nextInt();
				if (userSelection == 1) {
					MenuItems.tasksMenu();
				} else if (userSelection == 2) {
					MenuItems.welcomeMenu();
				} else {
					System.out.println("Please input a number from the menu above!");
					trueCheck = false;
					MenuItems.tasksMenu();
				}
			}
			scanner.close();
			scanner2.close();
			scanner3.close();	
			scanner4.close();
		} catch (InputMismatchException e) {
			System.out.println("wrong input, please input a number!");
			MenuItems.tasksMenuCreateMenu();
		}			
	}

	public static void tasksMenuDeleteMenu() throws InputMismatchException {
		try {
			Scanner scanner = new Scanner(System.in);
			boolean trueCheck = true;
			while (trueCheck == true) {
				System.out.println("\nDelete Task - \nWhat task would you like to remove?");
				String scannerNextText = scanner.next();
				taskDatabase.deleteTaskFromFile(scannerNextText);
				System.out.println("Would you like to go back to:\n1. Tasks\n2. Main Menu");
				int userSelection = scanner.nextInt();
				if (userSelection == 1) {
					MenuItems.tasksMenu();
				} else if (userSelection == 2) {
					MenuItems.welcomeMenu();
				} else {
					System.out.println("Please input a number from the menu above!");
					trueCheck = false;
					MenuItems.tasksMenu();
				}
			}
			scanner.close();			
		} catch (InputMismatchException e) {
			System.out.println("wrong input, please input a number!");
			MenuItems.tasksMenuDeleteMenu();
		}		
	}

	public static void tasksMenuPrintTasks() throws InputMismatchException {
		try {
			Scanner scanner = new Scanner(System.in);
			boolean trueCheck = true;
			while (trueCheck == true) {
				if (taskDatabase.isFileEmpty() == true) {
					System.out.println("There are no tasks!");
				} else {
					taskDatabase.loadTaskFromFile("C:\\Dev\\test_files\\TaskDatabase.txt");
				}
				System.out.println("Would you like to go back to:\n1. Tasks\n2. Main Menu");
				int userSelection = scanner.nextInt();
				if (userSelection == 1) {
					MenuItems.tasksMenu();
				} else if (userSelection == 2) {
					MenuItems.welcomeMenu();
				} else {
					System.out.println("Please input a number from the menu above!");
					trueCheck = false;
					MenuItems.tasksMenu();
				}
			}
			scanner.close();		
		} catch (InputMismatchException e) {
			System.out.println("wrong input, please input a number!");
			MenuItems.tasksMenuPrintTasks();
		}		
	}

	public static void scheduleMenu() {
		Scanner scanner = new Scanner(System.in);
		boolean trueCheck = true;
		while (trueCheck == true) {
			System.out.println("SCHEDULE - \n1. See Schedule\n2. Create New Schedule\n3. Main Menu");
			int userSelection = scanner.nextInt();
			if (userSelection == 1) {
				System.out.println("--still in progress--");
			} else if (userSelection == 2) {
				System.out.println("--still in progress--");
			} else {
				System.out.println("Please input a number from the menu above!");
				trueCheck = false;
				MenuItems.scheduleMenu();
			}
		}
		scanner.close();
	}

}
