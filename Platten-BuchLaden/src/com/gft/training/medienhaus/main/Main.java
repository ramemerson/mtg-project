package com.gft.training.medienhaus.main;

import java.io.IOException;
import java.util.ArrayList;

import com.gft.training.medienhaus.data.Mediums;
import com.gft.training.medienhaus.data.SortData;
import com.gft.training.medienhaus.io.CSVReader;
import com.gft.training.medienhaus.ui.Menus;

public class Main {

	public static void main(String[] args) throws IOException {
		
//		Menus.welcomeMenu();
//		Menus.firstSelectionMenu();	
		
		System.out.println(CSVReader.readFileAndCreateProductArrayList("Movie"));
		
	}

}
