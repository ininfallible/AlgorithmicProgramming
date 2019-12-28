package old3_1;/*
ID: derekl3
LANG: JAVA
TASK: old3_1.stamps
*/

import java.io.*;
import java.util.*;

public class stampsEx {
	public static void main(String[] args) throws IOException {
		//read file
		int nS = 5; //number of old3_1.stamps
		int tS = 2; //types of old3_1.stamps
		int[] stamps = new int[]{1, 3};
		//dp solution
		int[] sU = new int[2000000]; //old3_1.stamps used
		Arrays.fill(sU, 2000000);
		sU[0] = 0;
		int j; //current number being checked
		for (j = 1; j < 2000000; j++) {
			//knapsack
			for (int k : stamps) { //current total being checked
				System.out.println("number, value being used: " + j + " " + k);
				if (j - k >= 0) sU[j] = Math.min(sU[j], sU[j - k] + 1);
			}
			if (sU[j] > nS) {System.out.println(j + " is invalid, exiting"); break;} //if there's sol for j
		}
		System.out.println(j-1);
	}
}
