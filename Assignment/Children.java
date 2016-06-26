public class Children {
	private final double[] cells;
	private final String function;

	public Children(double[] cells, String function) {
		this.cells = new double[cells.length];
		System.arraycopy(cells, 0, this.cells, 0, cells.length);
		this.function = function;
	}

	public double[] getCells() {
		return this.cells;
	}

	public double getFitness() {
		double sum = 0;
		int loop = this.cells.length - 1;

		if(this.function.equalsIgnoreCase("RosenBrock")){
			for (int i = 0; i < loop; i++) {
				// RosenBrock Function
				sum += 100
						* Math.pow(
								(this.cells[i + 1])
										- Math.pow(this.cells[i], 2), 2)
						+ Math.pow((this.cells[i]) - 1, 2);
			}

		}
		else if (this.function.equalsIgnoreCase("Scheweful"))
		{
			for (int i = 0; i < loop; i++) {
				sum += this.cells[i]
						* (Math.sin(Math.sqrt(Math.abs(this.cells[i]))));

			}
			sum = (418.982887 * this.cells.length)-sum;

		}
		else if(this.function.equalsIgnoreCase("Rastrain")){
			for (int i = 0; i < loop; i++) {
				sum += Math.pow(this.cells[i], 2) - 10
						* Math.cos(2 * 3.142 * this.cells[i]);
			}
			sum = 10*cells.length + sum;
		}else{
			System.out.println("ERROR");
		}

		return sum;
	}

	public int size() {
		return this.cells.length;
	}

}
