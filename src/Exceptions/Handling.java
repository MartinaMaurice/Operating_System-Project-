package Exceptions;

import java.util.ArrayList;
import java.util.*;
import java.util.Random;
import java.util.Scanner;

public class Handling {
	public static Object[] memory = new Object[25];

	static ProcessState ps;
	static String a;
	static String DiskState = "Idle";

	public static events GenerateRandomEvent() {
		int pick = new Random().nextInt(events.values().length);
		return events.values()[4];
	}

	public static OperationState GenerateRandomState() {
		int OpS = new Random().nextInt(OperationState.values().length);
		return OperationState.values()[OpS];
	}

	public static void main(String[] args) {
		// ArrayList<Object> memory = new ArrayList<Object>();
		Handle();

	}

	static int numberModule;
	static int decimalNumber;
	static String binaryNumber = "";

	public static ProcessState Handle() {
		events k = GenerateRandomEvent();

		if (k == events.Keypress) {
			System.out.println(k);
			pressingKey();

		} else if (k == events.Disk) {
			System.out.println(k);
			Scanner input = new Scanner(System.in);
			if (input.hasNextInt()) {
				   decimalNumber = input.nextInt();					//decimal to binary
				   while(decimalNumber > 0){
					   numberModule = decimalNumber % 2;
				          binaryNumber = numberModule + "" + binaryNumber;
				          decimalNumber = decimalNumber / 2;
				        }
				        System.out.println("Binary Notation : "+binaryNumber);
				DiskState = "Busy";
				System.out.println(DiskState);
			} else {
				DiskState = "Idle";
				System.out.println(DiskState);
			}
			System.out.println(GenerateRandomState());
			ps = ProcessState.Waiting;
			System.out.println(ps);
			return ps;

		} else if (k == events.heap) {

			for (int i = 0; i < memory.length - 6; i++) {
				if (memory[i] != null) {
					memory[i] = memory[21];

				}
			}

			System.out.println(k);
		} else if (k == events.divzero) {
			/// 3aizin n3rf el div asln
			System.out.println(k);
			devidingByZero();
		} else if (k == events.priviligeM) {

			System.out.println(k);
			privilageMem();
		}
		return ps;

	}

	// heap (make arraylist, sagel kol 7aga fl array ka variable, w ngib el index el
	// 3aizino b loop, w replacement
	public static ProcessState pressingKey() {
		Scanner sc = new Scanner(System.in); // System.in is a standard input stream
		char c = sc.next().charAt(0);
		ps = ProcessState.Waiting;
		System.out.println(c);
		System.out.println("Process State: " + ps);
		memory[19] = c;

		return ps;
	}

	public static ProcessState devidingByZero() {

		Scanner sc = new Scanner(System.in);
		int in = sc.nextInt();
		if (in != 0) {
			ps = ProcessState.Terminated;
			System.out.println(ps);
			return ps;

		} else {
			System.out.println("0");

		}
		ps = ProcessState.Running;
		System.out.println(ps);
		return ps;

	}

	public static void privilageMem() {

		memory[21] = "hi"; /// FOR TESTING

		memory[14] = memory[20];
		memory[15] = memory[21];
		memory[16] = memory[22];
		memory[17] = memory[23];
		memory[18] = memory[24];

		System.out.println(memory[15]); /// FOR TESTING

	}
}
