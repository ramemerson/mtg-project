package com.gft.training.medienhaus.data;

public class Mediums {

	private String IdNumber;
	private String typeOfMedium;
	private String titleOfMedium;
	private String artist;
	private Double price;

	public Mediums(String IdNumber, String typeOfMedium, String titleOfMedium, String artist, double price) {
		this.IdNumber = IdNumber;
		this.typeOfMedium = typeOfMedium;
		this.titleOfMedium = titleOfMedium;
		this.artist = artist;
		this.price = price;
	}

	public Mediums() {
		// TODO Auto-generated constructor stub
	}

	public String getIdNumber() {
		return IdNumber;
	}

	public void setIdNumber(String idNumber) {
		IdNumber = idNumber;
	}

	public String getTypeOfMedium() {
		return typeOfMedium;
	}

	public void setTypeOfMedium(String typeOfMedium) {
		this.typeOfMedium = typeOfMedium;
	}

	public String getTitleOfMedium() {
		return titleOfMedium;
	}

	public void setTitleOfMedium(String titleOfMedium) {
		this.titleOfMedium = titleOfMedium;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "ID: " + this.getIdNumber() + " Type: " + this.getTypeOfMedium() + " Tittle: " + this.getTitleOfMedium()
				+ " Artist: " + this.getArtist() + " Price: " + this.getPrice() + "\n";
	}

}
