import java.io.IOException;
import java.util.Scanner;

public class AddReverse {
  /**
   * Iterate through each line of input.
   */
  public static void main(String[] args) throws IOException {
    Scanner in = new Scanner(System.in);
    String line;
    while ((line = in.nextLine()) != null) {
      long number = Long.parseLong(line);
      
      int additions = 0;
      
      boolean firstPass = true;
      
      while(containsEven(number) || firstPass){
        
        firstPass = false;
        
        number = addReverse(number);
        
        additions++;
        
      }
      
      System.out.println(additions + " " + number);
    }
    
    in.close();
  }
  
  static long addReverse(long number){
    String[] strNumArray = Long.toString(number).split("");
    
    for (int i = 0; i < strNumArray.length / 2; i++) {
      
			String holder = strNumArray[i];
			strNumArray[i] = strNumArray[strNumArray.length - (i + 1)];
			strNumArray[strNumArray.length - (i + 1)] = holder;
			
		}
    
    return number + Long.parseLong(String.join("", strNumArray));
  }
  
  static boolean containsEven(long number){
    String strNum = Long.toString(number);
    
    
    String[] evenNumsStringArray = {"0", "2", "4", "6", "8"};
		
		for (int i = 0; i < evenNumsStringArray.length; i++) {
			if(strNum.contains(evenNumsStringArray[i])){
			  return true;
			}
		}
    
    return false;
  }
  
}
