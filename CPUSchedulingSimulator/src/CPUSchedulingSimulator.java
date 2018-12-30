import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Random;

class CPUSchedulingSimulator {
	static PrintWriter out = null;

	public static void main(String[] args) {

		createPrinter("C:\\Users\\Pleasure Cruise\\Desktop\\output.txt");

		final int NUMBER_OF_PROCESSES = 50;

		PCB[] processes = new PCB[NUMBER_OF_PROCESSES];

		createProcesses(NUMBER_OF_PROCESSES, processes);

		printProcesses(processes);

		simulatePriorityBasedPreemptiveScheduling(processes);

		simulateShortestRemainingTimeNextPreemptiveScheduling(processes);

		final int TIME_QUANTUM = 10;

		simulateRoundRobinScheduling(processes, TIME_QUANTUM);
	}

	static void simulatePriorityBasedPreemptiveScheduling(PCB[] processes) {
		PriorityQueue<PCB> readyQueue = new PriorityQueue<>(new ByPrioritySorter());
		PCB currentlyRunning = null;
		int time = 0;
		int i = 0;

		System.out.println();
		System.out.println("Beginning Priority-Based Preemptive Scheduling Simulation");
		System.out.println();
		out.println();
		out.println("Beginning Priority-Based Preemptive Scheduling Simulation");
		out.println();

		while (i < processes.length || !readyQueue.isEmpty() || currentlyRunning != null) {

			if (currentlyRunning == null || (i < processes.length
					&& processes[i].arrivalTimeStamp < currentlyRunning.executionEndTime)) {

				time = processes[i].arrivalTimeStamp;
				currentlyRunning = handleProcessArrival_PP(readyQueue, currentlyRunning,
						new PCB(processes[i]), time);
				i++;
			} else {
				time = currentlyRunning.executionEndTime;

				System.out.print("Time: " + time + " Ending process " + currentlyRunning.processID);
				out.print("Time: " + time + " Ending process " + currentlyRunning.processID);

				currentlyRunning = handleProcessCompletion_PP(readyQueue, time);

				if (currentlyRunning != null) {
					System.out.println(" Starting process " + currentlyRunning.processID);
					out.println(" Starting process " + currentlyRunning.processID);
				} else {

					System.out.println();
					out.println();

					if (i == processes.length) {
						System.out.println();
						System.out.println(
								"Priority-Based Preemptive Scheduling Simulation complete");
						out.println();
						out.println("Priority-Based Preemptive Scheduling Simulation complete");
					}
				}
			}
		}

		System.out.println();
		out.println();
	}

	static void simulateShortestRemainingTimeNextPreemptiveScheduling(PCB[] processes) {
		PriorityQueue<PCB> readyQueue = new PriorityQueue<>(new ByRemainingTimeSorter());
		PCB currentlyRunning = null;
		int time = 0;
		int i = 0;

		System.out.println();
		System.out
				.println("Beginning Shortest Remaining Time Next Preemptive Scheduling Simulation");
		System.out.println();
		out.println();
		out.println("Beginning Shortest Remaining Time Next Preemptive Scheduling Simulation");
		out.println();

		while (i < processes.length || !readyQueue.isEmpty() || currentlyRunning != null) {

			if (currentlyRunning == null || (i < processes.length
					&& processes[i].arrivalTimeStamp < currentlyRunning.executionEndTime)) {

				time = processes[i].arrivalTimeStamp;
				currentlyRunning = handleProcessArrival_SRTP(readyQueue, currentlyRunning,
						new PCB(processes[i]), time);
				i++;
			} else {
				time = currentlyRunning.executionEndTime;

				System.out.print("Time: " + time + " Ending process " + currentlyRunning.processID);
				out.print("Time: " + time + " Ending process " + currentlyRunning.processID);

				currentlyRunning = handleProcessCompletion_SRTP(readyQueue, time);

				if (currentlyRunning != null) {
					System.out.println(" Starting process " + currentlyRunning.processID);
					out.println(" Starting process " + currentlyRunning.processID);
				} else {

					System.out.println();
					out.println();

					if (i == processes.length) {
						System.out.println();
						System.out.println(
								"Shortest Remaining Time Next Preemptive Scheduling Simulation complete");
						out.println();
						out.println(
								"Shortest Remaining Time Next Preemptive Scheduling Simulation complete");
					}
				}
			}
		}

		System.out.println();
		out.println();
	}

