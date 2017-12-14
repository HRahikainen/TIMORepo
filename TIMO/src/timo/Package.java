package timo;

public abstract class Package{
	protected double sizeLimit;
	protected double weightLimit;
	
	protected Item pItem;
	protected SmartPost startPoint;
	protected SmartPost destination;
	protected int packageClass;
	
	// Allow info to be written after creation regardless of subclass
	public void setInfo(Item i, SmartPost start, SmartPost dest, int packageClass) {
		this.pItem = i;
		this.startPoint = start;
		this.destination = dest;
		this.packageClass = packageClass;
	}
	
	public String getInfo() {
		return this.getClass().getSimpleName() + " with " + this.pItem.getClass().getSimpleName() + " From: " + this.startPoint + " To: " + this.destination;
	}
	
	@Override
	public String toString() {
		return this.getInfo();
	}

	public double getSizeLimit() {
		return this.sizeLimit;
	}

	public double getWeightLimit() {
		return this.weightLimit;
	}

	public Item getpItem() {
		return this.pItem;
	}

	public SmartPost getStartPoint() {
		return this.startPoint;
	}

	public SmartPost getDestination() {
		return this.destination;
	}

	public int getPackageClass() {
		return this.packageClass;
	}
	
}
