package assignment4;

public class Critter4 extends Critter{
	boolean weaponDrawn = false;
	
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