	static void simulateRoundRobinScheduling(PCB[] processes, int timeQuantum) {
		ArrayDeque<PCB> readyQueue = new ArrayDeque<PCB>();
		PCB currentlyRunning = null;
		int time = 0;
		int i = 0;

		System.out.println();
		System.out.println("Beginning Round Robin Scheduling Simulation");
		System.out.println();
		out.println();
		out.println("Beginning Round Robin Scheduling Simulation");
		out.println();

		while (i < processes.length || !readyQueue.isEmpty() || currentlyRunning != null) {

			if (currentlyRunning == null || (i < processes.length
					&& processes[i].arrivalTimeStamp < currentlyRunning.executionEndTime)) {

				time = processes[i].arrivalTimeStamp;
				currentlyRunning = handleProcessArrival_RR(readyQueue, currentlyRunning,
						new PCB(processes[i]), time, timeQuantum);
				i++;
			} else {
				time = currentlyRunning.executionEndTime;

				if (currentlyRunning.remainingBurstTime > timeQuantum) {
					currentlyRunning = handleEndOfTimeQuantum_RR(readyQueue, currentlyRunning, time,
							timeQuantum);
				} else {
					System.out.print(
							"Time: " + time + " Ending process " + currentlyRunning.processID);
					out.print("Time: " + time + " Ending process " + currentlyRunning.processID);

					currentlyRunning = handleProcessCompletion_RR(readyQueue, time, timeQuantum);

					if (currentlyRunning != null) {
						System.out.println(" Starting process " + currentlyRunning.processID);
						out.println(" Starting process " + currentlyRunning.processID);
					} else {

						System.out.println();
						out.println();

						if (i == processes.length) {
							System.out.println();
							System.out.println("Round Robin Scheduling Simulation complete");
							out.println();
							out.println("Round Robin Scheduling Simulation complete");
						}
					}
				}
			}
		}

		System.out.println();
		out.println();
	}

	static PCB handleProcessArrival_PP(PriorityQueue<PCB> readyQueue, PCB currentlyRunning,
			PCB arriving, int time) {

		if (currentlyRunning == null) {

			System.out.println("Time: " + time + "  Process " + arriving.processID
					+ " with priority " + arriving.processPriority + " has arrived.");
			out.println("Time: " + time + "  Process " + arriving.processID + " with priority "
					+ arriving.processPriority + " has arrived.");

			arriving.executionStartTime = time;
			arriving.executionEndTime = time + arriving.totalBurstTime;
			arriving.remainingBurstTime = arriving.totalBurstTime;

			return arriving;
		}

		if (currentlyRunning.processPriority <= arriving.processPriority) {

			System.out.println("Time: " + time + " Current process " + currentlyRunning.processID
					+ " priority: " + currentlyRunning.processPriority + "  Arriving process "
					+ arriving.processID + " priority: " + arriving.processPriority
					+ " Adding arriving to ready queue.");
			out.println("Time: " + time + " Current process " + currentlyRunning.processID
					+ " priority: " + currentlyRunning.processPriority + "  Arriving process "
					+ arriving.processID + " priority: " + arriving.processPriority
					+ " Adding arriving to ready queue.");

			arriving.executionStartTime = 0;
			arriving.executionEndTime = 0;
			arriving.remainingBurstTime = arriving.totalBurstTime;

			readyQueue.add(arriving);

			return currentlyRunning;
		}

		System.out.println("Time: " + time + " Current process " + currentlyRunning.processID
				+ " priority: " + currentlyRunning.processPriority + "  Arriving process "
				+ arriving.processID + " priority: " + arriving.processPriority
				+ " Context switch, adding current process to ready queue.");
		out.println("Time: " + time + " Current process " + currentlyRunning.processID
				+ " priority: " + currentlyRunning.processPriority + "  Arriving process "
				+ arriving.processID + " priority: " + arriving.processPriority
				+ " Context switch, adding current process to ready queue.");

		currentlyRunning.remainingBurstTime = currentlyRunning.remainingBurstTime
				- (time - currentlyRunning.executionStartTime);
		currentlyRunning.executionStartTime = 0;
		currentlyRunning.executionEndTime = 0;

		readyQueue.add(currentlyRunning);

		arriving.executionStartTime = time;
		arriving.executionEndTime = time + arriving.totalBurstTime;
		arriving.remainingBurstTime = arriving.totalBurstTime;

		return arriving;
	}

	static PCB handleProcessCompletion_PP(PriorityQueue<PCB> readyQueue, int time) {
		if (readyQueue.peek() == null) {
			return null;
		}

		PCB nextProcess = readyQueue.poll();

		nextProcess.executionStartTime = time;
		nextProcess.executionEndTime = time + nextProcess.remainingBurstTime;

		return nextProcess;
	}

