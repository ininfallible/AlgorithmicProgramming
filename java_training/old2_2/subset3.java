package old2_2;
/*
ID: derekl3
LANG: JAVA
TASK: subset
 */

import java.io.*;
import java.util.Arrays;

public class subset3 {
	static int[][] S;
	public static int cA(int in) {
		return ((1+in)*in)/2; //return arithmetic
	}
	public static int sol(int t, int n) { //search using dfs target, num
		//System.out.println(t + " " + n);
		if (t < 0 || n < 0) return 0; //base case 1: no sol
		if (S[t][n] != -1) return S[t][n];
		if (t == 0 && n == 0) return S[t][n] = 1; //base case 2: sol by 0,0
		return sol(t, n-1) + sol(t-n, n-1);
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader fin = new BufferedReader(new FileReader("subset.in"));
		PrintWriter fout = new PrintWriter(new BufferedWriter( new FileWriter("subset.out")));
		int in = Integer.parseInt(fin.readLine());
		fin.close();
		
		if (cA(in) % 2 == 1) fout.println(0);
		else { 
			S = new int[cA(in)/2+1][in+1];
			for (int[] i : S) Arrays.fill(i, -1);
			fout.println(sol(cA(in)/2, in)/2);
		}
		fout.close();
	}

}
