package temp;/*
ID: derekl3
LANG: JAVA
TASK: temp.kimbits
*/

import java.io.*;
import java.util.*;

public class kimbits {
	
	public static void main(String[] args) throws IOException {
		BufferedReader fin = new BufferedReader(new FileReader("temp.kimbits.in"));
		PrintWriter fout = new PrintWriter(new BufferedWriter(new FileWriter("temp.kimbits.out")));
    	StringTokenizer sT = new StringTokenizer(fin.readLine());
    	int N = Integer.parseInt(sT.nextToken()); //max string length
    	int L = Integer.parseInt(sT.nextToken()); //max number of bits in string
		long I = Long.parseLong(sT.nextToken()); //string in sorted list
    	fin.close();
		//assemble dp
		int[][] dp = new int[N+1][L+1]; //length, bits 
		Arrays.fill(dp[0], 1);
		for (int i = 1; i <= N; i++) { //length
			for (int j = 0; j <= L; j++) { //number of bits used
				if (j == 0) {
					dp[i][j] = 1;
				} else {
					dp[i][j] += dp[i-1][j] + dp[i-1][j-1];
				}
			}
		}
		//debug
		for (int[] j : dp) {
			for (int k: j) System.out.print(k + " ");
			System.out.println();
		}
		//solution
		int nBits = N;
		int nOnes = L;
		long index = I-1;

		while (true) {
			if (nBits == 0) break;
			nBits--;
			int cur = dp[nBits][nOnes];
			if (cur <= index) {
				nOnes--;
				index -= cur;
				fout.print("1");
			} else {
				fout.print("0");
			}
		}
		fout.println();
    	fout.close();
	}
}
