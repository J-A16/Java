import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.SplittableRandom;
import java.util.Stack;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

class Test {
	void print(String x) {

		x = x + " How are you!?";

		System.out.println(x);

	}
}

public class MyFirstApp {

	public static void main(String[] args) {

		String tester = "Hello!";
		Test func = new Test();
		
		String copy = tester;
		
		System.out.println(copy);
		
		func.print(tester);

		System.out.println(tester);

		ArrayList<Integer> list = new ArrayList<Integer>(Arrays.asList(2,2,3,2,4));

		list.add(1);

		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
			// System.out.println(list.indexOf(list.get(i)));
		}
		
		System.out.println();
		
		Collections.sort(list);

		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
			// System.out.println(list.indexOf(list.get(i)));
		}

		System.out.println();

		for (int i = 0; i < list.size(); i++) {
			System.out.print(list.get(i));
		}

		int[] anArray = new int[10];

		for (int i = 0; i < anArray.length; i++) {
			anArray[i] = (int) Math.pow(i, 2);
		}

		for (int i = 0; i < anArray.length; i++) {
			System.out.println(i);
			System.out.println(anArray[i]);
			System.out.println(i);
		}

		String H = "Jackson";

		char[] charArray = H.toCharArray();
		System.out.println(charArray.length);
		System.out.println(charArray[1]);

		System.out.println();

		String SpaceSeparatedStrings = "Jer emy Alex andre";

		String[] strings = SpaceSeparatedStrings.split("");
		
		ArrayList<String> sortTest = new ArrayList<String>();
		
		for (String string : strings) {
			sortTest.add(string);
		}
		
		System.out.println(sortTest);
		
		String[] sortTestArray = new String[sortTest.size()];
		
		sortTest.toArray(sortTestArray);
		
		Arrays.sort(sortTestArray);
		
		System.out.println(Arrays.toString(sortTestArray));
		
		Arrays.sort(strings, Collections.reverseOrder());
		
		for (int i = 0; i < strings.length / 2; i++) {
			String holder = strings[i];
			strings[i] = strings[strings.length - (i + 1)];
			strings[strings.length - (i + 1)] = holder;
		}

		for (String string : strings) {
			System.out.println(string);
		}

		System.out.println(Arrays.toString(strings));

		System.out.println(String.join("", strings));

		String[] evenNumsStringArray = { "e", "w" };

		for (int i = 0; i < evenNumsStringArray.length; i++) {
			System.out.println(String.join("", strings).contains(evenNumsStringArray[i]));
		}

		System.out.println();

		Stack<Character> holder = new Stack<Character>();

		holder.push('g');

		System.out.println(holder);

		HashMap<Character, Integer> precedence = new HashMap<Character, Integer>();

		precedence.put('2', 4);

		System.out.println(precedence.get('2'));

		
		// Max heap
		PriorityQueue<Integer> ints = new PriorityQueue<Integer>(Collections.reverseOrder());
		
		
		
		ints.add(92);
		ints.add(23);
		ints.add(42);
		ints.add(2);
		ints.add(5);
		ints.remove(92);

		System.out.println(ints.peek());

		System.out.println(ints.contains(42));

		System.out.println(ints.remove());
		
		System.out.println(ints.remove());
		
		System.out.println(ints.remove());
		
		System.out.println(ints.remove());
		
		// initializing unsorted long array
		long longArr[] = { 12, 15, 56, 46464, 3342, 232, 3445 };

		// sorting array
		Arrays.sort(longArr);

		// let us print all the elements available in list
		System.out.println("The sorted long array is:");
		for (long number : longArr) {
			System.out.println("Number = " + number);
		}

		// entering the value to be searched
		// returns index of the search key, if it is contained in the array;
		// otherwise, (-(insertion point index) - 1)
		long searchVal = 232;

		// entering range of index
		int retVal = Arrays.binarySearch(longArr, searchVal);

		System.out.println("The index of element 232 is : " + retVal);

		Math.log(2);

		ArrayList<HashSet<Integer>> clusters = new ArrayList<HashSet<Integer>>();

		clusters.add(new HashSet<Integer>());

		System.out.println(clusters);

		int cores = Runtime.getRuntime().availableProcessors();

		System.out.println(cores);

		SplittableRandom rand = new SplittableRandom();
		System.out.println(rand.nextDouble());
		
		TriangularNumberGenerator gen = new TriangularNumberGenerator();
	  	
	  	for(int i = 0; i < 5; i++){
	  		System.out.println(gen.nextTriangularNumber());
	  	}
	  	
	  	String example = String.format("%02d", 5);
	  	
	  	System.out.println(example);
	  	
	  	String river = "Mississippi Mississippi";
	  	
	  	Pattern p = Pattern.compile("Mi(.*?)pi");
	  	Matcher m = p.matcher(river);
	  	
	  	while(m.find()) {
	  		System.out.println(m.group(1));
	  	}
	  	
	  	String testString = "Google,STOCK,10,50,0|Microsoft,STOCK,15,50,0|IBM,BOND,15,100,0.05:IBM,BOND,20,100,0.05|Google,STOCK,15,50,0|Microsoft,STOCK,10,50,0.05";
	  	System.out.println(Arrays.toString(testString.split(":")[0].split("\\|")));
	  	System.out.println(Arrays.toString(testString.split(":")[1].split("\\|")));
	  	
	  	double testD = 2.0;
	  	
	  	for(int i = 0; i < 100; i++){
	  		testD /= 3;
	  	}
	  	
	  	System.out.println(testD);
	}
}

class TriangularNumberGenerator {
	int sum = 0;
	int numPosition = 0;

	int nextTriangularNumber() {
		return sum += ++numPosition;
	}
}

abstract class x{
	void sayHello(){
		
	}
}

interface y{
	
}
