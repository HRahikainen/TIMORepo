package timo;

public class ItemFactory {
	private static ItemFactory instance = null;
	
    protected ItemFactory() {}
    
    public static ItemFactory getInstance() {
        if(instance == null) {
            instance = new ItemFactory();
        }
        return instance;
    }
    
	public Item newItem(String itemType){
		// Chooses first one that is selected if multiple are tried		
	      if(itemType.equalsIgnoreCase("Conscript")){
	         return new Conscript();
	         
	      }/* else if(itemType.equalsIgnoreCase("2.luokka")){
	         return new SecondClassPackage();
	         
	      } else if(itemType.equalsIgnoreCase("3.luokka")){
	         return new ThirdClassPackage();
	      }*/
	      
	      return null;
	}
}
