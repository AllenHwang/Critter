package assignment4;

/*
 * This critter is a basic critter that models the motion patterns
 * of a knight on a chess board. Since he is a lone soldier, he only
 * sires one child for the entire duration of his lifetime. He also
 * has a high amount of health to begin with.
 */
public class Critter2 extends Critter{
	
	@Override
	public String toString() { return "1"; }
	
	private int dir;
	
	public Critter2() {
		dir = Critter.getRandomInt(8);
	}
	
	// Critter2 fights if it is in the mood
	public boolean fight(String not_used) {
		int wannaFite = Critter.getRandomInt(1);
		if (wannaFite%2 == 1) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void doTimeStep() {
		/* walk normally */
		super.walk(dir);
	}
}
