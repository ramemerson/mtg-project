package carinfos;

public class Car {
	
	//car details 
	private String carSerialID;
	private String carBrand;
	private String carModel;
	private int horsePower;
	private int maxCarSpeed;
	private int tankMaxVolume;
	private int averageUsage; //average gas usage per 100km 
	

	//constructors
	public Car(String carSerialID, String carBrand, String carModel, int horsePower, int maxCarSpeed, int tankMaxVolume, int averageUsage) {
		this.carSerialID = carSerialID;
		this.setCarBrand(carBrand);
		this.setCarModel(carModel);
		this.setHorsePower(horsePower);
		this.setMaxCarSpeed(maxCarSpeed);
		this.setTankMaxVolume(tankMaxVolume);
		this.setAverageUsage(averageUsage);	
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

	public int getTankMaxVolume() {
		return tankMaxVolume;
	}

	public void setTankMaxVolume(int tankMaxVolume) {
		this.tankMaxVolume = tankMaxVolume;
	}

	public int getAverageUsage() {
		return averageUsage;
	}

	public void setAverageUsage(int averageUsage) {
		this.averageUsage = averageUsage;
	}

	public String getCarSerialID() {
		return carSerialID;
	}

	public void setCarSerialID(String carSerialID) {
		this.carSerialID = carSerialID;
	}
	

}
