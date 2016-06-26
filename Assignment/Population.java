import java.util.concurrent.atomic.*;

public class Population {
	private Children[] children;
	private int cap;
	private AtomicInteger counter = new AtomicInteger(0);
	
	public Population(int cap){
		this.cap = cap;
		this.children = new Children[cap];
		counter = new AtomicInteger(0);
	}
	
	public Population(Children[] children){
		this.children = new Children[children.length];
		System.arraycopy(children, 0, this.children, 0, children.length);
		this.cap = children.length;
	}
	
	public Children get(int index){
		return children[index];
	}
	
	public int size(){
		return this.children.length;
	}
	
	public Children[] getChildren(){
		return children;
	}
	
	public synchronized boolean add(Children child){
		if(counter.get() < cap){
			children[counter.get()] = child;
			counter.incrementAndGet();
			return true;
		}
		return false;
	}
	
	public synchronized double getMin(){
		double min = Double.MAX_VALUE;
		for(int i = 0;i < this.cap;i++){
			if(min > this.children[i].getFitness()){
				min = this.children[i].getFitness();
			}
		}
		
		return min;
	}
	
	public Population replace(Population newGen){
		return new Population(newGen.getChildren());
	}
	
}
