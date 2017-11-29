package timo;

public class PackageInfo {
	// Adapted from Olio-Opas Osa 2
	// Will contain data fetched from package creation window
	private static PackageInfo instance = null;
    private String text;
    
    protected PackageInfo() {}
    
    public static PackageInfo getInstance() {
        if(instance == null) {
            instance = new PackageInfo();
        }
        return instance;
    }
    
    public String getText() {
        return text;
    }
    
    public void setText(String input) {
        text = input;
    }
}
