import java.util.ArrayList;

public class Buddy {
	Node root;
	int size;
	int internal_freg = 0;
	int  extrernal_frag = 0;
	


	public Buddy (Node root) {
		this.root = root;
		this.size = root.size;
}
	
	public  void allocate (Process p, ArrayList<Integer> run) {
		if (p.getSize() > this.size) {   
			System.out.println("Process is bigger thar memory");
			return;
		}
		if (p.getSize() <= this.size && p.getSize() > this.size/2) { //if process need all memory but we have one process in the root node
			if (root.isAllocated==true) {
				System.out.println("Not enought memory to allocate, overall flagmentation: "+ extrernal_frag);
				return;
			}
			else{                                // allocation in root node (one big process)
				root.allocated = p.getSize();
				root.freg = this.size -p.getSize();
				internal_freg += root.freg;
				root.isAllocated = true;
				root.possibleToAll = false;
				run.add(p.getId());
				run.add(0);
				run.add(this.size-1);
				
				System.out.println("Proccess allocted");
				
				return;
			}	
		}
			
			Node n =root;
			allocateSmallProcess(n, p,  run); //if process need new smaller node 
			if (!p.isAlloc )
				System.out.println("Not enought memory to allocate, overall flagmentation: "+ extrernal_frag);
			
			
			
			
				
				
			
}
	
		public void allocateSmallProcess (Node n, Process p,  ArrayList<Integer> run) {
			if (p.isAlloc == true) {
				return;
			}
			if (n == null)
				return;
			int temp = n.size;
			if (p.getSize() <=temp/2 ) {
				
				if (n.left == null && n.right == null && n.possibleToAll ==true) {
					n.possibleToAll = false; // if node have child we can't allocate in this node
					Node l = new Node(temp/2, n.start, n.start +temp/2 - 1); //create now node
					n.left = l;
					Node r = new Node (temp/2, n.start +temp/2, n.end);
					n.right = r;
					allocateSmallProcess(n.left, p,  run);	
					
		}
				
				else {
					allocateSmallProcess(n.left, p,  run);
					allocateSmallProcess(n.right, p,  run);
					
				}
				
			}	
				if (n.isAllocated == false && p.isAlloc == false && n.possibleToAll==true) {
					n.allocated = n.size;
					n.isAllocated = true;
					n.freg = n.size - p.getSize();
					this.internal_freg +=n.freg;
					p.isAlloc = true;
					n.possibleToAll = false;
					n.proccessId = p.getId();
					n.proccessSize = p.getSize();
					run.add(p.getId());
					run.add(n.start);
					run.add(n.end);
					System.out.println("Process allocated");
					
					
					
					
					
				}
				
				return;	
	}
		public void releaseMemory (int id, Node root,  ArrayList<Integer> run) {
			if (root == null)
				return;
			if (root.proccessId == id) {
				root.possibleToAll = true;
				root.allocated = 0;
				root.isAllocated = false;
				this.internal_freg -= root.freg;
				System.out.println("Proccess "+ id + " deleted");
				updateRunningproccesses(run, id);
				merge(this.root);
				
				
				return;
				
			}
			else {
				releaseMemory(id, root.left,  run);
				releaseMemory(id, root.right,  run);
			}
					
		}
		
		public void merge (Node root) {
			
			if (root == null) {
				return ;
			}
			if (root.left != null && root.right != null) {
				if (root.left.possibleToAll ==true && root.right.possibleToAll ==true) {
					root.left = null;
					root.right = null;
					root.possibleToAll = true;
					merge(this.root);
					
			}
			merge(root.left);
			
				
			}
		}
		
		public void enternalFragmrntation (Process p, ArrayList<Integer> arr) {
			int sum = 0;
			int max =0;
			
			for (int i = 0; i< arr.size(); i+=3) {
				sum+=arr.get(i);
				if (arr.get(i)> max) {
					max =arr.get(i);
				}
			}
			this.extrernal_frag = sum - max;
			
		}
		
		public void updateAvaivable (Node root, ArrayList<Integer> arr){
			
			if(root == null) {
				return;
			}
			if (root.possibleToAll == true) {
				arr.add(root.size); 
				arr.add(root.start);
				arr.add(root.end);
								
			}
			updateAvaivable(root.left, arr );
			updateAvaivable(root.right, arr);
		}
		
		public void updateRunningproccesses (ArrayList<Integer> run, int id) {
			for (int i = 0; i< run.size(); i+=3) {
				if (run.get(i)==id) {
					run.remove(i);
					run.remove(i);
					run.remove(i);
					
					
					break;
				}
				
			}
		}
		
}
