package carinfos;

public class ElectricCar extends Car {

	private int batteryMax;
	private int averageBatteryUsage;

	public ElectricCar(String carSerialID, String carBrand, String carModel, int horsePower, int maxCarSpeed,
			int batteryMax, int averageBatteryUsage) {
		super(carSerialID, carBrand, carModel, horsePower, maxCarSpeed);
		this.setBatteryMax(batteryMax);
		this.setAverageBatteryUsage(averageBatteryUsage);

	}

	public int getBatteryMax() {
		return batteryMax;
	}

	public void setBatteryMax(int batteryMax) {
		this.batteryMax = batteryMax;
	}

	public int getAverageBatteryUsage() {
		return averageBatteryUsage;
	}

	public void setAverageBatteryUsage(int averageBatteryUsage) {
		this.averageBatteryUsage = averageBatteryUsage;
	}

}
