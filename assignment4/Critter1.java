
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

/*
 * This critter is a basic critter that models the motion patterns
 * of a knight on a chess board. Since he is a lone soldier, he only
 * sires one child for the entire duration of his lifetime. He also
 * has a high amount of health to begin with.
 */
public class Critter1 extends Critter {
	
	@Override
	public String toString() { return "1"; }
	
	private int dir;
	
	public Critter1() {
		dir = Critter.getRandomInt(8);
	}
	
	// 1 in 4 chance of fighting
	public boolean fight(String not_used) {
		int wannaFite = Critter.getRandomInt(4);
		if (wannaFite == 3) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void doTimeStep() {
		if (dir%2 == 1) {
			super.walk(dir);
		} else {
			super.run(dir);
		}
	}
}
