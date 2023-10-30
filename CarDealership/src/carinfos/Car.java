package carinfos;

import java.lang.invoke.CallSite;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Car {
	
	//car details 
	private String carBrand;
	private String carModel;
	private int carPS;
	private int maxCarSpeed;
	private int tankMaxVolume;
	private int averageUsage; //average gas usage per 100km 
	private HashMap<String, > listOfCarsHashMap;
	
	//constructors
	public void createCar(String carBrand, String carModel, int carPS, int maxCarSpeed, int tankMaxVolume, int averageUsage) {
		this.carBrand = carBrand;
		this.carModel = carModel;
		this.carPS = carPS;
		this.maxCarSpeed = maxCarSpeed;
		this.tankMaxVolume = tankMaxVolume;
		this.averageUsage = averageUsage;
			
		
	}
	

}
