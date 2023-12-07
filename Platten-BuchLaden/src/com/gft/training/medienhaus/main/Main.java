package com.gft.training.medienhaus.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

import com.gft.training.medienhaus.data.Mediums;
import com.gft.training.medienhaus.data.SortData;
import com.gft.training.medienhaus.io.CSVReader;
import com.gft.training.medienhaus.ui.Menus;

public class Main {

	public static void main(String[] args) throws IOException {
		
		Menus.welcomeMenu(null);
//		Menus.firstSelectionMenu();	
		
		Comparator<Mediums> highToLowComparator = (o1, o2) -> {
			if (o1.getPrice() < o2.getPrice()) {
				return 1;
			}
			if (o1.getPrice() > o2.getPrice()) {
				return -1;
			}
			return 0;
		};
		
		
		
	}

}
