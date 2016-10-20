
/* CRITTERS Critter.java
 * EE422C Project 4 submission by
 * Allen Hwang
 * ah45755
 * 16445
 * Sagar Krishnaraj
 * sk37433
 * 16455
 * Slip days used: <0>
 * Fall 2016
 * GIT URL: https://github.com/HorizonStrider/Critter.git
 */
package assignment4;

/**
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
