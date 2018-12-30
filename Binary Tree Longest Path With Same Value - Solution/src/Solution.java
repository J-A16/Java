import java.util.ArrayList;

/*

We consider an undirected tree with N nodes, numbered from 1 to N,
Additionally, each node also has a label attached to it and the
label is an integer value. Note that different nodes can have identical labels.
You need to write a function , that , given a zero-indexed array A
of length N, where A[J] is the label value of the (J + 1)-th node
in the tree, and a zero-indexed array E of length K = (N - 1) * 2
in which the edges of the tree are described (for every 0 <= j <= N -2
values E[2 * J] and E[2 * J + 1] represents and edge connecting node E[2 * J]
		with node E[2 * J + 1])， returns the length of the longest path such
that all the nodes on that path have the same label. Then length of a path
if defined as the number of edges in that path.

Notice
Assume that: 1 <= N <= 1000

Example

Give A = [1, 1, 1 ,2, 2] and E = [1, 2, 1, 3, 2, 4, 2, 5]
described tree appears as follows:

                   1 （value = 1）
                 /   \
    (value = 1) 2     3 (value = 1)
               /  \
 (value = 2)  4     5 (value = 2)

and your function should return 2, because the longest path
(in which all nodes have the save value of 1) is (2 -> 1 -> 3).
The number of edges on this path is 2, thus, that is the answer.

*/

public class Solution {
    /*
     * @param : as indicated in the description
     * @param : as indicated in the description
     * @return: Return the number of edges on the longest path with same value.
     */
     
     int longestPathLength;
     
    public int LongestPathWithSameValue(int[] A, int[] E) {
        // write your code here
        Node root = BuildTree(A, E);
        
        longestPathLength = 0;
        pathLength(root);
        return longestPathLength;
    }
    
    public int pathLength(Node node){
        if (node == null) return 0;
        int left = pathLength(node.leftChild);
        int right = pathLength(node.rightChild);
        int pathLeft = 0, pathRight = 0;
        if (node.leftChild != null && node.leftChild.value == node.value){
            pathLeft += left + 1;
        }
        if (node.rightChild != null && node.rightChild.value == node.value){
            pathRight += right + 1;
        }
        longestPathLength = Math.max(longestPathLength, pathLeft + pathRight);
        return Math.max(pathLeft, pathRight);
    }
    
    public Node BuildTree(int[] A, int[] E) {
        ArrayList<Node> tree = new ArrayList<Node>();
        
        for(int i = 0; i < A.length; i++){
            tree.add(new Node(A[i]));
        }
        for(int i = 0; i < E.length; i = i + 2){
            if(E[i]*2 == E[i + 1]){
                tree.get(E[i] - 1).leftChild = tree.get(E[i + 1] - 1);
                tree.get(E[i + 1] - 1).parent = tree.get(E[i] - 1);
            }else{
                tree.get(E[i] - 1).rightChild = tree.get(E[i + 1] - 1);
                tree.get(E[i + 1] - 1).parent = tree.get(E[i] - 1);
            }
        }
        
        return tree.get(0);
    }
    
    class Node{
         int value;
         Node parent, leftChild, rightChild;
         
         Node(int val){
             value = val;
         }
     }
};