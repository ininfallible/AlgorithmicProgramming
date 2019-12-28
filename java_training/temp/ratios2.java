package temp;/*
ID: derek.l3
LANG: JAVA
TASK: temp.ratios
*/
import java.io.*;
import java.util.*;

public class ratios {
	//temp.ratios
	static int r1;
	static int r2;
	static int r3;
	//dp of whether it works or not
	static int[][][] dp;
	//array of mixes
	static int[][] mixes;
	static int nMixes;
	//check up to n
	static int n = 1000; 

	public static boolean cR (int f1, int f2, int f3) { //check temp.ratios
		if (f1 == 0 || f2 == 0 || f3 == 0) return false; //avoid div by 0
		if ((double) f1 / f2 != (double) r1 / r2) return false; //first and second
		if ((double) f1 / f3 != (double) r1 / r3) return false; //first and third
		if ((double) f2 / f3 != (double) r2 / r3) return false; //second and third
		return true;
	}
	
	public static int[] sol(int a, int b, int c) {
		int[][] opts = new int[nMixes + 1][3]; //options: children, itself
		//stop branch if visited
		if (dp[a][b][c] == 1 || a>=n || b>=n || c>=n) return new int[]{-1};
		//check if current location works
		if (cR(a, b, c)) sol[nMixes] = new int[] {a, b, c};
		else sol[nMixes] = new int[] {-1};
		//branch if not visited before
		if (dp[a][b][c] == 0) {
			dp[a][b][c] = 1;
			for (int i = 0; i < nMixes; i++) { //go through all children
				opts[i] = sol(a + mixes[i][0], b + mixes[i][1], c + mixes[i][2]);
			}
		}
		//decide what to return
		int min = 10000;
		int minIndex = nMixes; //default to this node
		for (int j = 0; j < nMixes + 1; j++) {
			if (opts[j][0] != -1 && opts[j][0] < min) {
				min = opts[j][0];
				minIndex = j;
			}
		}
		return opts[minIndex];
	}
	
	public static void main(String[] args) throws IOException {
		//read file
		BufferedReader fin = new BufferedReader(new FileReader("temp.ratios.in"));
		PrintWriter fout = new PrintWriter(new BufferedWriter(new FileWriter("temp.ratios.out")));
		StringTokenizer sT = new StringTokenizer(fin.readLine());

		//vars for algorithm
		r1 = Integer.parseInt(sT.nextToken());
		r2 = Integer.parseInt(sT.nextToken());
		r3 = Integer.parseInt(sT.nextToken());
		nMixes = 0;
		mixes = new int[100][3];
		dp = new int[n][n][n]; //barley, oats, wheat
		fin.close();

		String ln;
		while ((ln = fin.readLine()) != null) {
			sT = new StringTokenizer(ln);
			mixes[nMixes][0] = Integer.parseInt(sT.nextToken());
			mixes[nMixes][1] = Integer.parseInt(sT.nextToken());
			mixes[nMixes][2] = Integer.parseInt(sT.nextToken());
			nMixes++;
		}

		//algorithm
		int[] out = sol(0,0,0);
		System.out.println(out[0]+" "+out[1]+" "+out[2]);
		fout.println(out[0]+" "+out[1]+" "+out[2])
		fout.close();
	}
}
