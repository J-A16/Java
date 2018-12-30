import java.util.ArrayList;
import java.util.List;

class CheckIfGridsMatch {

	// Complete the countMatches function below.
	public static void main(String[] args) {

		ArrayList<String> grid1 = new ArrayList<String>();
		ArrayList<String> grid2 = new ArrayList<String>();

		grid1.add("0010");
		grid1.add("0111");
		grid1.add("0100");
		grid1.add("1111");

		grid2.add("0010");
		grid2.add("0111");
		grid2.add("0110");
		grid2.add("1111");

		Boolean[][] visited = new Boolean[grid1.size()][grid1.size()];
		int matchingRegionCount = 0;

		for (int i = 0; i < grid1.size(); i++) {
			for (int j = 0; j < grid1.size(); j++) {
				visited[i][j] = false;
			}
		}

		for (int i = 0; i < grid1.size(); i++) {
			for (int j = 0; j < grid1.size(); j++) {
				if (!visited[i][j] && grid1.get(i).charAt(j) != '0') {
					if (checkIfMatchingRegions(grid1, grid2, visited, i, j)) {
						matchingRegionCount++;
						System.out.println(i + " " + j);
					} else {
						Boolean[][] visitedInRegion = new Boolean[grid1.size()][grid1.size()];

						for (int m = 0; m < grid1.size(); m++) {
							for (int n = 0; n < grid1.size(); n++) {
								visitedInRegion[m][n] = false;
							}
						}

						invalidateRegion(grid1, visited, visitedInRegion, i, j);
					}
				}
			}
		}

		System.out.println(matchingRegionCount);
	}

	static void invalidateRegion(List<String> grid1, Boolean[][] visited,
			Boolean[][] visitedInRegion, int i, int j) {

		if (visitedInRegion[i][j] || grid1.get(i).charAt(j) == '0') {
			return;
		}

		visited[i][j] = true;
		visitedInRegion[i][j] = true;

		invalidateCell(grid1, visited, visitedInRegion, i - 1, j);
		invalidateCell(grid1, visited, visitedInRegion, i, j - 1);
		invalidateCell(grid1, visited, visitedInRegion, i + 1, j);
		invalidateCell(grid1, visited, visitedInRegion, i, j + 1);
	}

	static void invalidateCell(List<String> grid1, Boolean[][] visited, Boolean[][] visitedInRegion,
			int i, int j) {
		if (isValidCell(grid1, i, j)) {
			invalidateRegion(grid1, visited, visitedInRegion, i, j);
		}
	}

	static boolean checkIfMatchingRegions(List<String> grid1, List<String> grid2,
			Boolean[][] visited, int i, int j) {

		if (visited[i][j] || grid1.get(i).charAt(j) == '0') {
			return true;
		}

		visited[i][j] = true;

		if (checkIfCellAndAdjacentMatch(grid1, grid2, i, j)) {
			return checkAdjacentCells(grid1, grid2, visited, i, j);
		} else {
			return false;
		}

	}

	static boolean checkAdjacentCells(List<String> grid1, List<String> grid2, Boolean[][] visited,
			int i, int j) {
		if (!checkCell(grid1, grid2, visited, i - 1, j)) {
			return false;
		}

		if (!checkCell(grid1, grid2, visited, i, j - 1)) {
			return false;
		}

		if (!checkCell(grid1, grid2, visited, i + 1, j)) {
			return false;
		}

		if (!checkCell(grid1, grid2, visited, i, j + 1)) {
			return false;
		}

		return true;
	}

	static boolean checkCell(List<String> grid1, List<String> grid2, Boolean[][] visited, int i,
			int j) {
		if (isValidCell(grid1, i, j)) {
			return checkIfMatchingRegions(grid1, grid2, visited, i, j);
		} else {
			return true;
		}
	}

	static boolean isValidCell(List<String> grid1, int i, int j) {
		int size = grid1.size();

		return i >= 0 && i < size && j >= 0 && j < size;
	}

	static boolean checkIfCellAndAdjacentMatch(List<String> grid1, List<String> grid2, int i,
			int j) {

		if (!checkIfCellMatches(grid1, grid2, i, j)) {
			return false;
		}

		if (!checkIfCellMatches(grid1, grid2, i - 1, j)) {
			return false;
		}

		if (!checkIfCellMatches(grid1, grid2, i, j - 1)) {
			return false;
		}

		if (!checkIfCellMatches(grid1, grid2, i + 1, j)) {
			return false;
		}

		if (!checkIfCellMatches(grid1, grid2, i, j + 1)) {
			return false;
		}
		return true;
	}

	static boolean checkIfCellMatches(List<String> grid1, List<String> grid2, int i, int j) {
		if (isValidCell(grid1, i, j)) {
			return grid1.get(i).charAt(j) == grid2.get(i).charAt(j);
		} else {
			return true;
		}
	}
}