import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CreateMediaTypes {

	private static String filePath = "C:\\Dev\\test_files\\Plattenhaus";

	public static void createNewMediaTypeFile(String typeOfMedium) {

		File file = new File(filePath + "\\" + typeOfMedium);

		if (!file.exists()) {
			File newCreatedFile = new File(filePath + "\\" + typeOfMedium + ".csv");
			try {
				newCreatedFile.createNewFile();
				try (BufferedWriter writer = new BufferedWriter(new FileWriter(newCreatedFile, true))) {
					writer.write("Media ID, Type Of Media, Title, Artist, Price");
					writer.newLine();
				} catch (Exception e) {
					System.err.println("An error occurred while adding the item to the file: " + e.getMessage());
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (file.exists()) {
			System.out.println("File already exists.");
		}
	}

	public static List<String> returnListOfAllExistingMediaTypes() {
		List<String> temporarySave = new ArrayList<String>();
		List<String> allFileNames = new ArrayList<String>();
		File[] files = new File(filePath).listFiles();

		for (File file : files) {
			if (file.isFile()) {
				temporarySave.add(file.getName());
			}
		}

		for (String file : temporarySave) {
			String fileWithoutExt = file.substring(0, file.indexOf("."));
			allFileNames.add(fileWithoutExt);
		}

		return allFileNames;
	}

	public static Mediums createMediaItems(String IdNumber, String typeOfMedium, String titleOfMedium, String artist,
			int price) {
		List<String> allFileNames = returnListOfAllExistingMediaTypes();

		if (allFileNames.contains(typeOfMedium)) {
			Mediums newMedium = new Mediums(IdNumber, typeOfMedium, titleOfMedium, artist, price);
			File foundFile = new File(filePath + "\\" + typeOfMedium + ".csv");
			try (BufferedWriter writer = new BufferedWriter(new FileWriter(foundFile, true))) {
				writer.write(IdNumber + "," + typeOfMedium + "," + titleOfMedium + "," + artist + "," + price);
				writer.newLine();
				System.out.println("Item added!");
				return newMedium;
			} catch (Exception e) {
				System.err.println("An error occurred while adding the item to the file: " + e.getMessage());
			}
		}
		return null;
	}

}
