import java.util.Arrays;
import java.util.Random;

import org.jfree.ui.RefineryUtilities;

public class Main {
	public static void main(String[] args) {
		long time = System.currentTimeMillis();
		double[][] data = new double[300][100];
		init(data);
		Genetic ga =  new Genetic(data);
		
		Thread t1 = new Thread(ga);
		t1.start();
		
		try{
			t1.join();
			
			System.out.println("time = " + (System.currentTimeMillis()-time));
		}catch(Exception e){
			
		}
		LineChart_AWT chart = new LineChart_AWT(
			      "Genetic Algorithm" ,
			      "Genetic Algorithm Fitness",ga.getArrayOfMin());

			      chart.pack( );
			      RefineryUtilities.centerFrameOnScreen( chart );
			      chart.setVisible( true );

			      
	}
	
	public static void init(double[][] data) {
		Random rand = new Random();
		double randValue;
		for (int row = 0; row < data.length; row++) {
			for (int column = 0; column < data[0].length; column++) {
				randValue = (double)(rand.nextInt(1024)) - 512;
				randValue = randValue/100;
				data[row][column] = randValue;

			}

		}
	}
	
	public static void print(double[][] data){
		for (int row = 0; row < data.length; row++) {
			for (int column = 0; column < data[1].length; column++) {
				System.out.print(data[row][column] + " ");
			}
			System.out.println();
		}
	}

}
