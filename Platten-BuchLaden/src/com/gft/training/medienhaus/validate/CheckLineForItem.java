package com.gft.training.medienhaus.validate;

import java.io.IOException;

public class CheckLineForItem {
	
public static boolean returnTrueIfItemIsFoundInLine (String line, String itemToSearchBy, int columnToSearchBy) throws IOException {
		
		String[] splitLine = line.split(",");
		
		if (line != null) {
			if (line.toLowerCase().contains(itemToSearchBy.toLowerCase())) {
				if (splitLine[columnToSearchBy].toLowerCase().equals(itemToSearchBy.toLowerCase())) {
					return true;
				}
			}
		}
		return false;
	}

}
