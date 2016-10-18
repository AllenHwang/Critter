package assignment4;

/*
 * This Critter picks an initial direction to go in.
 * Once it picks a direction, it cannot change.
 * Every time fight is called, it has a 50% chance of either wishing to fight or not fight.
 *
 */
public class Critter2 extends Critter{
	
	@Override
	public String toString() { return "2"; }
	
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
