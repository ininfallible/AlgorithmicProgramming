package old3_1;/*
ID: derekl3
LANG: JAVA
TASK: old3_1.humble
*/

import java.io.*;
import java.util.*;

public class humble2 {

	public static void main(String[] args) throws IOException {
		BufferedReader fin = new BufferedReader(new FileReader("old3_1.humble.in"));
		PrintWriter fout = new PrintWriter(new BufferedWriter(new FileWriter("old3_1.humble.out")));
		StringTokenizer sT = new StringTokenizer(fin.readLine());
		int K = Integer.parseInt(sT.nextToken());
		int N = Integer.parseInt(sT.nextToken());
		int[] S = new int[K];
		sT = new StringTokenizer(fin.readLine());
		for (int i = 0; i < K; i++) S[i] = Integer.parseInt(sT.nextToken());
		fin.close();
		
		BitSet sol = new BitSet(200000);
		for (int j = 0; j < K; j++) sol.set(S[j]);
		long cur = 1; //number we're checking
		int n = 0; //number of numbers old3_1.humble
		while (n < N) {
			if (sol.get((int) cur)) {
				for (int j : S) sol.set(j * (int) cur);
				n++;
			}
			cur++;
		}
		fout.println(cur-1);
		fout.close();
	}
}
