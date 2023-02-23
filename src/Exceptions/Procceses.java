package Exceptions;

import java.io.IOException;
import java.util.*;
import Exceptions.OperatingSystem;

public class Procceses extends Thread {
	public int processID;
	public ProcessState status = ProcessState.New;

	public Procceses(int m) {
		processID = m;
	}

	public ProcessState getStatus() {
		return status;
	}

	public void setStatus(ProcessState status) {
		this.status = status;
	}

	public int getProcessID() {
		return processID;
	}

	public void setProcessID(int processID) {
		this.processID = processID;
	}

//no burst time or arrival time asln 

	public static void proccessA() throws Exception {
		SemPrint.semPrintWait();
		System.out.println("PA TOOK PRINT SEMAPHORE");
		SemRead.semReadWait();
		System.out.println("PA TOOK READ SEMAPHORE");
		SemAssign.semAssignWait();
		System.out.println("PA TOOK INPUT SEMAPHORE");
		OperatingSystem.print(OperatingSystem.readFile(OperatingSystem.assign()));
		SemPrint.semPrintPost();
		System.out.println("PA RELEASED PRINT SEMAPHORE");
		SemRead.semReadPost();
		System.out.println("PA RELEASED READ SEMAPHORE");
		SemAssign.semAssignPost();
		System.out.println("PA RELEASED INPUT SEMAPHORE");
		// setStatus(ProcessState.Terminated);
	}

	public static void proccessB() throws Exception {
		SemPrint.semPrintWait();
		System.out.println("PB TOOK PRINT SEMAPHORE");
		SemAssign.semAssignWait();
		System.out.println("PB TOOK INPUT SEMAPHORE");
		OperatingSystem.print("Enter File Name: ");
		String filename = OperatingSystem.assign();
		OperatingSystem.print("Enter Data: ");
		String data = OperatingSystem.assign();
		SemAssign.semAssignPost();
		System.out.println("PB RELEASED INPUT SEMAPHORE");
		SemPrint.semPrintPost();
		System.out.println("PB RELEASED PRINT SEMAPHORE");
		SemWrite.semWriteWait();
		System.out.println("PB TOOK WRITE SEMAPHORE");
		OperatingSystem.writefile(filename, data);
		SemWrite.semWritePost();
		System.out.println("PB RELEASED WRITE SEMAPHORE");
		// setStatus(ProcessState.Terminated);

	}

	public static void main(String[] args) throws Exception {
		//proccessA();
		proccessB();

	}

}