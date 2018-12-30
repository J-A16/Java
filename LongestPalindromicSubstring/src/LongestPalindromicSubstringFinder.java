public class LongestPalindromicSubstringFinder {
	
	//METHOD SIGNATURE BEGINS, THIS METHOD IS REQUIRED
    String longestPalindromicSubstring(String inputStr)
    {
        // WRITE YOUR CODE HERE
        
        int longestPalindromicSubstringLength = 0;
        
        String longestPalindromicSubstring = "";
        
        for(int i = 0; i < inputStr.length(); i++){
            for(int j = i + 1; j <= inputStr.length(); j++){
                String substring = inputStr.substring(i, j);
                if(isPalindrome(substring)){
                    
                    if(substring.length() >
                    longestPalindromicSubstringLength){
                        longestPalindromicSubstringLength = substring.length();
                        longestPalindromicSubstring = substring;
                        
                    }
                    
                }
            }
        }
        
        return longestPalindromicSubstring;
    }
    // METHOD SIGNATURE ENDS
    
    boolean isPalindrome(String string){
        boolean palindrome = true;
        
        int front = 0, back = string.length() - 1;
        
        while(front < back){
			
			if(string.charAt(front) != string.charAt(back)){
				palindrome = false;
			}
			front++;
			back--;
		}
		
		return palindrome;
    }
}
