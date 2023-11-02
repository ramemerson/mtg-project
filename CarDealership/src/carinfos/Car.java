package carinfos;

public class Car {
	
	//car details 
	private String carSerialID;
	private String carBrand;
	private String carModel;
	private int horsePower;
	private int maxCarSpeed;

	//constructors
	public Car(String carSerialID, String carBrand, String carModel, int horsePower, int maxCarSpeed) {
		this.carSerialID = carSerialID;
		this.setCarBrand(carBrand);
		this.setCarModel(carModel);
		this.setHorsePower(horsePower);
		this.setMaxCarSpeed(maxCarSpeed);
	}

	public String getCarBrand() {
		return carBrand;
	}

	public void setCarBrand(String carBrand) {
		this.carBrand = carBrand;
	}

	public String getCarModel() {
		return carModel;
	}

	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}

	public int getHorsePower() {
		return horsePower;
	}

	public void setHorsePower(int horsePower) {
		this.horsePower = horsePower;
	}

	public int getMaxCarSpeed() {
		return maxCarSpeed;
	}

	public void setMaxCarSpeed(int maxCarSpeed) {
		this.maxCarSpeed = maxCarSpeed;
	}

	public String getCarSerialID() {
		return carSerialID;
	}

	public void setCarSerialID(String carSerialID) {
		this.carSerialID = carSerialID;
	}
	

}
