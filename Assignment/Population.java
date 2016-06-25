
public class Population {
	private Children[] children;
	
	public Population(Children[] children){
		this.children = new Children[children.length];
		System.arraycopy(children, 0, this.children, 0, children.length);
	}
	
	public Children get(int index){
		return children[index];
	}
	
	public int size(){
		return this.children.length;
	}
}
