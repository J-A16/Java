import java.util.*;

public class subStringsKDistFinder {
	
	// METHOD SIGNATURE BEGINS, THIS METHOD IS REQUIRED
    public List<String> subStringsKDist(String inputStr, int num)
	{
        // WRITE YOUR CODE HERE
        
        ArrayList<String> substrings = new ArrayList<>();
        HashSet<String> alreadyFoundSubstrings = new HashSet<>();
        for(int i = 0; i + num <= inputStr.length(); i++){
               String substring = inputStr.substring(i, i + num);
               
               if(lettersDistinct(substring) &&
               !alreadyFoundSubstrings.contains(substring)){
                   substrings.add(substring);
                   alreadyFoundSubstrings.add(substring);
               }
        }
        
        return substrings;
    }
    // METHOD SIGNATURE ENDS
    
    boolean lettersDistinct(String string){
        HashSet<Character> letters = new HashSet<>();
        
        for(int i = 0; i < string.length(); i++){
            if(!letters.contains(string.charAt(i))){
                letters.add(string.charAt(i));
            } else{
                return false;
            }
        }
        
        return true;
    }
    
}
