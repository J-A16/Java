// you can also use imports, for example:
// import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");

class Solution {
    public int solution(String A, String B) {
        // write your code in Java SE 8
        
        if(B.length() == 0)
            return 0;
        
        if(A.length() == 0)
            return -1;
            
        String testingString = A;
        int repeats = 1;
        
        for(int i = 0; i < A.length(); i++){
               if(B.charAt(0) == A.charAt(i)){
                    for(int j = i + 1; j < testingString.length(); j++){
                        if(B.charAt(j - i) != testingString.charAt(j)){
                            break;   
                        }
                        
                        if(j - i == B.length() - 1)
                            return repeats;
                        
                        if(j == testingString.length() - 1){
                            testingString += A;
                            repeats++;
                        }
                    }
               }
        }
        
        return -1;
    }
}