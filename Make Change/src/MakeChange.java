import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/*
Programming Challenge Description:
The goal of this challenge is to design a cash register program. You will be given two decimal numbers. The first is the purchase price (PP) of the item. The second is the cash (CH) given by the customer. Your register currently has the following bills/coins within it: 
'PENNY': .01,
'NICKEL': .05,
'DIME': .10,
'QUARTER': .25,
'HALF DOLLAR': .50,
'ONE': 1.00,
'TWO': 2.00,
'FIVE': 5.00,
'TEN': 10.00,
'TWENTY': 20.00,
'FIFTY': 50.00,
'ONE HUNDRED': 100.00
The aim of the program is to calculate the change that has to be returned to the customer.

Input:

Your program should read lines of text from standard input.
Each line contains two numbers which are separated by a semicolon.
The first is the Purchase price (PP) and the second is the cash(CH) given by the customer.

Output:

For each line of input print a single line to standard output which is the change
to be returned to the customer. In case the CH < PP, print out ERROR.
If CH == PP, print out ZERO. For all other cases print the amount that needs to be returned,
in terms of the currency values provided. The output should be alphabetically sorted.

Test 1

Sample Input

13.45;15.00

Expected Output

HALF DOLLAR,NICKEL,ONE

Test 2

Sample Input

14.00;100.00

Expected Output

FIFTY,FIVE,ONE,TEN,TWENTY
*/

public class MakeChange {
	/**
	 * Iterate through each line of input.
	 */
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String line;
		while (in.hasNextLine()) {
			
			line = in.nextLine();
			String[] values = line.split(";");

			double purchasePrice = Double.parseDouble(values[0]);

			double cashGiven = Double.parseDouble(values[1]);

			if (purchasePrice > cashGiven) {

				System.out.println("ERROR");

			} else if (purchasePrice == cashGiven) {

				System.out.println("ZERO");

			} else {

				System.out.println(calculateChange(purchasePrice, cashGiven));
			}

		}

		in.close();
	}

	static String calculateChange(double purchasePrice, double cashGiven) {

		int changeInPennies = (int) ((cashGiven - purchasePrice) * 100);

		int[] registerValuesInPennies = { 10000, 5000, 2000, 1000, 500, 200, 100, 50, 25, 10, 5,
				1 };
		String[] namesOfValues = { "ONE HUNDRED", "FIFTY", "TWENTY", "TEN", "FIVE", "TWO", "ONE",
				"HALF DOLLAR", "QUARTER", "DIME", "NICKEL", "PENNY" };

		ArrayList<String> notes = new ArrayList<String>();
		
		

		for (int i = 0; i < registerValuesInPennies.length && changeInPennies > 0; i++) {

			while (changeInPennies >= registerValuesInPennies[i]) {

				changeInPennies = changeInPennies % registerValuesInPennies[i];

				notes.add(namesOfValues[i]);
			}
		}

		String[] notesArray = new String[notes.size()];
		
		notes.toArray(notesArray);
		
		Arrays.sort(notesArray);
		
		return String.join(",", notesArray);
	}
}