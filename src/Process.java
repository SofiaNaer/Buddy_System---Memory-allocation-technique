
public class Process {
	private int id;
	private int size;
	boolean isAlloc = false;

	
	public Process (int id, int size) {
		this.id = id;
		this.size = size;
	}
	
	public int getSize () {
		return size;
	}
	
	public int getId () {
		return id;
	}

}
