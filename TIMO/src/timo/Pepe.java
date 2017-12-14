package timo;

public class Pepe extends Item {
	// If sent in first class, then must be rare
	private String rarity = "Common";
	private boolean isSad = true;
	
	public Pepe() {
		this.mass = 4.5;
		this.size = 4;
		this.isFragile = true;
	}
}
