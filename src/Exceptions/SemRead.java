package Exceptions;

import java.util.LinkedList;
import java.util.Queue;

public class SemRead {
	public static int semRead = 1;
	public static Queue<Integer> ReadQueue = new LinkedList<>();
	static int R = 1;

	public static void semReadWait() throws Exception {
		int id = R++;
		if (semRead == 1) {
			semRead = 0;

		} else {
			ReadQueue.add(id);
			while (ReadQueue.contains(id)) {
				Thread.sleep(1);
			}
			return;
		}
	}

	public static void semReadPost() {
		if (ReadQueue.isEmpty()) {
			semRead = 1;
		} else {
			ReadQueue.remove();
		}

	}

}
