
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

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.List;

/* see the PDF for descriptions of the methods and fields in this class
 * you may add fields, methods or inner classes to Critter ONLY if you make your additions private
 * no new public, protected or default-package code or data can be added to Critter
 */


public abstract class Critter {
	private static String myPackage;
	private	static List<Critter> population = new java.util.ArrayList<Critter>();
	private static List<Critter> babies = new java.util.ArrayList<Critter>();
	private boolean hasMoved = false;
	// Gets the package name.  This assumes that Critter and its subclasses are all in the same package.
	static {
		myPackage = Critter.class.getPackage().toString().split(" ")[1];
	}
	
	private static java.util.Random rand = new java.util.Random();
	public static int getRandomInt(int max) {
		return rand.nextInt(max);
	}
	
	public static void setSeed(long new_seed) {
		rand = new java.util.Random(new_seed);
	}
	
	
	/**
	 *  a one-character long string that visually depicts your critter in the ASCII interface */
	public String toString() { return ""; }
	
	private int energy = 0;
	protected int getEnergy() { return energy; }
	
	private int x_coord;
	private int y_coord;
	/**
	 *  Increments the x_coord by the number given.
	 * @param stepsX The steps that x will move by (a negative means that the critter will move left)
	 * @return The x_coord after the stepX, accounting for moving across borders.
	 */
	private int stepX(int stepsX) {
		x_coord += stepsX;
		x_coord = (x_coord + Params.world_width) % Params.world_width;
		return x_coord;
	}
	
	/**
	 * Increments the y_coord by the number given.
	 * @param stepsY the steps that y will move by (a negative means that the critter will move up)
	 * @return The y_coord after the stepY, accounting for moving across borders.
	 */
	private int stepY(int stepsY) {
		y_coord += stepsY;
		y_coord = (y_coord + Params.world_height) % Params.world_height;
		return y_coord;
	}
	
	/**
	 * Walks in a given direction; direction is determined from a number 0-7 and goes counter clockwise with 0 going to the right
	 * It increases coordinates by one.
	 * It decrements energy by walk energy.
	 * @param direction direction the critter will walk.
	 */
	protected final void walk(int direction) {
		if(!hasMoved)
		{
			switch(direction)
			{
			case 0: x_coord = stepX(1);
			break;
			case 1: x_coord = stepX(1); y_coord = stepY(-1);
			break;
			case 2: y_coord = stepY(-1);
			break;
			case 3: y_coord = stepY(-1); y_coord = stepX(-1);
			break;
			case 4: x_coord = stepX(-1);
			break;
			case 5: y_coord = stepY(1); x_coord = stepX(-1);
			break;
			case 6: y_coord = stepY(1);
			break;
			case 7: y_coord = stepY(1); x_coord = stepX(1);
			break;
			}
			energy -= Params.walk_energy_cost;
			hasMoved=true;
		}
		else
		{
			energy -= Params.walk_energy_cost;
		}

	}
	/**
	 * Runs in a given direction, using 0-7, with 0 representing right and going counter clockwise incrementally.
	 * It increases coordinates by two
	 * It decrements energy by the run energy.
	 * @param direction the direction that the critter will run in
	 */
	protected final void run(int direction) {
		if(!hasMoved)
		{
			switch(direction)
			{
			case 0: x_coord = stepX(2);
			break;
			case 1: x_coord = stepX(2); y_coord = stepY(-2);
			break;
			case 2: y_coord = stepY(-2);
			break;
			case 3: y_coord = stepY(-2); y_coord = stepX(-2);
			break;
			case 4: x_coord = stepX(-2);
			break;
			case 5: y_coord = stepY(2); x_coord = stepX(-2);
			break;
			case 6: y_coord = stepY(2);
			break;
			case 7: y_coord = stepY(2); x_coord = stepX(2);
			break;
			}
			hasMoved = true;
			energy -= Params.run_energy_cost;
		}
		else
		{
			energy -= Params.run_energy_cost;
		}
	}
	/**
	 * Creates a new offspring that has roughly half of its original energy
	 * @param offspring the new Critter that will be created.
	 * @param direction places the new offspring onto a random tile
	 */
	protected final void reproduce(Critter offspring, int direction) {
		if(energy < Params.min_reproduce_energy)
			return;
		else
		{
			offspring.energy = energy/2;
			energy =(energy +1)/2;
			switch(direction)
			{
			case 0: offspring.x_coord = x_coord+1;
			break;
			case 1: offspring.x_coord = x_coord+1;offspring.y_coord = y_coord-1;
			break;
			case 2:offspring.y_coord = y_coord-1;
			break;
			case 3 :offspring.y_coord = y_coord-1;offspring.x_coord = x_coord-1;
			break;
			case 4 :offspring.x_coord = x_coord-1;
			break;
			case 5 :offspring.y_coord = y_coord+1;offspring.x_coord = x_coord-1;
			break;
			case 6:offspring.y_coord = y_coord+1;
			break;
			case 7: offspring.y_coord = y_coord+1;offspring.x_coord = x_coord+1;
			break;
			}
			offspring.x_coord =(Params.world_width + x_coord) % Params.world_width;
			offspring.y_coord = (Params.world_height + y_coord) % Params.world_height;
			babies.add(offspring);
		}
	}

