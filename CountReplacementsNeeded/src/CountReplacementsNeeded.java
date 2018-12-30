import java.util.ArrayList;
import java.util.List;

class CountReplacementsNeeded {
	// Complete the minimalOperations function below.
	public static void main(String[] args) {
		
		ArrayList<String> words = new ArrayList<String>();
		
		words.add("add");
		words.add("abdbsssjjjj");
		
		ArrayList<Integer> result = new ArrayList<Integer>();

		for (int i = 0; i < words.size(); i++) {
			result.add(countReplacements(words.get(i)));
		}

		System.out.println(result);
	}

	static int countReplacements(String word) {

		char lastCharacter = word.charAt(0);
		int repeatCount = 0;
		int replacements = 0;

		for (int i = 1; i < word.length(); i++) {
			if (lastCharacter == word.charAt(i)) {
				repeatCount++;
				if (repeatCount % 2 == 1) {
					replacements++;
				}
			} else {
				repeatCount = 0;
				lastCharacter = word.charAt(i);
			}
		}

		return replacements;
	}
}