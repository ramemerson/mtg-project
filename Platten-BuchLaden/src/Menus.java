import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import javax.swing.plaf.synth.SynthOptionPaneUI;

public class Menus {
	
	public static void welcomeMenu() {
		System.out.println("------------------------------------");
		System.out.println("Welcome to the multimedia database");
		System.out.println("------------------------------------");
		System.out.println("");
	}
	
	public static void firstSelectionMenu() throws IOException {
		System.out.println("");
		System.out.println("Select a function from below:");
		System.out.println("[1] Add media types\n[2] Add media\n[3] See all different media types\n[4] Search\n");
			try {
				Scanner scanner = new Scanner(System.in);
				int selection = scanner.nextInt();
				if (TypeValidation.validateNumber(selection) == true) {
					switch (selection) {
					case 1:
						addMediaTypeMenu();
						break;	
					case 2:
						addMediaMenu();
						break;
					case 3:
						seeAllMediaTypes();
						break;
					case 4:
						searchMenu();
						break;
					default:
						System.out.println("wrong input, please try again\n");
						firstSelectionMenu();
						break;
					}
				}
				
			} catch (NoSuchElementException e) {
				System.out.println("wrong input, please try again\n");
				firstSelectionMenu();
			}
		}
	
	public static void addMediaTypeMenu() throws IOException {
		System.out.println("First check if the media type already exists:");
		System.out.println(CreateMedias.returnListOfAllExistingMediaTypes());
		System.out.println("\nWould you like to add a new media type? [1] yes [2] no");	
		try {
			Scanner scanner = new Scanner(System.in);
			int selection = scanner.nextInt();
			if (TypeValidation.validateNumber(selection) == true) {
				switch (selection) {
				case 1:
					System.out.println("Type of media to add:");
					String userInput = scanner.next();
					if (TypeValidation.validateLetter(userInput) == true) {
						CreateMedias.createNewMediaTypeFile(userInput);
					}
					firstSelectionMenu();
					break;
				case 2:
					firstSelectionMenu();
					break;
				default:
					System.out.println("wrong input, please try again\n");
					break;
				}
			}	
		} catch (NoSuchElementException e) {
			System.out.println("wrong input, please try again\n");
			addMediaMenu();
		}
		
		
	}

	public static void addMediaMenu() throws IOException{
		try {
			Scanner scanner = new Scanner(System.in);
			System.out.println("Add a new media item: ");
			System.out.println("Input media type:");
			String type = scanner.next();
			if (TypeValidation.validateLetter(type) == true) {
				System.out.println("Input media ID:");
				String id = scanner.next();
				System.out.println("Input artist name:");
				String artist = scanner.next();
				System.out.println("Input media title:");
				String title = scanner.next();
				System.out.println("Input price of media:");
				int price = scanner.nextInt();
				System.out.println("check if the inputed data is correct - ");
				System.out.println("---------------------------------------------------------------------------------");
				System.out.println("Media type: " + type + ", Media ID: " + id + ", Artist name: " + artist + ", Media title: " + title + ", Price: " + price);
				System.out.println("---------------------------------------------------------------------------------");
				System.out.println("[1] yes [2] no/redo");
				int selection = scanner.nextInt();
				switch (selection) {
				case 1:
					CreateMedias.createMediaItems(id, type, title, artist, price);
					firstSelectionMenu();
					break;
				case 2:
					System.out.println("Which item would you like to edit? [1] Type [2] ID [3] Artist [4] Title [5] Price");
					int editSelection = scanner.nextInt();
					switch (editSelection) {
					case 1:
						System.out.println("Enter the edited type of media:");
						String newType = scanner.next();
						CreateMedias.createMediaItems(id, newType, title, artist, price);
						firstSelectionMenu();
						break;
					case 2:
						System.out.println("Enter the edited id:");
						String newId = scanner.next();
						CreateMedias.createMediaItems(newId, type, title, artist, price);
						firstSelectionMenu();
						break;
					case 3:
						System.out.println("Enter the edited artist name:");
						String newArtist = scanner.next();
						CreateMedias.createMediaItems(id, type, title, newArtist, price);
						firstSelectionMenu();
						break;
					case 4:
						System.out.println("Enter the edited title:");
						String newTitle = scanner.next();
						CreateMedias.createMediaItems(id, type, newTitle, artist, price);
						firstSelectionMenu();
						break;
					case 5:
						System.out.println("Enter the edited price:");
						int newPrice = scanner.nextInt();
						CreateMedias.createMediaItems(id, type, title, artist, newPrice);
						firstSelectionMenu();
						break;
					default:
						System.out.println("Wrong input, please try again!");
						addMediaMenu();
						break;
					}
					break;
				default:
					System.out.println("wrong input");
					firstSelectionMenu();
					break;
				}
			}
		} catch (NoSuchElementException e) {
			System.out.println("wrong input, please try again\n");
			addMediaMenu();
		}
		
	}
	
	public static void seeAllMediaTypes() throws IOException {
		System.out.println(CreateMedias.returnListOfAllExistingMediaTypes());
		firstSelectionMenu();
	}
	
	public static void searchMenu() throws IOException{
		System.out.println("What would you like to search by? [1] Artist [2] Title [3] ID");
		try {
			Scanner scanner = new Scanner(System.in);
			int selection = scanner.nextInt();
			if (TypeValidation.validateNumber(selection) == true) {
				switch (selection) {
				case 1:
					System.out.println("Input artist to search by:");
					String artistToSearch = scanner.next();
					if (TypeValidation.validateLetter(artistToSearch) == true) {
						SearchInFiles.printItemsByArtist(artistToSearch);
						firstSelectionMenu();
					} else {
						System.out.println("wrong type of input, please try that again");
						searchMenu();
					}
					break;
				case 2:
					System.out.println("Input a title to search for:");
					String titleToSearch = scanner.next();
					if (TypeValidation.validateLetter(titleToSearch) == true) {
						SearchInFiles.printItemByTitle(titleToSearch);
						firstSelectionMenu();
					} else {
						System.out.println("wrong type of input, please try that again");
						searchMenu();
					}
					break;
				case 3:
					System.out.println("Input an ID to search for:");
					String idToSearch = scanner.next();
					if (TypeValidation.validateLetter(idToSearch) == true) {
						SearchInFiles.printById(idToSearch);
						firstSelectionMenu();
					} else {
						System.out.println("wrong type of input, please try that again");
						searchMenu();
					}
					break;
				default:
					System.out.println("wrong input, please try again\n");
					break;
				}
			}	
		} catch (NoSuchElementException e) {
			System.out.println("wrong input, please try again\n");
			searchMenu();
		}
		
	}
}