	static PCB handleProcessArrival_SRTP(PriorityQueue<PCB> readyQueue, PCB currentlyRunning,
			PCB arriving, int time) {

		if (currentlyRunning == null) {

			System.out.println("Time: " + time + "  Process " + arriving.processID
					+ " with total burst time " + arriving.totalBurstTime + " has arrived.");
			out.println("Time: " + time + "  Process " + arriving.processID
					+ " with total burst time " + arriving.totalBurstTime + " has arrived.");

			arriving.executionStartTime = time;
			arriving.executionEndTime = time + arriving.totalBurstTime;
			arriving.remainingBurstTime = arriving.totalBurstTime;

			return arriving;
		}

		currentlyRunning.remainingBurstTime = currentlyRunning.remainingBurstTime
				- (time - currentlyRunning.executionStartTime);
		currentlyRunning.executionStartTime = time;

		if (currentlyRunning.remainingBurstTime <= arriving.totalBurstTime) {

			System.out.println("Time: " + time + " Current process " + currentlyRunning.processID
					+ " Remaining Burst Time: " + currentlyRunning.remainingBurstTime
					+ "  Arriving process " + arriving.processID + " Total Burst Time: "
					+ arriving.totalBurstTime + " Adding arriving to ready queue.");
			out.println("Time: " + time + " Current process " + currentlyRunning.processID
					+ " Remaining Burst Time: " + currentlyRunning.remainingBurstTime
					+ "  Arriving process " + arriving.processID + " Total Burst Time: "
					+ arriving.totalBurstTime + " Adding arriving to ready queue.");

			arriving.executionStartTime = 0;
			arriving.executionEndTime = 0;
			arriving.remainingBurstTime = arriving.totalBurstTime;

			readyQueue.add(arriving);

			return currentlyRunning;
		}

		System.out.println("Time: " + time + " Current process " + currentlyRunning.processID
				+ " Remaining Burst Time: " + currentlyRunning.remainingBurstTime
				+ "  Arriving process " + arriving.processID + " Total Burst Time: "
				+ arriving.totalBurstTime
				+ " Context switch, adding current process to ready queue.");
		out.println("Time: " + time + " Current process " + currentlyRunning.processID
				+ " Remaining Burst Time: " + currentlyRunning.remainingBurstTime
				+ "  Arriving process " + arriving.processID + " Total Burst Time: "
				+ arriving.totalBurstTime
				+ " Context switch, adding current process to ready queue.");

		currentlyRunning.executionStartTime = 0;
		currentlyRunning.executionEndTime = 0;

		readyQueue.add(currentlyRunning);

		arriving.executionStartTime = time;
		arriving.executionEndTime = time + arriving.totalBurstTime;
		arriving.remainingBurstTime = arriving.totalBurstTime;

		return arriving;
	}

	static PCB handleProcessCompletion_SRTP(PriorityQueue<PCB> readyQueue, int time) {
		if (readyQueue.peek() == null) {
			return null;
		}

		PCB nextProcess = readyQueue.poll();

		nextProcess.executionStartTime = time;
		nextProcess.executionEndTime = time + nextProcess.remainingBurstTime;

		return nextProcess;
	}

	static PCB handleProcessArrival_RR(ArrayDeque<PCB> readyQueue, PCB currentlyRunning,
			PCB arriving, int time, int timeQuantum) {

		if (currentlyRunning == null) {

			System.out
					.println("Time: " + time + "  Process " + arriving.processID + " has arrived.");
			out.println("Time: " + time + "  Process " + arriving.processID + " has arrived.");

			arriving.executionStartTime = time;

			if (timeQuantum < arriving.totalBurstTime) {
				arriving.executionEndTime = time + timeQuantum;
			} else {
				arriving.executionEndTime = time + arriving.totalBurstTime;
			}

			arriving.remainingBurstTime = arriving.totalBurstTime;

			return arriving;
		}

		System.out.println("Time: " + time + "  Process " + arriving.processID
				+ " has arrived, adding to ready queue.");
		out.println("Time: " + time + "  Process " + arriving.processID
				+ " has arrived, adding to ready queue.");

		arriving.executionStartTime = 0;
		arriving.executionEndTime = 0;
		arriving.remainingBurstTime = arriving.totalBurstTime;

		readyQueue.add(arriving);

		return currentlyRunning;
	}

	static PCB handleProcessCompletion_RR(ArrayDeque<PCB> readyQueue, int time, int timeQuantum) {

		if (readyQueue.peek() == null) {
			return null;
		}

		PCB nextProcess = readyQueue.poll();

		nextProcess.executionStartTime = time;

		if (timeQuantum < nextProcess.remainingBurstTime) {
			nextProcess.executionEndTime = time + timeQuantum;
		} else {
			nextProcess.executionEndTime = time + nextProcess.remainingBurstTime;
		}

		return nextProcess;
	}

