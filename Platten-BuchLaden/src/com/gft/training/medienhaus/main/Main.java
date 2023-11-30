package com.gft.training.medienhaus.main;
import java.awt.print.Printable;
import java.io.IOException;

import com.gft.training.medienhaus.data.PrintData;
import com.gft.training.medienhaus.ui.Menus;

public class Main {

	public static void main(String[] args) throws IOException {
		
//		Menus.welcomeMenu();
//		Menus.firstSelectionMenu();
//		
		
		PrintData.printDataFromFile(null, "brian eno", null);
		
		
	}

}
