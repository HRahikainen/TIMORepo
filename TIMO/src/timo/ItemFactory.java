package timo;

public class ItemFactory {
	/**
	 * Similar in functionality to PackageFactory counterpart
	 * */
	private static ItemFactory instance = null;
	
    protected ItemFactory() {}
    
    public static ItemFactory getInstance() {
        if(instance == null) {
            instance = new ItemFactory();
        }
        return instance;
    }
    
	public Item newItem(String itemType){

		if(itemType == null) {
			return null;
		}
		if(itemType.equalsIgnoreCase("Conscript")){
			return new Conscript();

		}else if(itemType.equalsIgnoreCase("DankMeme")){
	         return new DankMeme();

	    } else if(itemType.equalsIgnoreCase("Pepe")){
	         return new Pepe();
	    } else if(itemType.equalsIgnoreCase("SamsungGalaxyNoteSeven")){
	         return new SamsungGalaxyNoteSeven();
	    } else if(itemType.equalsIgnoreCase("Glasses")){
	         return new Glasses();
	    }

		return null;
	}
}
