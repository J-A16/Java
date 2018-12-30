import java.util.Scanner;

/*
 * A person has taken a loan of $6000.00 with a fixed annual
 * interest rate of 6% for 5 years with no down payment.
 * The monthly payment has been fixed at $116.00 for entire term
 * of the loan. Here is the formula to calculate monthly fixed payment:
 * (NOTE: See attachment)

P = (monthly rate * Loan amount) / (1 - (1+monthly interest rate)^-n)
Here n is the number of payment periods.

Write a program to: 1. To calculate monthly payment 2.
To print out monthly payment and total interest payment for
the duration of loan rounded to its nearest integer

Input:
6000~5~6~0

Output:
$116.00~$960

Test 1
Test Input 25000~10~6~0
Expected Output $277.55~$8306
Test 2
Test Input 30000~10~6~5000
Expected Output $277.55~$8306
Test 3
Test Input 5000~5~6~0
Expected Output $96.66~$800
*/

public class CalculateMonthlyPayment {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String line;
		while (in.hasNextLine()) {

			line = in.nextLine();
			
			String[] values = line.split("~");

			int loanAmount = Integer.parseInt(values[0]);
			int years = Integer.parseInt(values[1]);
			int yearlyInterestRate = Integer.parseInt(values[2]);
			int downPayment = Integer.parseInt(values[3]);

			double monthlyPayment = calculateMonthlyPayment(loanAmount, years, yearlyInterestRate,
					downPayment);

			System.out.println("$" + monthlyPayment + "~" + "$"
					+ calculateTotalInterest(loanAmount, downPayment, years, monthlyPayment));
		}
		in.close();
	}

	static double calculateMonthlyPayment(int loanAmount, int years, int yearlyInterestRate,
			int downPayment) {

		int MONTHS_IN_ONE_YEAR = 12;

		loanAmount = loanAmount - downPayment;

		int numberOfPaymentPeriods = years * MONTHS_IN_ONE_YEAR;

		double monthlyInterestRateDecimal = (((double) yearlyInterestRate) / MONTHS_IN_ONE_YEAR)
				/ 100;

		double monthlyPayment = Math
				.round(100 * (monthlyInterestRateDecimal * loanAmount)
						/ (1 - Math.pow((1 + monthlyInterestRateDecimal), -numberOfPaymentPeriods)))
				/ 100.0;

		return monthlyPayment;
	}

	static int calculateTotalInterest(int loanAmount, int downPayment, int years,
			double monthlyPayment) {
		int MONTHS_IN_ONE_YEAR = 12;

		return (int) Math
				.round((monthlyPayment * years * MONTHS_IN_ONE_YEAR) - (loanAmount - downPayment));
	}
}