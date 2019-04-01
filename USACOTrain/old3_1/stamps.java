package old3_1;/*
ID: derekl3
LANG: JAVA
TASK: old3_1.stamps
*/

import java.io.*;
import java.util.*;

public class stamps {
	public static void main(String[] args) throws IOException {
		//read file
		BufferedReader fin = new BufferedReader(new FileReader("old3_1.stamps.in"));
		PrintWriter fout = new PrintWriter(new BufferedWriter(new FileWriter("old3_1.stamps.out")));
		StringTokenizer sT = new StringTokenizer(fin.readLine());	
		int nS = Integer.parseInt(sT.nextToken()); //number of old3_1.stamps
		int tS = Integer.parseInt(sT.nextToken()); //types of old3_1.stamps
		int[] stamps = new int[tS];
		sT = new StringTokenizer(fin.readLine());
		for (int i = 0; i < tS; i++) {
			if (!sT.hasMoreTokens()) sT = new StringTokenizer(fin.readLine());
			stamps[i] = Integer.parseInt(sT.nextToken());
		}
		fin.close();
		//dp solution
		int[] sU = new int[2000000]; //old3_1.stamps used
		Arrays.fill(sU, 2000000);
		sU[0] = 0;
		int j; //current number being checked
		for (j = 1; j < 2000000; j++) {
			//knapsack
			for (int k : stamps) { //current stamp being checked
				if (j - k >= 0) sU[j] = Math.min(sU[j], sU[j - k] + 1);
			}
			if (sU[j] > nS) break; //if there's sol for j
		}
		fout.println(j-1);
		fout.close();
	}
}
