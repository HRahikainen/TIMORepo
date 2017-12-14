package timo;

public abstract class Item {
	protected double mass;
	protected double size;
	protected boolean isFragile;
	protected boolean isBroken;
		
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

}