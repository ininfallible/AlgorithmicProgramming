//2016 US Open Gold P3

import java.io.*;
import java.util.*;

public class TwoFourEight {

	public static void main(String[] args) throws IOException{
		BufferedReader fin = new BufferedReader(new FileReader("248.in"));
		PrintWriter fout = new PrintWriter(new BufferedWriter(new FileWriter("248.out")));
		int N = Integer.parseInt(fin.readLine());
		int[] n = new int[N];
		for (int i = 0; i < N; i++){
			n[i] = Integer.parseInt(fin.readLine());
		}
		fin.close();
	
		//solution: find the max for every subsequence of the input
		//start, end (inclusive)
		int[][] dp = new int[N+1][N+1];
		int sol = 1;
		for (int l=1;l<=N;l++) { 
			for (int s=0;s<=N-l;s++) {
				int e = s+l;
				dp[s][e]=-1;
				//initialize array
				if (l==1) dp[s][e] = n[s];
				//middle split
				for (int m=s;m<e;m++) {
					if (dp[s][m]==dp[m][e]&&dp[s][m]>0) dp[s][e] = dp[s][m]+1;
				}
				sol = Math.max(sol, dp[s][e]);
			}
		}
		fout.println(sol);
		fout.close();
	}
}
