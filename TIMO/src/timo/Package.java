package timo;

public abstract class Package{
	private double sizeLimit;
	private double weightLimit;
	
	private Item pItem;
	private String startPoint;
	private String destination;
	private String packageClass;
	
	// Allow info to be written after creation regardless of subclass
	public void setInfo(Item i, String start, String dest, String packageClass) {
		this.pItem = i;
		this.startPoint = start;
		this.destination = dest;
		this.packageClass = packageClass;
	}
	
	public String getInfo() {
		return this.packageClass + " From: " + this.startPoint + " To: " + this.destination;
	}
	
	// Debug method
	public void printInfo() {
		System.out.println(this.pItem.getClass());
		System.out.println(this.startPoint);
		System.out.println(this.destination);
	}
}
