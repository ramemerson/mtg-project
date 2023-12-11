package com.gft.training.medienhaus.data;

import java.util.Comparator;

public class MediaComparator implements Comparator<Mediums> {

	private SortType type;
	private SortDirection direction;

	public enum SortType {
		PRICE,
		TITLE,
		ARTIST;
	}
	
	public enum SortDirection {
		ASC,
		DESC;
	}
	
	public MediaComparator(SortType type, SortDirection direction) {
		this.type = type;
		this.direction = direction;		
	}
	
	@Override
	public int compare(Mediums o1, Mediums o2) {
		int ret = 0;
		switch (type) {
		case PRICE:
			ret = o1.getPrice().compareTo(o2.getPrice());
			break;
		case TITLE:
			ret = o1.getTitleOfMedium().compareTo(o2.getTitleOfMedium());
			break;
		case ARTIST:
			ret = o1.getArtist().compareTo(o2.getArtist());
		default:
			break;
		}
		if (direction.equals(SortDirection.DESC)) {
			ret *= -1;
		}
		return ret;
	}
	

}
