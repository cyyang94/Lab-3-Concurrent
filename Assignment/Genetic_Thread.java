import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.*;

public class Genetic_Thread implements Callable<Boolean> {
	private Population population;
	private final int populationSize;
	private final String function;
	private Population newGeneration;

	public Genetic_Thread(Population population, String function, Population newGeneration) {
		this.population = population;
		this.populationSize = population.size();
		this.function = function;
		this.newGeneration = newGeneration;
	}

	public Boolean call() throws Exception{
		int generation = 0;
		int parent1 = this.selector(-1, 3);
		int parent2 = this.selector(parent1, 3);

		double newCells[][] = new double[2][this.population.get(parent1).size()];
		
		newCells = this.crossover(parent1,parent2);

		this.newGeneration.add(new Children(this.mutate(newCells[0]),this.function));
		this.newGeneration.add(new Children(this.mutate(newCells[0]),this.function));
		
		return true;
	}

	/*
	 * random first parent and second parent check if duplicated compare fitness
	 * return index of parent
	 */
	public int selector(int exclude, int tournamentSize) {
		Random rand = new Random();
		int[] candidates = new int[tournamentSize];
		int fittest = 0;

		for (int i = 0; i < tournamentSize; i++) {
			candidates[i] = rand.nextInt(populationSize);
			if (this.population.get(candidates[i]).getFitness() < this.population
					.get(fittest).getFitness()) {
				fittest = candidates[i];
			}
			// -- prevent selection of selected parent or candidates
			if (candidates[i] == exclude) {
				i--;
			}

			for (int j = 0; j < i; j++) {
				if (candidates[i] == candidates[j]) {
					i--;
				}
			}
		}
		return fittest;
	}

	/*
	 * random a cut off point crossover return child
	 */
	public double[][] crossover(int parent1, int parent2) {
		Random rand = new Random();
		int numberOfCells = this.population.get(parent1).size();
		double[][] child = new double[2][numberOfCells];

		System.arraycopy(this.population.get(parent1).getCells(), 0, child[0], 0,
				numberOfCells);
		System.arraycopy(this.population.get(parent2).getCells(), 0, child[1], 0,
				numberOfCells);

		if (rand.nextInt(10) < 7) // 70% chance of crossover
		{
			double temp = 0;
			for (int i = 0; i < numberOfCells; i++) {
				if (rand.nextInt(10) < 5) {
					temp = child[0][i];
					child[0][i] = child[1][i];
					child[1][i] = temp;
				}
			}
			
			//shuffle
			for (int col = numberOfCells - 1; col > 0; col--) {
				int index = rand.nextInt(numberOfCells);
				int row = 0;
				while (row < 2) {
					// Simple swap
					temp = child[row][index];
					child[row][index] = child[row][col];
					child[row][col] = temp;
					row++;
				}
			}
			
		}
		return child;
	}

	/*
	 * loop through every cell with a 1% chance of mutating child
	 */
	public double[] mutate(double[] cells) {
		Random rand = new Random();
		int numberOfCells = cells.length;

		for (int i = 0; i < numberOfCells; i++) {
				if (rand.nextInt(100) < 1) {
					double RandomValue = rand.nextInt(1000) - 500;
					RandomValue = RandomValue / 1000;
					double newNumber = cells[i] + (RandomValue);
					if (newNumber > 500) {
						newNumber = 500;
					} 
					else if (newNumber < -500) {
						newNumber = -500;
					}
					cells[i] = newNumber;
				}
			}
		
		return cells;
	}
}
