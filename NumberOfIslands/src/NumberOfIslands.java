import java.util.ArrayList;

class NumberOfIslands {

	public static void main(String[] args) {

		ArrayList<char[][]> grids = new ArrayList<>();
		
		grids.add(new char[][] {
		      "11110".toCharArray(),
		      "11010".toCharArray(),
		      "11000".toCharArray(),
		      "00000".toCharArray()
		      });
		
		grids.add(new char[][] {
		      "11000".toCharArray(),
		      "11000".toCharArray(),
		      "00100".toCharArray(),
		      "00011".toCharArray()
		      });
		
		while (!grids.isEmpty()) {
			
			char[][] grid = grids.remove(0);
			
			if (grid.length > 0) {
				boolean[][] counted = new boolean[grid.length][grid[0].length];

				System.out.println(countIslands(grid, counted));
			} else{
				
				System.out.println(0);
			}
		}
	}

	public static int countIslands(char[][] grid, boolean[][] counted) {
		int count = 0;

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (!counted[i][j] && grid[i][j] == '1') {
					count++;
					markIsland(i, j, grid, counted);

				}
			}
		}

		return count;
	}

	public static void markIsland(int i, int j, char[][] grid, boolean[][] counted) {
		if (!counted[i][j] && grid[i][j] == '1') {
			counted[i][j] = true;

			for (int m = -1; m <= 1; m++)
				for (int n = -1; n <= 1; n++)
					if (checkBounds(m, n, i + m, j + n, grid))
						markIsland(i + m, j + n, grid, counted);
		}

	}

	public static boolean checkBounds(int m, int n, int i, int j, char[][] grid) {
		if (m == -1 && n == -1 || m == 1 && n == -1 || m == 1 && n == 1 || m == -1 && n == 1)
			return false;

		if (i >= 0 && i < grid.length && j >= 0 && j < grid[0].length)
			return true;
		return false;
	}
}