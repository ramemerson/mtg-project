import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class SearchInFiles {

	private static File filePath = new File("C:\\Dev\\test_files\\Plattenhaus");

	public static String printById(String id) throws IOException {
		boolean titleFound = false;
		for (File files : filePath.listFiles()) {
			if (files.isDirectory()) {
				return files.toString();
			} else {
				ArrayList<String> lines = new ArrayList<String>();
				try (BufferedReader br = new BufferedReader(new FileReader(files))) {
					String foundId;
					while ((foundId = br.readLine()) != null) {
						String[] splitFoundLine = foundId.split(",");
						if (foundId.toLowerCase().contains(id.toLowerCase()) || foundId.contains(id.toLowerCase())) {
							if (splitFoundLine[3].equals(id)) {
								titleFound = true;
								lines.add(foundId);
							}
						}
					}
				}
				if (!lines.isEmpty()) {
					System.out.println(files.getName().replace(".csv", "") + ":");
					for (String line : lines) {
						System.out.println("  " + line);
					}
				}
			}
		}
		if (!titleFound) {
			System.out.println("ID not found!");
		}
		return null;
	}

	public static String printItemByTitle(String title) throws IOException {
		boolean titleFound = false;
		for (File files : filePath.listFiles()) {
			if (files.isDirectory()) {
				return files.toString();
			} else {
				ArrayList<String> lines = new ArrayList<String>();
				try (BufferedReader br = new BufferedReader(new FileReader(files))) {
					String foundLine;
					while ((foundLine = br.readLine()) != null) {
						String[] splitFoundLine = foundLine.split(",");
						if (foundLine.contains(title.toLowerCase())
								|| foundLine.toLowerCase().contains(title.toLowerCase())) {
							if (splitFoundLine[2].toLowerCase().equals(title.toLowerCase())) {
								titleFound = true;
								lines.add(foundLine);
							}
						} 
					}
				}
				if (!lines.isEmpty()) {
					System.out.println(files.getName().replace(".csv", "") + ":");
					for (String line : lines) {
						System.out.println("  " + line);
					}
				} 
			}
		}
		if (!titleFound) {
			System.out.println("Title not found!");
		}
		return null;
	}

	public static String printItemsByArtist(String artistName) throws IOException {
		boolean titleFound = false;
		for (File files : filePath.listFiles()) {
			if (files.isDirectory()) {
				return files.toString();
			} else {
				ArrayList<String> lines = new ArrayList<String>();
				try (BufferedReader br = new BufferedReader(new FileReader(files))) {
					String foundLine = " ";
					while ((foundLine = br.readLine()) != null) {
						String[] splitFoundLine = foundLine.split(",");
						if (foundLine.toLowerCase().contains(artistName.toLowerCase())) {
							if (splitFoundLine[3].toLowerCase().equals(artistName.toLowerCase())) {
								lines.add(foundLine);
								titleFound = true;
							}
						}
					}
				}
				if (!lines.isEmpty()) {
					System.out.println(files.getName().replace(".csv", "") + ":");
					for (String line : lines) {
						System.out.println("  " + line);
					}
				}
			}
		}
		if (!titleFound) {
			System.out.println("Artist not found!");
		}
		return null;
	}
}
