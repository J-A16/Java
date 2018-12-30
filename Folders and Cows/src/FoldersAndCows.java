import java.util.*;

/*
 * Dropbox interview question
 * 
 * Check input.txt in this package
 * 
 * 3
 * 2 1
 * 1 1 0
 * 2 1 1
 * 3 3 0 1 2
 * 2
 * 1 2
 * 1 3
 * 
 * output: 2
 * 
 */

public class FoldersAndCows {
	public static void main(String args[]) throws Exception {
		/*
		 * Enter your code here. Read input from STDIN. Print output to STDOUT
		 */

		Scanner cin = new Scanner(System.in);

		final int NUMBER_OF_COWS = cin.nextInt();

		final int SHARED_FOLDERS = cin.nextInt();

		final int CONFIDENTIAL_FOLDERS = cin.nextInt();

		ArrayList<Integer> folderIDS = new ArrayList<Integer>();

		HashMap<Integer, Node> Nodes = new HashMap<Integer, Node>();

		for (int i = 0; i < SHARED_FOLDERS; i++) {
			int folderID = cin.nextInt();
			folderIDS.add(folderID);
			int numCowsWithAccess = cin.nextInt();
			int[] cowsWithAccess = new int[numCowsWithAccess];
			for (int j = 0; j < numCowsWithAccess; j++) {
				cowsWithAccess[j] = cin.nextInt();
			}

			Node thisNode = new Node();

			thisNode.cowsWithAccess = cowsWithAccess;

			Nodes.put(folderID, thisNode);
		}

		for (int i = 0; i < CONFIDENTIAL_FOLDERS; i++) {
			int folderID = cin.nextInt();
			folderIDS.add(folderID);
			int numCowsWithAccess = cin.nextInt();
			int[] cowsWithAccess = new int[numCowsWithAccess];
			for (int j = 0; j < numCowsWithAccess; j++) {
				cowsWithAccess[j] = cin.nextInt();
			}

			Node thisNode = new Node();

			thisNode.confidential = true;

			thisNode.cowsWithAccess = cowsWithAccess;

			Nodes.put(folderID, thisNode);
		}

		final int NUMBER_OF_BRANCHES = cin.nextInt();

		HashSet<Integer> parents = new HashSet<Integer>();

		for (int i = 0; i < NUMBER_OF_BRANCHES; i++) {
			int parent = cin.nextInt();
			parents.add(parent);
			int child = cin.nextInt();

			Node parentNode = Nodes.get(parent);
			Node childNode = Nodes.get(child);

			// parentNode.children.put(child, childNode);

			childNode.parent = parentNode;

		}

		cin.close();

		
		ArrayList<Integer> leaves = new ArrayList<Integer>(folderIDS);
		
		leaves.removeAll(parents);

		System.out.println(parents);
		System.out.println(leaves);
		
		HashSet<Integer> allCowsMissingAccess = new HashSet<Integer>();
		
		for(int i = 0; i < leaves.size(); i++){
			ArrayList<Integer> cowsMissingAccessToNode =
					findCowsMissingAccess(NUMBER_OF_COWS,Nodes.get(leaves.get(i)));
			
			for(int j = 0; j < cowsMissingAccessToNode.size(); j++){
				allCowsMissingAccess.add(cowsMissingAccessToNode.get(j));
			}
		}
		
		System.out.println(allCowsMissingAccess);

	}
	
	static ArrayList<Integer> findCowsWithAccess(Node node){
		
		ArrayList<Integer> cowsWithLeafAccess = new ArrayList<Integer>();
		
		if(!node.confidential && node.parent != null){
			cowsWithLeafAccess = findCowsWithAccess(node.parent);
		}
		
		for(int i = 0; i < node.cowsWithAccess.length; i++){
			cowsWithLeafAccess.add(node.cowsWithAccess[i]);
		}
		
		
		return cowsWithLeafAccess;
	}
	
	static ArrayList<Integer> findCowsMissingAccess(int numCows, Node node){
		
		ArrayList<Integer> cowsMissingAccess = new ArrayList<Integer>();
		
		for(int i = 0; i < numCows; i++){
			cowsMissingAccess.add(i);
		}
		
		cowsMissingAccess.removeAll(findCowsWithAccess(node));
		
		return cowsMissingAccess;
	}
	
}

class Node {
	Node parent;

	boolean confidential;

	int[] cowsWithAccess;

	// HashMap<Integer, Node> children = new HashMap<Integer, Node>();
}