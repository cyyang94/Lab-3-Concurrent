import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.*;

public class Genetic_Thread implements Callable<Double> {
	private Population population;
	private final int populationSize;
	private final String function;

	public Genetic_Thread(Population population,String function) {
		this.population = population;
		this.populationSize = population.size();
		this.function = function;
	}

	public Double call() {
		int generation = 0;
		int parent1 = this.selector(-1, 3);
		int parent2 = this.selector(parent1, 3);

		double newCells[][] = new double[2][this.population.get(parent1).size()];
		newCells = crossover(parent1, parent2);
		
		Children[] offSpring = new Children[2];
		System.arraycopy(this.mutate(newCells), 0, offSpring, 0, 2);
		

		return 0.1;
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

		// ---------------UNIT TEST--------------------------
		System.out.println("UNIT TEST FOR selection");
		for (int i = 0; i < tournamentSize; i++) {
			System.out.println(" candidate " + candidates[i] + " fitness : "
					+ this.population.get(candidates[i]).getFitness());
		}
		System.out.println("SELECTED " + fittest);
		// ---------------UNIT TEST--------------------------

		return fittest;
	}

	/*
	 * random a cut off point crossover return child
	 */
	public double[][] crossover(int parent1, int parent2) {
		Random rand = new Random();
		int numberOfCells = this.population.get(parent1).size();
		int cutOffPoint = rand.nextInt((numberOfCells - 1) / 2) + 1;

		int cutOffPoint2 = rand.nextInt(numberOfCells - cutOffPoint)
				+ cutOffPoint;
		double[][] child = new double[2][numberOfCells];

		System.arraycopy(this.population.get(parent1), 0, child[0], 0,
				numberOfCells);
		System.arraycopy(this.population.get(parent2), 0, child[1], 0,
				numberOfCells);

		if (rand.nextInt(10) <= 7) // 70% chance of crossover
		{
			// 2 point crossover
			/*
			 * for (int i = cutOffPoint; i < cutOffPoint2; i++) { child[0][i] =
			 * this.population[parent2][i]; child[1][i] =
			 * this.population[parent1][i]; }
			 */
			// uniform crossover

			double temp = 0;
			for (int i = 0; i < numberOfCells; i++) {
				if (rand.nextInt(10) < 5) {
					temp = child[0][i];
					child[0][i] = child[1][i];
					child[1][i] = temp;
				}
			}

		}
		return child;
	}

	/*
	 * loop through every cell with a 1% chance of mutating child
	 */
	public Children[] mutate(double[][] cells) {
		Random rand = new Random();
		int numberOfCells = cells.length;

		for (int i = 0; i < numberOfCells; i++) {
			int counter = 0;
			while (counter < 2) {
				if (rand.nextInt(100) < 1) {
					double RandomValue = rand.nextInt(1000) - 500;
					RandomValue = RandomValue / 1000;
					double newNumber = cells[counter][i] + (RandomValue);
					if (newNumber > 500) {
						newNumber = 500;
					} else if (newNumber < -500) {
						newNumber = -500;
					}
					cells[counter][i] = newNumber;
				}
				counter++;
			}
		}
		
		Children[] offSpring = {
				new Children(cells[0],this.function),
				new Children(cells[1],this.function)
		};
		 
		return offSpring;
	}

}
