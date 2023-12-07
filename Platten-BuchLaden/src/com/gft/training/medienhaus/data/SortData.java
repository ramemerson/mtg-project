package com.gft.training.medienhaus.data;

import java.util.ArrayList;
import java.util.Comparator;

public abstract class SortData implements Comparator<Mediums>{

	public static int comparePriceLowToHigh(Mediums o1, Mediums o2) {
		if (o1.getPrice() < o2.getPrice()) {
			return -1;
		}
		
		if (o1.getPrice() > o2.getPrice()) {
			return 1;
		}
		return 0;
	}
	
	public static int comparePriceHighToLow(Mediums o1, Mediums o2) {
		if (o1.getPrice() > o2.getPrice()) {
			return -1;
		}
		
		if (o1.getPrice() < o2.getPrice()) {
			return 1;
		}
		return 0;
	}

	protected abstract char[] comparePriceHighToLow(ArrayList<Mediums> readFileAndCreateProductArrayList,
			ArrayList<Mediums> readFileAndCreateProductArrayList2);
	
	
}
