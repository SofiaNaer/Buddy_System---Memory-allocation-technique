
public class Node {
	int size;
	Node left, right; 
	int allocated = 0;
	int freg = 0;  //Internal fregmentation for each Node
	boolean isAllocated = false;
	boolean possibleToAll = true;
	int proccessId = 0;
	int proccessSize = 0;
	int start;
	int end;
	
	public Node (int size, int start, int end) {
		this.size = size;
		this.left = null;
		this.left = null;
		this.start = start;
		this.end = end;
	}
}