	static PCB handleEndOfTimeQuantum_RR(ArrayDeque<PCB> readyQueue, PCB currentlyRunning, int time,
			int timeQuantum) {

		if (currentlyRunning != null) {

			currentlyRunning.remainingBurstTime = currentlyRunning.remainingBurstTime - timeQuantum;
			currentlyRunning.executionStartTime = 0;
			currentlyRunning.executionEndTime = 0;
			currentlyRunning.arrivalTimeStamp = time;

			readyQueue.add(currentlyRunning);

			PCB nextProcess = readyQueue.poll();

			if (!readyQueue.isEmpty()) {
				System.out.println(
						"Time: " + time + " End of time quantum, context switch. Current process "
								+ currentlyRunning.processID + " added to queue, next process "
								+ nextProcess.processID + " taken off queue");
				out.println(
						"Time: " + time + " End of time quantum, context switch. Current process "
								+ currentlyRunning.processID + " added to queue, next process "
								+ nextProcess.processID + " taken off queue");
			} else {
				System.out.println(
						"Time: " + time + " End of time quantum, queue is empty. Current process "
								+ currentlyRunning.processID + " continues");
				out.println(
						"Time: " + time + " End of time quantum, queue is empty. Current process "
								+ currentlyRunning.processID + " continues");
			}

			nextProcess.executionStartTime = time;

			if (timeQuantum < nextProcess.remainingBurstTime) {
				nextProcess.executionEndTime = time + timeQuantum;
			} else {
				nextProcess.executionEndTime = time + nextProcess.remainingBurstTime;
			}

			return nextProcess;
		}

		return null;
	}

	static void printProcesses(PCB[] processes) {
		for (int i = 0; i < processes.length; i++) {

			System.out.println(processes[i]);
			out.println(processes[i]);
		}
	}

	static void createProcesses(int numProcesses, PCB[] processes) {

		HashSet<Integer> takenArrivalTimes = new HashSet<>();

		Random rand = new Random();

		int processPriority;
		int arrivalTimeStamp;
		int totalBurstTime;

		for (int i = 0; i < numProcesses; i++) {
			processPriority = rand.nextInt(3) + 1;
			arrivalTimeStamp = rand.nextInt(1000);

			while (takenArrivalTimes.contains(arrivalTimeStamp)) {
				arrivalTimeStamp = rand.nextInt(1000);
			}

			takenArrivalTimes.add(arrivalTimeStamp);

			totalBurstTime = rand.nextInt(50) + 1;

			processes[i] = new PCB(processPriority, arrivalTimeStamp, totalBurstTime);

		}

		Arrays.sort(processes, new ByArrivalTimeSorter());

		for (int i = 0; i < processes.length; i++) {
			processes[i].setProcessID(i + 1);
		}
	}

	static void createPrinter(String fileName) {
		try {
			out = new PrintWriter(new FileWriter(fileName), true);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}

class PCB {
	public int processID;
	public int processPriority;
	public int arrivalTimeStamp;
	public int totalBurstTime;
	public int executionStartTime;
	public int executionEndTime;
	public int remainingBurstTime;

	PCB(PCB original) {
		this.processID = original.processID;
		this.processPriority = original.processPriority;
		this.arrivalTimeStamp = original.arrivalTimeStamp;
		this.totalBurstTime = original.totalBurstTime;
	}

	PCB(int processPriority, int arrivalTimeStamp, int totalBurstTime) {
		this.processPriority = processPriority;
		this.arrivalTimeStamp = arrivalTimeStamp;
		this.totalBurstTime = totalBurstTime;
	}

	public void setProcessID(int processID) {
		this.processID = processID;
	}

	@Override
	public String toString() {
		return "{processID = " + processID + "; processPriority = " + processPriority
				+ "; arrivalTimeStamp = " + arrivalTimeStamp + "; totalBurstTime = "
				+ totalBurstTime + ";}";
	}
}

class ByArrivalTimeSorter implements Comparator<PCB> {

	@Override
	public int compare(PCB first, PCB second) {
		return first.arrivalTimeStamp - second.arrivalTimeStamp;
	}
}

class ByPrioritySorter implements Comparator<PCB> {

	@Override
	public int compare(PCB first, PCB second) {
		return first.processPriority - second.processPriority;
	}
}

class ByRemainingTimeSorter implements Comparator<PCB> {

	@Override
	public int compare(PCB first, PCB second) {
		return first.remainingBurstTime - second.remainingBurstTime;
	}
}