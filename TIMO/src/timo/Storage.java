package timo;

import java.util.ArrayList;

public class Storage {
	private static Storage instance = null;
	private ArrayList<Package> packageList = null;
	
	private Storage() {packageList = new ArrayList<Package>();}
	
	public static Storage getInstance() {
		if(instance == null) {
			instance = new Storage();
		}
		return instance;
	}
	
	// This static too? 
	public ArrayList<Package> getPackages(){
		return packageList;
	}
}
