package assignment4;
/*
 * This critter walks around the world
 * Every time it walks, it changes its ability to fight
 * Fight simply returns its current ability to fight
 */
public class Critter4 extends Critter{
	boolean weaponDrawn;
	
	public Critter4()
	{
		weaponDrawn = false;
	}
	public String toString()
	{
		return "4";
	}
	
	public void doTimeStep()
	{
		int random = Critter.getRandomInt(8);
		walk(random);
		weaponDrawn = !weaponDrawn;
	}
	
	public boolean fight(String eh)
	{
		return weaponDrawn;
	}

}
