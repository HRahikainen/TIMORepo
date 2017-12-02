package timo;

public abstract class Item {
	private double mass;
	private double size;
	private boolean isFragile;
	private boolean isBroken;
	
	public void setMass(double mass){
		this.mass = mass;
	}
	
	public void setSize(double size) {
		this.size = size;
	}
	
	public void setFragile(boolean isFragile) {
		this.isFragile = isFragile;
	}
	
	public double getMass() {
		return this.mass;
	}
	
	public double getSize() {
		return this.size;
	}
	
	public boolean getFragile() {
		return this.isFragile;
	}
	
	public boolean getBroken() {
		return this.isBroken;
	}
	
	public void breakItem() {
		this.isBroken = true;
	}

	// X amount of extending non-generic objects
	// Objects for example conscript who breaks every time the destination is either Lappi or Eastern Finland
}