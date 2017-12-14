package timo;

public class SamsungGalaxyNoteSeven extends Item {

	private boolean isBurning = true;

	public SamsungGalaxyNoteSeven() {
		this.mass = 2;
		this.size = 1;
		this.isFragile = true;
	}
	public boolean isBurning() {
		return isBurning;
	}

	public void setBurning(boolean isBurning) {
		this.isBurning = isBurning;
	}
}
