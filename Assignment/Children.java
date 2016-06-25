
public class Children {
	private final double[] cells;
	private final String function;
	
	public Children(double[] cells, String function){
		this.cells = new double[cells.length];
		System.arraycopy(cells, 0, this.cells, 0, cells.length);
		this.function = function;
	}
	
	public double[] getCells(){
		return this.cells;
	}
	
	public double getFitness(){
		double sum = 0;
		int loop = this.cells.length - 1;
		for (int i = 0; i < loop; i++) {
			// RosenBrock Function
			sum += 100
					* Math.pow(
							(this.cells[i + 1])
									- Math.pow(this.cells[i], 2), 2)
					+ Math.pow((this.cells[i]) - 1, 2);
		}
		return sum;
	}
	
	public int size(){
		return this.cells.length;
	}
	
	public Children mutate(double lowerBound, double upperBound){
		
		return new Children(new double[4],"ad");
	}
	
	
}