	public abstract void doTimeStep();
	public abstract boolean fight(String oponent);
	
	/**
	 *   create and initialize a Critter subclass.  
	 *   critter_class_name must be the unqualified name of a concrete subclass of Critter, if not,
	 * an InvalidCritterException must be thrown. 
	 * (Java weirdness: Exception throwing does not work properly if the
	parameter has lower-case instead of
	 * upper. For example, if craig is supplied instead of Craig, an error is
		thrown instead of
	 * an Exception.)
	 * @param critter_class_name  The name of the critter to be made
	 * @throws InvalidCritterException Throws this exception if the critter does not exist
	 * */
	 
	public static void makeCritter(String critter_class_name) throws InvalidCritterException {
		
		// create new critter object
		
		Class<?> crit_class = null;
		String tempClass = myPackage + "." + critter_class_name;
		try {
			crit_class = Class.forName(tempClass);
		} catch (ClassNotFoundException e) {
			throw new InvalidCritterException("");
		}
		
		try {
			Object critterNew = crit_class.getConstructor().newInstance();
			
			// initialize position of new critter
			((Critter) critterNew).x_coord = Critter.getRandomInt(Params.world_width);
			((Critter) critterNew).y_coord = Critter.getRandomInt(Params.world_height);
			((Critter) critterNew).energy = Params.start_energy;
			
			// add critter to general population
			population.add((Critter) critterNew);
			
		} catch (InstantiationException e) {
			throw new InvalidCritterException("");
		} catch (IllegalAccessException e) {
			throw new InvalidCritterException("");
		} catch (IllegalArgumentException e) {
			throw new InvalidCritterException("");
		} catch (InvocationTargetException e) {
			throw new InvalidCritterException("");
		} catch (NoSuchMethodException e) {
			throw new InvalidCritterException("");
		} catch (SecurityException e) {
			throw new InvalidCritterException("");
		}
	}
	
	/**
	 * Gets a list of critters of a specific type.
	 * @param critter_class_name What kind of Critter is to be listed.  Unqualified class name.
	 * @return List of Critters.
	 * @throws InvalidCritterException Throws this exception if the critter does not exist
	 */
	public static List<Critter> getInstances(String critter_class_name) throws InvalidCritterException {
		List<Critter> result = new java.util.ArrayList<Critter>();
		
		Class<?> crit_class;
		String className = myPackage + "." + critter_class_name;
		try {
			crit_class = Class.forName(className);
		} catch (ClassNotFoundException e) {
			throw new InvalidCritterException("");
		}
		
		
		for (Critter jiminy : population) {
			if (crit_class.isInstance(jiminy)) {
				result.add(jiminy);
			}
		}
		return result;
	}
	

	
	/**
	 * Prints out how many Critters of each type there are on the board.
	 * @param critters List of Critters.
	 */
	public static void runStats(List<Critter> critters) {
		System.out.print("" + critters.size() + " critters as follows -- ");
		java.util.Map<String, Integer> critter_count = new java.util.HashMap<String, Integer>();
		for (Critter crit : critters) {
			String crit_string = crit.toString();
			Integer old_count = critter_count.get(crit_string);
			if (old_count == null) {
				critter_count.put(crit_string,  1);
			} else {
				critter_count.put(crit_string, old_count.intValue() + 1);
			}
		}
		String prefix = "";
		for (String s : critter_count.keySet()) {
			System.out.print(prefix + s + ":" + critter_count.get(s));
			prefix = ", ";
		}
		System.out.println();		
	}
	
	/* the TestCritter class allows some critters to "cheat". If you want to 
	 * create tests of your Critter model, you can create subclasses of this class
	 * and then use the setter functions contained here. 
	 * 
	 * NOTE: you must make sure that the setter functions work with your implementation
	 * of Critter. That means, if you're recording the positions of your critters
	 * using some sort of external grid or some other data structure in addition
	 * to the x_coord and y_coord functions, then you MUST update these setter functions
	 * so that they correctly update your grid/data structure.
	 */
	static abstract class TestCritter extends Critter {
		protected void setEnergy(int new_energy_value) {
			super.energy = new_energy_value;
		}
		
		protected void setX_coord(int new_x_coord) {
			super.x_coord = new_x_coord;
		}
		
