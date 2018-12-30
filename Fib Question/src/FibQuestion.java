import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class FibQuestion {
  /**
   * Iterate through each line of input.
   */
  public static void main(String[] args) throws IOException {
    InputStreamReader reader = new InputStreamReader(System.in, StandardCharsets.UTF_8);
    BufferedReader in = new BufferedReader(reader);
    String line;
    
    int position = Integer.parseInt(in.readLine());
    
    int repititions = findFib(position);
    
    for(int i = 0; i < repititions; i++){
      System.out.print((char) ('a' + (position - 1)));
    }
    
  }
  static int findFib(int position){
    
    ArrayList<Integer> positions = new ArrayList<Integer>();
    
    for(int i = 0; i < position; i++){
      if (i == 0 || i == 1){
        positions.add(1);
      } else {
        positions.add(positions.get(i-2)+positions.get(i-1));
      }
    }
    
    return positions.get(positions.size()-1);
  }
}

