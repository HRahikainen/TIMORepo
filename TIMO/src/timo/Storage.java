package timo;

import java.util.ArrayList;

public class Storage {
	/**
	 * Stores all created packages and keeps track of their count
	 */
	private static Storage instance = null;
	private static int packageCount = 0;
	private ArrayList<Package> packageList = null;
	
	private Storage() {packageList = new ArrayList<Package>();}
	
	public static Storage getInstance() {
		if(instance == null) {
			instance = new Storage();
		}
		return instance;
	}
	
	public ArrayList<Package> getPackages(){
		return packageList;
	}
	
	public void add(Package p) {
		packageList.add(p);
		packageCount++;
	}
	
	public int getCount() {
		return packageCount;
	}
	
	public void setCount() {
		// When storage is loaded from file, restore last count.
		packageCount = packageList.size();
	}
}
