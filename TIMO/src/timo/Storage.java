package timo;


public class Storage {
	// Stores Packages
	private static Storage instance = null;
	
	private Storage() {}
	
	public static Storage getInstance() {
		if(instance == null) {
			instance = new Storage();
		}
		return instance;
	}
}
