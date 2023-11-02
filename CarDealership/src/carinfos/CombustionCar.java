package carinfos;

public class CombustionCar extends Car{
	
	private int tankMaxVolume;
	private int averageGasUsage;

	public CombustionCar(String carSerialID, String carBrand, String carModel, int horsePower, int maxCarSpeed,
			int tankMaxVolume, int averageGasUsage) {
		super(carSerialID, carBrand, carModel, horsePower, maxCarSpeed);
		this.setTankMaxVolume(tankMaxVolume);
		this.setAverageGasUsage(averageGasUsage);
		
	}

	public int getTankMaxVolume() {
		return tankMaxVolume;
	}

	public void setTankMaxVolume(int tankMaxVolume) {
		this.tankMaxVolume = tankMaxVolume;
	}

	public int getAverageGasUsage() {
		return averageGasUsage;
	}

	public void setAverageGasUsage(int averageGasUsage) {
		this.averageGasUsage = averageGasUsage;
	}

}
