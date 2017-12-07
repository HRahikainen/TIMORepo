package timo;

public abstract class Package{
	private double sizeLimit;
	private double weightLimit;
	
	private Item pItem;
	private String startPoint;
	private String destination;
	
	// Allow info to be written after creation regardless of subclass
	public void setInfo(Item i, String start, String dest) {
		this.pItem = i;
		this.startPoint = start;
		this.destination = dest;
	}
	
	// Debug method
	public void printInfo() {
		System.out.println(this.pItem.getClass());
		System.out.println(this.startPoint);
		System.out.println(this.destination);
	}
}
