package old2_2;
/*
ID: derekl3
LANG: JAVA
TASK: runround
 */

import java.util.*;
import java.io.*;

public class runround {
	static int in;
	static int out = 0;
	static boolean found = false;
	static boolean check (int[] in) {
		int[] C = new int[in.length]; //Check
		int digit = 0;
		int j = 0;
		for (int i = 0; i < in.length; i++) {
			C[j] = in[j];
			j = (j + in[j]) % in.length;
		}
		//for (int m : C) System.out.print(m + " ");
		if (j != 0) return false; // if doesn't loop around
		for (int k = 0; k < in.length; k++) {//don't check for duplicates
			//for (int l = k + 1; l < in.length; l++) if(in[k] == in[l]) return false;
			if (C[k] == 0) return false;
		}
		return true;
	}
	static void permutate(boolean[] u, int[] a, int n, int p) { //u is used, a is array, n is number, p is current pos, length of a is target length
		//handle solution case
		if (found) return;
		if (p == a.length && !found && n > in && check(a)) {out = n; found = true; return;}
		if (p < a.length) {
			for (int cur = 1; cur < 10; cur++) {
				if (!u[cur]) {
					u[cur] = true;
					a[p] = cur;
					int next = n * 10 + cur;
					permutate(u.clone(), a, next, p+1);
					u[cur] = false;
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader fin = new BufferedReader(new FileReader("runround.in"));
		PrintWriter fout = new PrintWriter(new BufferedWriter(new FileWriter("runround.out")));
		in = Integer.parseInt(fin.readLine());
		fin.close();
		//in = 99;
		for (int i = 1; i <= 9; i++) {
			permutate(new boolean[10], new int[i], 0, 0);
			if (found) break;
		}
		System.out.println(out);
		//generate numbers in sequence
		
		fout.println(out);
		fout.close();
	}
}
