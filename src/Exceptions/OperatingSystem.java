package Exceptions;

import java.io.BufferedReader;
import Exceptions.Procceses;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInput;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class OperatingSystem {

	static Queue<Procceses> readyQueue = new LinkedList<Procceses>();
	static SemPrint print = new SemPrint();
	static SemWrite write = new SemWrite();
	static SemRead read = new SemRead();
	static SemAssign assign = new SemAssign();
	static int TQ = 2;
	static ProcessState ps;
	static Procceses A;
	static Procceses B;
	static boolean flagready = false;
	static int processID = 0;
	static Queue<Object> helperQueue = new LinkedList<>();

	public static void Scheduler_RR() {
//		Queue<Object> RR = new LinkedList<>();
//		while (!readyQueue.isEmpty()) {
//			Procceses p = readyQueue.peek();
//			if (p.getStatus() != ProcessState.Terminated) {
//				
//			}
//		}

		// AYWA EL CAAAAROOOO
		while (!readyQueue.isEmpty()) {
			Procceses p = readyQueue.poll();
			for (int i = 0; i < TQ; i++) {
				System.out.println("Executing process " + p);
				if (p.getStatus() != ProcessState.Terminated) {
					readyQueue.add(p);
					System.out.println(readyQueue + "added to ready queue");
					p.setStatus(ProcessState.Terminated);
					System.out.println("proccess state is terminated");
				}
				if (p.getStatus() == ProcessState.Terminated) {
					break;
				}
			}
		}

		System.out.println("ESHTA 3ALEIKY YA CAAAAAROOOOOOO.");
	}

	private static void Scheduler_FCFS() {
		int i = 0;
		while (!helperQueue.isEmpty()) {
			Procceses p = (Procceses) helperQueue.peek();
			if (p.getStatus() == ProcessState.Ready && !flagready) {
				System.out.println(i++);
				p.start();
				flagready = true;
			}

			helperQueue.peek();
			helperQueue.remove();
			while (p.isAlive()) {
			}
		//System.out.println(p.status);
		}
		
	}

	public static void Scheduler_MLQS() {
		Queue<Object> highPriority = new LinkedList<>();
		Queue<Object> mediumPriority = new LinkedList<>();
		Queue<Object> lowPriority = new LinkedList<>();

		while (!readyQueue.isEmpty()) {
			Procceses p = readyQueue.poll();
			
				highPriority.add(p);
				readyQueue.remove(p);
				if (p.getStatus() == ProcessState.Terminated) {
					highPriority.remove(p);
				} else if (p.getStatus() != ProcessState.Terminated) {
					mediumPriority.add(p);
					highPriority.remove(p);
				} else if (p.getStatus() == ProcessState.Terminated) {
					mediumPriority.remove(p);
				}
				else if (p.getStatus() != ProcessState.Terminated) {
					lowPriority.add(p);
					mediumPriority.remove(p);
				} else if (p.getStatus() == ProcessState.Terminated) {
					lowPriority.remove(p);
				}
			

			while (!highPriority.isEmpty() || !mediumPriority.isEmpty() || !lowPriority.isEmpty()) {
				// Check the highest priority queue with processes waiting
				if (!highPriority.isEmpty()) {
					helperQueue = highPriority;
					System.out.println(helperQueue);
				} else if (!mediumPriority.isEmpty()) {
					helperQueue = mediumPriority;
					System.out.println(helperQueue);

				} else {
					helperQueue = lowPriority;
					System.out.println(helperQueue);

				}
				Scheduler_FCFS();
			}
			System.out.println("All processes have completed.");
		}

	}

	public static String readFile(String name) {
		{
			try {
				File file = new File(name);
				BufferedReader br = new BufferedReader(new FileReader(file));
				String st;
				while ((st = br.readLine()) != null)
					System.out.println(st);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}

		return null;

	}

	public static void writefile(String name, String data) throws IOException {
		try {
			FileWriter fWriter = new FileWriter(name);
			fWriter.write(data);
			System.out.println(data);
			fWriter.close();
			System.out.println("File is created successfully with the content: "+ data);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void print(String text) {

		System.out.println(text);

	}

	public static String assign() {
		Scanner in = new Scanner(System.in);
		String data = in.nextLine();
		return data;
	}

	private static Object createProcess() {
		Procceses p = new Procceses(processID++);
		System.out.print(processID + " ");
		// ProcessTable.add(p);
		readyQueue.add(p);
		ps = ProcessState.New;
		p.setStatus(ps);
		System.out.println(p.getStatus());
		return p;
	}

	public static void main(String[] args) throws Exception {
		A = (Procceses) createProcess();
		B = (Procceses) createProcess();
    	Scheduler_RR();
		Scheduler_FCFS();
		Scheduler_MLQS();
		readFile("ali");
		writefile("Martina", "Carooooo");
		
		
		System.out.println("Ahla caro enty walahy");
	}
}