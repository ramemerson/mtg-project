
public class Mediums {

	private String IdNumber;
	private String typeOfMedium;
	private String titleOfMedium;
	private String artist;
	private int price;
	
	public Mediums (String IdNumber, String typeOfMedium, String titleOfMedium, String artist, int price) {
		this.IdNumber = IdNumber;
		this.typeOfMedium = typeOfMedium;
		this.titleOfMedium = titleOfMedium;
		this.artist = artist;
		this.price = price;
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
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	
	
	
}
