import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/*
Programming Challenge Description:
We say a portfolio matches the benchmark when the market
value percentage of each asset in the portfolio matches the
market value percentage of each asset in the benchmark. Your
challenge is to write a program that determines the transactions
necessary to make a portfolio match a benchmark, assuming the total
market value of the portfolio stays the same.

Background

A portfolio is a collection of assets such as stocks and bonds.
A portfolio could have 10 shares of Vodafone stock, 15 shares of
Google stock and 15 shares of Microsoft bonds.

A benchmark is also just a collection of assets.
A benchmark could have 15 shares of Vodafone stock,
10 shares of Google stock and 15 shares of Microsoft bonds.

The market value of a stock is 
shares * price
The market value of a bond is
shares * (price + accrued interest) * 0.01

A transaction is when you “buy” or “sell” a particular asset.
For instance, you can decide to buy 5 shares of Vodafone stock
which, given the portfolio described above, would result in you
having 15 shares of Vodafone stock.

An asset’s market value percentage can be calculated by dividing
the market value of the asset by the total market value of every
asset in the portfolio or benchmark. For example, given the portfolio
described above and assuming all assets have a price of 5 and accrued
interest is 0.05, the market value percentage of Vodafone would be

(10 * 5) / ( (10 * 5) + (15 * 5) + (15 * (5 + 0.05) * 0.01) )

Inputs and Outputs

You will receive a string in the following format Portfolio:Benchmark
where Portfolio & Benchmark each are in the same format.

Here is the format: Name,AssetType,Shares,Price,AccruedInterest
where each asset within Portfolio or Benchmark is separated by '|' symbol.

The output for the transactions is TransactionType,Name,Shares

Assumptions

Shares & Price are positive decimals
There will always be at least 1 asset present in the Portfolio and
Benchmark
A particular asset will only be a stock or a bond, but not both
The final trades should be rounded to the nearest decimals
The trades should be sorted in ascending order based on the names of the
assets
Input:

Vodafone,STOCK,10,50,0|Google,STOCK,15,50,0|Microsoft,BOND,15,100,0.05:Vodafone,STOCK,15,50,0|Google,STOCK,10,50,0|Microsoft,BOND,15,100,0.05

Output:

BUY,Vodafone,5
SELL,Google,5

Test 1

Test Input

Google,STOCK,10,50,0|Microsoft,STOCK,15,50,0|IBM,BOND,15,100,0.05:IBM,BOND,20,100,0.05|Google,STOCK,15,50,0|Microsoft,STOCK,10,50,0.05

Expected Output

BUY,Google,5
BUY,IBM,5
SELL,Microsoft,5

Test 2

Test Input 

Vodafone,STOCK,10,50,0|Google,STOCK,15,50,0|Microsoft,BOND,15,100,0.05:Vodafone,STOCK,15,50,0|Google,STOCK,10,50,0|Microsoft,BOND,15,100,0.05

Expected Output

SELL,Google,5
BUY,Vodafone,5
*/


public class BalancePortfolio {
	/**
	 * Iterate through each line of input.
	 */
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		String line;
		
		while (in.hasNextLine()) {

			line = in.nextLine();

			String[] portfolioAndBenchmark = line.split(":");

			String[] portfolioArray = portfolioAndBenchmark[0].split("\\|");
			String[] benchmarkArray = portfolioAndBenchmark[1].split("\\|");

			CollectionOfAssets portfolio = new CollectionOfAssets(portfolioArray);
			CollectionOfAssets benchmark = new CollectionOfAssets(benchmarkArray);

			ArrayList<String> transactions = portfolio.calculateTransactionsToBalance(benchmark);

			for (int i = 0; i < transactions.size(); i++) {
				System.out.println(transactions.get(i));
			}
		}
		
		in.close();
	}

	static class CollectionOfAssets {
		String[] companyName;
		String[] assetType;
		int[] shares;
		int[] price;
		double[] accruedInterest;

		final double BOND_CONSTANT;

		double totalMarketValue;
		double[] marketValuePercentages;

		CollectionOfAssets(String[] collectionOfAssets) {

			Arrays.sort(collectionOfAssets);

			companyName = new String[collectionOfAssets.length];
			assetType = new String[collectionOfAssets.length];
			shares = new int[collectionOfAssets.length];
			price = new int[collectionOfAssets.length];
			accruedInterest = new double[collectionOfAssets.length];

			for (int i = 0; i < collectionOfAssets.length; i++) {

				String[] asset = collectionOfAssets[i].split(",");

				companyName[i] = asset[0];
				assetType[i] = asset[1];
				shares[i] = Integer.parseInt(asset[2]);
				price[i] = Integer.parseInt(asset[3]);
				accruedInterest[i] = Double.parseDouble(asset[4]);

			}

			BOND_CONSTANT = 0.01;

			totalMarketValue = 0;
			marketValuePercentages = new double[collectionOfAssets.length];

			findTotalMarketValue();
			findMarketValuePercentages();
		}

		ArrayList<String> calculateTransactionsToBalance(CollectionOfAssets benchmark) {

			ArrayList<String> transactions = new ArrayList<String>();

			for (int i = 0; i < marketValuePercentages.length; i++) {

				if (marketValuePercentages[i] > benchmark.marketValuePercentages[i]) {
					String transaction = "SELL," + companyName[i] + ",";

					int shares = 0;

					if (assetType[i].equals("STOCK")) {
						shares = (int) Math.round(
								((marketValuePercentages[i] - benchmark.marketValuePercentages[i])
										* totalMarketValue) / (double) price[i]);
					} else {
						shares = (int) Math.round(
								((marketValuePercentages[i] - benchmark.marketValuePercentages[i])
										* totalMarketValue) / ((double) price[i] * BOND_CONSTANT));
					}

					transaction += shares;

					transactions.add(transaction);

				} else if (marketValuePercentages[i] < benchmark.marketValuePercentages[i]) {
					String transaction = "BUY," + companyName[i] + ",";

					int shares = 0;

					if (assetType[i].equals("STOCK")) {
						shares = (int) Math.round(
								((benchmark.marketValuePercentages[i] - marketValuePercentages[i])
										* totalMarketValue) / (double) price[i]);
					} else {
						shares = (int) Math.round(
								((benchmark.marketValuePercentages[i] - marketValuePercentages[i])
										* totalMarketValue) / ((double) price[i] * BOND_CONSTANT));
					}

					transaction += shares;

					transactions.add(transaction);
				}

			}

			return transactions;
		}

		void findMarketValuePercentages() {

			for (int i = 0; i < marketValuePercentages.length; i++) {

				if (assetType[i].equals("STOCK")) {

					marketValuePercentages[i] = (shares[i] * price[i]) / totalMarketValue;

				} else {

					marketValuePercentages[i] = (shares[i] * (price[i] + accruedInterest[i])
							* BOND_CONSTANT) / totalMarketValue;
				}

			}

		}

		void findTotalMarketValue() {

			totalMarketValue = 0;

			for (int i = 0; i < assetType.length; i++) {

				if (assetType[i].equals("STOCK")) {

					totalMarketValue += shares[i] * price[i];

				} else {

					totalMarketValue += shares[i] * (price[i] + accruedInterest[i]) * BOND_CONSTANT;
				}
			}
		}

	}

}
