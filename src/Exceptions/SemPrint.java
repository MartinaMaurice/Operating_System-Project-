package Exceptions;

import java.util.LinkedList;
import java.util.Queue;

public class SemPrint {
	public static int semPrint = 1;
	public static Queue<Integer> PrintQueue = new LinkedList<>();
	static int P = 1;
	public static void semPrintWait() throws Exception {
		int id = P++;
		if (semPrint == 1) {
			semPrint = 0;

		} else {
			PrintQueue.add(id);
			Thread.sleep(1);
			return;
		}
	}

	public static void semPrintPost() {
		if (PrintQueue.isEmpty()) {
			semPrint = 1;

		} else {
			System.out.println(PrintQueue.remove());
		}

	}

}