		protected void setY_coord(int new_y_coord) {
			super.y_coord = new_y_coord;
		}
		
		protected int getX_coord() {
			return super.x_coord;
		}
		
		protected int getY_coord() {
			return super.y_coord;
		}
		

		/*
		 * This method getPopulation has to be modified by you if you are not using the population
		 * ArrayList that has been provided in the starter code.  In any case, it has to be
		 * implemented for grading tests to work.
		 */
		protected static List<Critter> getPopulation() {
			return population;
		}
		
		/*
		 * This method getBabies has to be modified by you if you are not using the babies
		 * ArrayList that has been provided in the starter code.  In any case, it has to be
		 * implemented for grading tests to work.  Babies should be added to the general population 
		 * at either the beginning OR the end of every timestep.
		 */
		protected static List<Critter> getBabies() {
			return babies;
		}
	}

	/**
	 * Clear the world of all critters, dead and alive
	 */
	public static void clearWorld() {
		population.clear();
		babies.clear();
	}
	/**
	 * Steps the world once.
	 * The order of the actions are as follows:
	 * 1) All criters doTimeStep(which may or may not move)
	 * 2) Conflicting Critters are resolved through fight(s)
	 * 3) Algae are added into the world.
	 * 4) Any offspring are added into the working population.
	 * 5) Rest energy cost is applied
	 * 6) Any dead critters (energy is less than or equal to zero) are removed.
	 */
	public static void worldTimeStep() {
		for(int x = 0; x < population.size(); x++)
		{
			population.get(x).doTimeStep();
		}
		resolveFights();
		for(int i = 0; i < Params.refresh_algae_count;i++)
		{
			Algae a = new Algae();
			a.setEnergy(Params.start_energy);
			int x = Critter.getRandomInt(Params.world_width);
			int y = Critter.getRandomInt(Params.world_height);
			a.setX_coord(x);
			a.setY_coord(y);
			population.add(a);
		}
		population.addAll(babies);
		babies.clear();
		for(int x = 0; x < population.size();x++)
		{
			population.get(x).energy -= Params.rest_energy_cost;
			population.get(x).hasMoved = false;
		}
		Iterator<Critter> iter = population.iterator();
		while(iter.hasNext())
		{
			Critter c = iter.next();
			if(c.energy <= 0)
				iter.remove();
		}

	}
	/**
	 * Displays the world
	 * It displays in a world_width +2 x world_height +2 grid
	 * The +2 is to account for the border that is created.
	 */
	public static void displayWorld() {
		int width = Params.world_width + 2;
		int height = Params.world_height + 2;
		char[][] world = new char[height][width];
		for(int y = 0; y < height; y++)
		{
			if(y ==0 || y ==height -1)
			{
				world[y][0] = '+';
				world[y][width-1] = '+';
			}
			else
			{
				world[y][0] = '|';
				world[y][width-1] = '|';
			}
		}
		for(int x = 1; x < width - 1; x++)
		{
			world[0][x] = '-';
			world[height-1][x] = '-';
		}
		for(int y = 1; y < height -1; y++)
		{
			for(int x = 1; x < width -1; x++)
			{
				world[y][x] = ' ';
			}
		}
		for(int i = 0; i < population.size();i++)
		{
			int x = population.get(i).x_coord +1;
			int y = population.get(i).y_coord +1;
			
			world[y][x] =  population.get(i).toString().charAt(0);
		}
		for(int i = 0; i<height; i++)
		{
			String print = new String(world[i]);
			System.out.println(print);
		}
	}
	/**
	 * A private method that resolve encounters between all critters. 
	 */
	private static void resolveFights()
	{
			for(int x = 0; x < population.size(); x++)
			{
			Critter first = population.get(x);
			if(first.energy <= 0)
				continue;
			int x_c = first.x_coord;
			int y_c = first.y_coord;
			for(int y = x + 1; y < population.size();y++)
			{
				Critter second = population.get(y);
				if(second.energy<=0)
					continue;
				int x_c2 = second.x_coord;
				int y_c2 = second.y_coord;
				if(x_c==x_c2&&y_c==y_c2)
				{
					int firstDamage = 0;
					int secondDamage = 0;
					if(first.fight(second.toString()))
						firstDamage = getRandomInt(first.energy);
					if(second.fight(first.toString()))
						secondDamage = getRandomInt(second.energy);
					if(first.x_coord != x_c || first.y_coord != y_c)
						break;
					else if(second.x_coord != x_c2 || second.y_coord != y_c2)
						continue;
					if(secondDamage > firstDamage)
					{
						second.energy += first.energy /2;
						first.energy = 0;
						break;
					}
					else
					{
						first.energy += second.energy/2;
						second.energy = 0;
					}
				}
			}
		
		}
	}
}
	
