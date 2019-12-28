package old2_3;
/*
ID: derekl3
LANG: JAVA
TASK: nocows
 */

import java.io.*;
import java.util.*;

public class nocows {
	static long[][] nk;
	static int N;
	static int K;
	
	static long sol(int n, int k) {
		if (k == 1) return n == 1 ? 1 : 0; //base case
		//System.out.println("entering search " + n + " " + k);
		if (nk[n][k] != -1) return nk[n][k];
		nk[n][k] = 0;
		long out = 0;
		for (int L = 1; L + 1 < n; L += 2) {
			int R = n - L - 1;
			for (int h = 1; h + 1 < k; h++) {
				//System.out.println("h: " + h);
				out += sol(L, k-1) * sol(R, h) * 2;
				//out += sol(L, h) * sol(R, k-1);
			}
			out += sol(L, k-1) * sol(R, k-1);
		}
		out %= 9901;
		nk[n][k] = out;
		return out;
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader fin = new BufferedReader(new FileReader("nocows.in"));
		PrintWriter fout = new PrintWriter(new BufferedWriter(new FileWriter("nocows.out")));
		StringTokenizer sT = new StringTokenizer(fin.readLine());
		N = Integer.parseInt(sT.nextToken());
		K = Integer.parseInt(sT.nextToken());
		fin.close();
		nk = new long[N+1][K+1];
		for (long[] i : nk) Arrays.fill(i, -1);
		nk[1][1] = 1;
		fout.println(sol(N, K));
		fout.close();
	}
}
