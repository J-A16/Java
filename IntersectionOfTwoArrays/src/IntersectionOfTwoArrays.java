import java.util.ArrayList;
import java.util.Arrays;

class IntersectionOfTwoArrays {
	
	public static void main(String[] args) {
		
		ArrayList<int[]> arrays = new ArrayList<>();
		
		arrays.add(new int[] {1,2,2,1});
		arrays.add(new int[] {2,2});
		
		arrays.add(new int[] {4,9,5});
		arrays.add(new int[] {9,4,9,8,4});
		
		while(!arrays.isEmpty()){
			System.out.println(Arrays.toString(intersect(arrays.remove(0), arrays.remove(0))));
		}
	}
	
    public static int[] intersect(int[] nums1, int[] nums2) {
    	
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        
        if(nums1.length == 0 || nums2.length == 0){
            return new int[0];
        }
        
        if(nums1[0] < nums2[0]){
            int[] holder = nums2;
            nums2 = nums1;
            nums1 = holder;
        }
        
        ArrayList<Integer> intersection = new ArrayList<>();
        
        int i = 0, j = 0;
        
        while(i < nums1.length && j < nums2.length){
            
            if(nums1[i] == nums2[j]) {
                intersection.add(nums1[i]);
                
                i++;
                j++;
                
            } else if(nums1[i] < nums2[j]){
                
                i++;
                
            } else {
                
                j++;
                
            }
            
        }
        
        int[] intersectionArray = new int[intersection.size()];
        
        for(i = 0; i < intersection.size(); i++){
            intersectionArray[i] = intersection.get(i);
        }
        
        return intersectionArray;
    }
    
}