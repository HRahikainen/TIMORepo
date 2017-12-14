package timo;

public class Glasses extends Item {
	// Always lost
	private boolean isLost = true;
	public Glasses() {
		this.mass = 0.5;
		this.size = 3;
		this.isFragile = true;
	}
}
