package timo;

public abstract class Package{
	private double sizeLimit;
	private double weightLimit;
	// Should these be set in Package classes' constructors or via setters?
	private Item pItem;
	private String startPoint;
	private String destination;
}
