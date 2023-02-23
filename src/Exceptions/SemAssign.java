package Exceptions;

import java.util.LinkedList;
import java.util.Queue;

public class SemAssign {
	public static int semAssign = 1;
	public static int semWrite = 1;
	public static Queue<Integer> AssignQueue = new LinkedList<>();
	static int I = 1;

	public static void semAssignWait() throws Exception {
		int id = I++;
		if (semAssign == 1) {
			semAssign = 0;

		} else {
			AssignQueue.add(id);
			while (AssignQueue.contains(id)) {
				Thread.sleep(1);
			}
			return;
		}

	}

	public static void semAssignPost() {

		if (AssignQueue.isEmpty())
			semAssign = 1;
		else {
			AssignQueue.remove();
		}
	}

}
