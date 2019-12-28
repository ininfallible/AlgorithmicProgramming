package old3_1;/*
ID: derekl3
LANG: JAVA
TASK: old3_1.inflate
*/

import java.io.*;
import java.util.*;

public class inflate {
	public static void main(String[] args) throws IOException {
		BufferedReader fin = new BufferedReader(new FileReader("old3_1.inflate.in"));
		PrintWriter fout = new PrintWriter(new BufferedWriter(new FileWriter("old3_1.inflate.out")));
		StringTokenizer sT = new StringTokenizer(fin.readLine());
		//read file
		int M = Integer.parseInt(sT.nextToken()); //minutes
		int N = Integer.parseInt(sT.nextToken()); //types of problems
		int[] V = new int[N+1]; //value
		int[] T = new int[N+1]; //times
		int[] max = new int[M+1]; //max value for any given weight
		//assemble V and T
		for (int i = 1; i <= N; i++) {
			sT = new StringTokenizer(fin.readLine());
			V[i] = Integer.parseInt(sT.nextToken());
			T[i] = Integer.parseInt(sT.nextToken());
		}
		fin.close();
		//dp
		for (int j = 1; j <= M; j++) {
			for (int k = 1; k <= N; k++) {
				if (j - T[k] < 0) continue;
				max[j] = Math.max(max[j], max[j - T[k]] + V[k]);
			}
		} 
		fout.println(max[M]);
		fout.close();
	}
}
