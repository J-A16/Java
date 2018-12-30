import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

class MemoryAllocation {
	public static void main(String[] args) {

		Scanner cin = null;

		try {
			cin = new Scanner(new File("C:\\Users\\Pleasure Cruise\\Desktop\\input.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		final int ARRAY_LENGTH = cin.nextInt();

		int[][] requests = new int[ARRAY_LENGTH][2];

		boolean[] allocate = new boolean[ARRAY_LENGTH];

		for (int i = 0; i < requests.length; ++i) {

			if (cin.next().equals("allocate")) {
				allocate[i] = true;
				requests[i][0] = cin.nextInt();
				requests[i][1] = cin.nextInt();
			} else {
				allocate[i] = false;
				requests[i][0] = cin.nextInt();
			}
		}

		cin.close();

		PrintWriter out = null;

		try {
			out = new PrintWriter(new FileWriter("C:\\Users\\Pleasure Cruise\\Desktop\\output.txt"), true);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		for (int i = 0; i < requests.length; ++i) {
			System.out.println(allocate[i] + " " + requests[i][0] + " " + requests[i][1]);
		}

		int AMOUNT_OF_MEMORY = 100;

		int[] memory = new int[AMOUNT_OF_MEMORY];

		out.println("All range ends are exclusive");
		out.println();
		out.println("Begin Best Fit Allocate Testing");
		out.println();

		for (int i = 0; i < requests.length; ++i) {

			if (allocate[i]) {
				Range range = bestFitAllocate(requests[i][0], requests[i][1], memory);

				printAllocationRange(out, i, range, requests, memory);

			} else {
				releaseAndPrintRangeReleased(out, i, requests, memory);
			}
		}

		out.println();
		out.println("End Best Fit Allocate Testing");
		out.println();
		out.println();
		out.println();
		out.println("Begin First Fit Allocate Testing");
		out.println();

		memory = new int[AMOUNT_OF_MEMORY];

		System.out.println();

		for (int i = 0; i < requests.length; ++i) {

			if (allocate[i]) {
				Range range = firstFitAllocate(requests[i][0], requests[i][1], memory);

				printAllocationRange(out, i, range, requests, memory);

			} else {
				releaseAndPrintRangeReleased(out, i, requests, memory);
			}
		}

		out.println();
		out.println("End First Fit Allocate Testing");
		out.println();
		out.println();
		out.println();
		out.println("Begin Worst Fit Allocate Testing");
		out.println();

		memory = new int[AMOUNT_OF_MEMORY];

		System.out.println();

		for (int i = 0; i < requests.length; ++i) {

			if (allocate[i]) {
				Range range = worstFitAllocate(requests[i][0], requests[i][1], memory);

				printAllocationRange(out, i, range, requests, memory);

			} else {
				releaseAndPrintRangeReleased(out, i, requests, memory);
			}
		}

		out.println();
		out.println("End Worst Fit Allocate Testing");
		out.println();
		out.println();
		out.println();
		out.println("Begin Next Fit Allocate Testing");
		out.println();

		memory = new int[AMOUNT_OF_MEMORY];
		IntReference startIndex = new IntReference(0);

		System.out.println();

		for (int i = 0; i < requests.length; ++i) {

			if (allocate[i]) {

				Range range = nextFitAllocate(requests[i][0], requests[i][1], memory, startIndex);

				printAllocationRange(out, i, range, requests, memory);

			} else {
				releaseAndPrintRangeReleased(out, i, requests, memory);
			}
		}

		out.println();
		out.println("End Next Fit Allocate Testing");
		out.println();
	}

	static Range bestFitAllocate(int processId, int size, int[] memory) {

		boolean canFillRequest = false;
		int freeBlockSize = 0;
		int bestFit = memory.length;
		Range range = null;

		for (int i = 0; i < memory.length; ++i) {
			if (memory[i] == 0) {

				++freeBlockSize;

				if (i == memory.length - 1) {
					if (freeBlockSize <= bestFit && freeBlockSize >= size) {
						if (freeBlockSize < bestFit || (freeBlockSize == bestFit && !canFillRequest)) {
							canFillRequest = true;
							bestFit = freeBlockSize;
							int start = (i + 1) - freeBlockSize;
							int end = start + size;
							range = new Range(start, end);
						}
					}
				}
			} else {
				if (freeBlockSize <= bestFit && freeBlockSize >= size) {
					if (freeBlockSize < bestFit || (freeBlockSize == bestFit && !canFillRequest)) {
						canFillRequest = true;
						bestFit = freeBlockSize;
						int start = i - freeBlockSize;
						int end = start + size;
						range = new Range(start, end);
					}
				}

				if (bestFit == size)
					break;

				freeBlockSize = 0;
			}
		}

		if (canFillRequest) {
			for (int i = range.getStart(); i < range.getEnd(); ++i) {
				memory[i] = processId;
			}
		}

		return range;
	}

	static Range firstFitAllocate(int processId, int size, int[] memory) {

		boolean canFillRequest = false;
		int freeBlockSize = 0;
		Range range = null;

		for (int i = 0; i < memory.length; ++i) {
			if (memory[i] == 0) {

				++freeBlockSize;

				if (i == memory.length - 1) {
					if (freeBlockSize >= size) {
						canFillRequest = true;
						int start = (i + 1) - freeBlockSize;
						int end = start + size;
						range = new Range(start, end);
					}
				}
			} else {
				if (freeBlockSize >= size) {
					canFillRequest = true;
					int start = i - freeBlockSize;
					int end = start + size;
					range = new Range(start, end);
					break;
				}

				freeBlockSize = 0;
			}
		}

		if (canFillRequest) {
			for (int i = range.getStart(); i < range.getEnd(); ++i) {
				memory[i] = processId;
			}
		}

		return range;
	}

	static Range worstFitAllocate(int processId, int size, int[] memory) {

		boolean canFillRequest = false;
		int freeBlockSize = 0;
		int worstFit = 0;
		Range range = null;

		for (int i = 0; i < memory.length; ++i) {
			if (memory[i] == 0) {

				++freeBlockSize;

				if (i == memory.length - 1) {
					if (freeBlockSize > worstFit && freeBlockSize >= size) {
						canFillRequest = true;
						worstFit = freeBlockSize;
						int start = (i + 1) - freeBlockSize;
						int end = start + size;
						range = new Range(start, end);
					}
				}
			} else {
				if (freeBlockSize > worstFit && freeBlockSize >= size) {
					canFillRequest = true;
					worstFit = freeBlockSize;
					int start = i - freeBlockSize;
					int end = start + size;
					range = new Range(start, end);
				}

				freeBlockSize = 0;
			}
		}

		if (canFillRequest) {
			for (int i = range.getStart(); i < range.getEnd(); ++i) {
				memory[i] = processId;
			}
		}

		return range;
	}

	static Range nextFitAllocate(int processId, int size, int[] memory, IntReference startIndex) {

		boolean canFillRequest = false;
		int freeBlockSize = 0;
		Range range = null;

		for (int i = startIndex.getValue() % memory.length, j = 0; j < memory.length; ++i, i = i % memory.length, ++j) {
			if (memory[i] == 0) {

				++freeBlockSize;

				if (i == memory.length - 1) {
					if (freeBlockSize >= size) {
						canFillRequest = true;
						int start = (i + 1) - freeBlockSize;
						int end = start + size;
						range = new Range(start, end);
						startIndex.setValue(end);
						break;
					}

					freeBlockSize = 0;
				}
			} else {
				if (freeBlockSize >= size) {
					canFillRequest = true;
					int start = i - freeBlockSize;
					int end = start + size;
					range = new Range(start, end);
					startIndex.setValue(end);
					break;
				}

				freeBlockSize = 0;
			}
		}

		if (canFillRequest) {
			for (int i = range.getStart(); i < range.getEnd(); ++i) {
				memory[i] = processId;
			}
		}

		return range;
	}

	static Range releaseMemory(int processId, int[] memory) {

		boolean startRecorded = false;
		int start = 0, end = 0;

		for (int i = 0; i < memory.length; ++i) {
			if (memory[i] == processId) {
				if (!startRecorded) {
					start = i;
					startRecorded = true;
				}

				memory[i] = 0;

				if (i == memory.length - 1) {
					end = i + 1;
				}
			} else {
				if (startRecorded) {
					end = i;
					break;
				}
			}
		}

		return new Range(start, end);
	}

	static void printAllocationRange(PrintWriter out, int i, Range range, int[][] requests,
			int[] memory) {

		if (range != null) {
			out.println("Successful allocation \t\t\t\tID: " + requests[i][0] + " Size: " + requests[i][1] + " units from "
					+ range.getStart() + " to " + range.getEnd());
			
			System.out.println(Arrays.toString(memory));
		} else {
			out.println("Unsuccessful allocation attempt \t\tID: " + requests[i][0] + " Size: " + requests[i][1] + " units");
			
			System.out.println(Arrays.toString(memory));
		}
	}

	static void releaseAndPrintRangeReleased(PrintWriter out, int i, int[][] requests, int[] memory) {

		Range range = releaseMemory(requests[i][0], memory);

		out.println("Released \t\t\t\t\tID: " + requests[i][0] + " Size: " + (range.getEnd() - range.getStart())
				+ " units from " + range.getStart() + " to " + range.getEnd());

		
		System.out.println(Arrays.toString(memory));
	}
}

class Range {
	private int start;
	private int end;

	public Range(int start, int end) {
		this.start = start;
		this.end = end;
	}

	public int getStart() {
		return start;
	}

	public int getEnd() {
		return end;
	}
}

class IntReference {
	private int value;

	public IntReference(int x) {
		value = x;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int x) {
		value = x;
	}
}