import java.util.ArrayList;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class Genetic_package {
	
	private Population population;
	private String function;
	
	public Genetic_package(Population population,String function){
		this.population = population;
		this.function = function;
	}
	
	public void run(){
		ExecutorService exec = Executors.newFixedThreadPool(5);
		CompletionService<Boolean> completion = new ExecutorCompletionService<Boolean>(exec);
		int generation = 0;
		
		while(generation < 500){
		
			System.out.println("START");
			Population nextGeneration = new Population(300);
			int counter = 0;
			
			while(counter <= 150){
				completion.submit(new Genetic_Thread(population,function,nextGeneration));
				counter++;
			}
			
			counter = 0; 
			try {
				while(counter <= 150){
					completion.take();
					counter++;
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			generation++;
			System.out.println("GENERATION : " + generation + " : " + population.getMin());
			population = population.replace(nextGeneration);
			
		}
	}
}
