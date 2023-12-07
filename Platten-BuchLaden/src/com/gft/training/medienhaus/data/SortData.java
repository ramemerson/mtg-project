package com.gft.training.medienhaus.data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

import com.gft.training.medienhaus.io.CSVReader;

public class SortData {
	
	public ArrayList<Mediums> sort(String fileToSort, String howToSort) throws IOException {
		ArrayList<Mediums> listNotSorted = CSVReader.readFileAndCreateProductArrayList(fileToSort);
		
		listNotSorted.sort(lowtoHigh);
		
		return listNotSorted;
		
	}
	
	Comparator<Mediums> lowtoHigh = (o1, o2) -> {
		if (o1.getPrice() < o2.getPrice()) {
			return -1;
		}

		if (o1.getPrice() > o2.getPrice()) {
			return 1;
		}

		return 0;
	};
}
