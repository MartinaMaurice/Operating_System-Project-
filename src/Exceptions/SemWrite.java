package Exceptions;

import java.util.LinkedList;
import java.util.Queue;

public class SemWrite {
	public static int semWrite = 1;

	public static Queue<Integer> WriteQueue = new LinkedList<>();
	static int W = 1;

	public static void semWriteWait() throws Exception {
		int id = W++;
		if (semWrite == 1) {
			semWrite = 0;

		} else {
			WriteQueue.add(id);
			while (WriteQueue.contains(id)) {
				Thread.sleep(1);
			}
			return;

		}

	}

	public static void semWritePost() {
		if (WriteQueue.isEmpty())
			semWrite = 1;
		else {
			WriteQueue.remove();
		}

	}
}
