import java.util.ArrayList;
import java.util.Random;

import org.jfree.ui.RefineryUtilities;

import java.util.concurrent.*;

public class TheRealMain {
	private static String function = "Rosenbrock";
	public static void main(String[] args) {
		long time = System.currentTimeMillis();

		Population population = new Population(300);
		
		init(population);
		
		
		Genetic_package genetic = new Genetic_package(population,function);
		genetic.run();
		
		System.out.println("TOTAL TIME : " + (System.currentTimeMillis() - time));
		
	}
	
	public static void init(Population population) {
		Random rand = new Random();
		double randValue;
		
		double cells[] = new double[100];
		boolean full = false;
		while(!full){
			for (int col = 0; col < 100; col++) {
					randValue = (double)(rand.nextInt(1024)) - 512;
					randValue = randValue/100;
					cells[col] = randValue;
			}
			full = !population.add(new Children(cells,function));
		}
	}
	
}
