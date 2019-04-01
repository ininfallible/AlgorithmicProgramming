package temp;/*
ID: derek.l3
LANG: JAVA
TASK: temp.ratios
*/

import java.io.*;
import java.util.*;

public class ratios {
	static int r[][] = new int[4][3]; //r[0][] for target, r[1-3][for mixes]
	public static int check (int[] in) { //zero if false, ratio if true
		for (int i = 0; i < 3; i++)
			//check if in is divisible by each of numbers in ratio
			//elimates casting to double
			//if in is not divisible all terms in r it's not a solution
			if(r[0][i] != 0 && in[i] % r[0][i] != 0) return 0; 

		int out;
		if (r[0][0] != 0) out = in[0] / r[0][0]; //ratio from first if nonzero
		else if (r[0][1] != 0) out = in[1] / r[0][1]; //ratio from second if nonzero
		else if (r[0][2] != 0) out = in[2] / r[0][2]; //ratio from third if nonzero
		else out = 0; //if all zero out = 0;

		if (r[0][0] * out != in[0] || r[0][1]*out != in[1] || r[0][2]*out != in[2]) return 0; 
		//check by multiplying
		return out;
	}

	public static void main (String[] args) throws IOException {
		BufferedReader fin = new BufferedReader(new FileReader("temp.ratios.in"));
		PrintWriter fout = new PrintWriter(new BufferedWriter(new FileWriter("temp.ratios.out")));
		StringTokenizer ln;
		for (int i = 0; i < 4; i++) {
			ln = new StringTokenizer(fin.readLine());
			for (int j = 0; j < 3; j++) r[i][j] = Integer.parseInt(ln.nextToken());
		}
		fin.close();
		//algorithm
		int[] sol = new int[3];
		for (sol[0] = 0; sol[0] < 101; sol[0]++)
		for (sol[1] = 0; sol[1] < 101; sol[1]++)
		for (sol[2] = 0; sol[2] < 101; sol[2]++) {
			int[] cur = new int[3];
			for (int i = 0; i < 3; i++) { //assemble cur based on sol
				for (int j = 0; j < 3; j++) {
					cur[j] += r[i+1][j] * sol[i];
				}
			}
			int m;
			if ((m = check(cur)) != 0) {
				fout.println(sol[0] + " " + sol[1] + " " + sol[2] + " " + m);
				fout.close();
				return;
			}
		}
		fout.println("NONE");
		fout.close();
		return;
	}
}
