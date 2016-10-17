package assignment4;
/*
 * This critter either walks or runs, depending on if run is on cooldown.
 * If run is on cooldown(true), then this Critter walks.  else it runs(and run is put back on cooldown)
 * If the critter encounters a fight, cooldown is no longer on cooldown, and if it wins the fight, then the critter may run the next time step.
 */
public class Critter3 extends Critter{
	private boolean runCooldown;
	
	public Critter3()
	{
		runCooldown = false;
	}
	
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
