import java.util.ArrayList;
import java.util.Scanner;

public class Runner {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Enter memory size: ");
		Scanner sc = new Scanner(System.in);
		int memory_size = sc.nextInt();
		Node root = new Node(memory_size, 0, memory_size-1); 
		ArrayList<Integer> avalableBlocks = new ArrayList<Integer>();
		ArrayList<Integer> runningProccesses = new ArrayList<Integer>();
		Buddy b = new Buddy(root);
		int move = 0;
		
		while (move !=4) {
			System.out.println("Enter action: " );
			move = sc.nextInt();
			if (move == 4)
				break;
			if(move ==1) {
				System.out.println("Enter proccess id");
				int id = sc.nextInt();
				System.out.println("Enter proccess size: ");
				int size = sc.nextInt();
				Process p = new Process(id, size);
				b.allocate(p,  runningProccesses);
				b.enternalFragmrntation(p, avalableBlocks);
				avalableBlocks.clear();
				b.updateAvaivable(root, avalableBlocks);
			}
			if (move ==2) {
				System.out.println("Enter proccess id ");
				int id = sc.nextInt();
				b.releaseMemory(id, b.root,  runningProccesses);
				avalableBlocks.clear();
				b.updateAvaivable(root, avalableBlocks);
				
				
			}
			if (move == 3) {
				System.out.println("Running proccesses: ");
				for (int i =0; i< runningProccesses.size(); i+=3) {
					System.out.println("Proccess id:"+ runningProccesses.get(i)+", allocated block:{"+ runningProccesses.get(i+1)+
							", "+ runningProccesses.get(i+2)+"}");
				}
				System.out.println("Avalable blocks");
				for (int j = 1; j < avalableBlocks.size(); j+=3) {
					System.out.println("{"+ avalableBlocks.get(j)+", "+avalableBlocks.get(j+1)+"}");
					
				}
				System.out.println("Internal Fragmintation: "+ b.internal_freg);
			}
		}
	
			
			
			
			
		
		}
	
	

	}
	
	
		
	


