package old2_3;
/*
ID: derekl3
LANG: JAVA
TASK: nocows
 */

import java.io.*;
import java.util.*;

public class nocows2 {
	static int N;
	static int K;
	static int nk[][];
	static int solve(int n, int k) { //n is num nodes, k is 
		System.out.println("entering search: n = " + n + " k = " + k );
		//handle base case
		if (k == 1) return n == 1? 1 : 0;
		//dynamic programming
		if (nk[n][k] != -1) return nk[n][k];
		nk[n][k] = 0;

		//cycle through possible numbers of nodes 
		for (int nA = 1; nA + 1 < n; nA++) { // n > 2*k - 1 is minimum, i < 2^k is full tree
			//nC is current node num
			int nB = n - nA - 1; //nodes left - nodes we're spending - current node is active;
			for (int j = 1; j < k; j++	) { //going through heights
				nk[n][k] += solve(nA, j) * solve(nB, k - 1); //one of the two is chosen to finish to k, other goes to j
				nk[n][k] += solve(nA, k - 1) * solve(nB, j); 
				nk[n][k] %= 9901;
			}
			nk[n][k] += solve(nA, k - 1) * solve(nB, k - 1); //both are chosen to finish k
		}
		return nk[n][k];
	}
	public static void main(String[] args) {
		N = 3;
		K = 2;
		nk = new int[N+1][K+1];
		for (int[] i: nk) {
			Arrays.fill(i, -1);
		}
		nk[1][1] = 1;

		System.out.println(solve(N, K));
		System.out.println(nk[N][K]);
	}
}
