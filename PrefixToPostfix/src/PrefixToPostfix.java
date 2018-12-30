import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;

public class PrefixToPostfix {
	public static void main(String[] args) {
		String[] prefixes = {"+23","-+317","+2*54","++1*532"};
		
		for(int i = 0; i < prefixes.length; i++){
			System.out.println(prefixes[i]);
			}
		
		System.out.println();
		
		String[] postfixes = prefixToPostfix(prefixes);
		
		System.out.println();
		
		for(int i = 0; i < postfixes.length; i++){
		System.out.println(postfixes[i]);
		}
	}

	/*
	 * Complete the function below.
	 */
	static String[] prefixToPostfix(String[] prefixes) {

		HashMap<Character, Integer> precedence = new HashMap<Character, Integer>();
		precedence.put('-', 1);
		precedence.put('+', 2);
		precedence.put('*', 3);
		precedence.put('/', 4);
		
		String infix;
		LinkedList<Character> nums = new LinkedList<Character>();
		Stack<Character> opsStack = new Stack<Character>();

		for (int i = 0; i < prefixes.length; ++i) {
			infix = "";
			for (int j = 0; j < prefixes[i].length(); ++j) {
				if (Character.isDigit(prefixes[i].charAt(j))) {
					if(!nums.isEmpty()){
						infix += nums.remove();
						infix += opsStack.pop();
						infix += prefixes[i].charAt(j);
					}else{
						nums.add(prefixes[i].charAt(j));
					}
				} else {
					if (!opsStack.isEmpty() && precedence.get(prefixes[i].charAt(j)) > precedence.get(opsStack.peek())){
						if(!nums.isEmpty()){
						infix += nums.remove();
						infix += opsStack.pop();
						}
					}
					opsStack.add(prefixes[i].charAt(j));
				}
			}
			boolean digitNext;
			if(Character.isDigit(infix.charAt(infix.length() - 1))){
				digitNext = false;
			}else{
				digitNext = true;
			}

			while (!nums.isEmpty() || !opsStack.isEmpty()) {
				if (digitNext) {
					infix += nums.remove();
					digitNext = false;
				} else {
					infix += opsStack.pop();
					digitNext = true;
				}
			}
			
			System.out.println(infix);
			
			String postfix = "";

			for (int j = 0; j < infix.length(); ++j) {
				if (Character.isDigit(infix.charAt(j))) {
					postfix += infix.charAt(j);
				} else {
					if (!opsStack.isEmpty() && precedence.get(infix.charAt(j)) > precedence.get(opsStack.peek())) {
						opsStack.push(infix.charAt(j));
					} else {
						if (opsStack.isEmpty()) {
							opsStack.push(infix.charAt(j));
						} else {
							postfix += opsStack.pop();
							opsStack.push(infix.charAt(j));
						}
					}
				}
			}
			while (!opsStack.isEmpty()) {
				postfix += opsStack.pop();
			}
			prefixes[i] = postfix;
		}
		return prefixes;
	}

}