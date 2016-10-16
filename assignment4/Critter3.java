package assignment4;

public class Critter3 extends Critter{
	
	private boolean runCooldown = false;
	public String toString()
	{
		return "3";
	}

	public void doTimeStep()
	{
		if(runCooldown)
		{
			int random = Critter.getRandomInt(8);
			super.walk(random);
	
		}
		else
		{
			int randomm = Critter.getRandomInt(8);
			runCooldown = true;
			super.run(randomm);
		}
	}
	
	public boolean fight(String doesnt_matter)
	{
		runCooldown = false;
		return true;
	}
}
