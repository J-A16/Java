import java.util.ArrayList;

class FindTheMissingWords {
	// Complete the missingWords function below.
	public static void main(String[] args) {
		
		String s = "This sentence is complete but the other sentence will have only some words";
		
		String t = "This is but the other only";
		
		String[] WordsInS = s.split(" ");
		String[] WordsInT = t.split(" ");

		ArrayList<String> wordsMissingFromT = new ArrayList<String>();

		int i = 0;
		int j = 0;

		while (i < WordsInS.length && j < WordsInT.length) {

			if (WordsInS[i].equals(WordsInT[j])) {
				j++;
			} else {
				wordsMissingFromT.add(WordsInS[i]);
			}

			i++;
		}

		while (i < WordsInS.length) {
			wordsMissingFromT.add(WordsInS[i]);
			i++;
		}

		System.out.println(wordsMissingFromT);
	}

}