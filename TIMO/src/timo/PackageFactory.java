package timo;

public class PackageFactory {
	// Will make packages of data fetched from package creation window
	// Factory Pattern to create child class objects on the fly
	private static PackageFactory instance = null;
	
    protected PackageFactory() {}
    
    public static PackageFactory getInstance() {
        if(instance == null) {
            instance = new PackageFactory();
        }
        return instance;
    }
    
	public Package newPackage(String packageType){
		// Chooses first one that is selected if multiple are tried
		if(packageType == null) {
			return null;
		}
		if(packageType.equalsIgnoreCase("1st class")){
			return new FirstClassPackage();

		} else if(packageType.equalsIgnoreCase("2nd class")){
			return new SecondClassPackage();

		} else if(packageType.equalsIgnoreCase("3rd class")){
			return new ThirdClassPackage();
		}

		return null;
	}

}
