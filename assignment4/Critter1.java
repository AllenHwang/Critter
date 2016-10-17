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
		dir = Critter.getRandomInt(16);
		try {
			Critter.makeCritter(this.getClass().getName());
		} catch (InvalidCritterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean fight(String not_used) {
		return true;
	}

	@Override
	public void doTimeStep() {
		/* move like a chess knight */
		jump(dir);
	}
	
	private void jump (int dir) {
		switch (dir) {
			case 0: super.run(dir/2);
					super.walk(dir/2+6);
					break;
			case 1: super.run(dir/2);
					super.walk(dir/2+2);
					break;
			case 2: super.run(dir/2);
					super.walk(dir/2+6);
					break;
			case 3: super.run(dir/2);
					super.walk(dir/2+2);
					break;
			case 4: super.run(dir/2);
					super.walk(dir/2+6);
					break;
			case 5: super.run(dir/2);
					super.walk(dir/2+2);
					break;
			case 6: super.run(dir/2);
					super.walk(dir/2+6);
					break;
			case 7: super.run(dir/2);
					super.walk(dir/2+2);
					break;
			case 8: super.run(dir/2);
					super.walk(dir/2+6);
					break;
			case 9: super.run(dir/2);
					super.walk(dir/2+2);
					break;
			case 10: super.run(dir/2);
					super.walk(dir/2+6);
					break;
			case 11: super.run(dir/2);
					super.walk(dir/2+2);
					break;
			case 12: super.run(dir/2);
					super.walk(dir/2+2);
					break;
			case 13: super.run(dir/2);
					super.walk(dir/2+6);
					break;
			case 14: super.run(dir/2);
					super.walk(dir/2+2);
					break;
			case 15: super.run(dir/2);
					super.walk(dir/2+6);
					break;
		}
	}
}
