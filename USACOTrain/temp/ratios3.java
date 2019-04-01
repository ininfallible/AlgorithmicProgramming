package temp;/*
ID: derek.l3
LANG: JAVA
TASK: temp.ratios
*/

import java.io.*;
import java.util.*;

public class ratios {
	static int[] r = new int[3]; //temp.ratios

	static int[][] m = new int[3][3]; //mixes 

	static int[] curM; //used later

	static int cR(int[] in) { //number of each feed
		for (int i = 0; i != 3; i++) {
			if (r[0][i] != 0 && in[i] % r[0][i] != 0)
				return 0;
		}	
	}

	public static void main(String[] args) throws IOException {
		//read the file
		BufferedReader fin = new BufferedReader(new FileReader("temp.ratios.in"));
		PrintWriter fout = new PrintWriter(new BufferedWriter(new FileWriter("temp.ratios.out")));

		StringTokenizer ln = new StringTokenizer(fin.readLine());
		for (int i = 0; i < 3; i++) r[i] = Integer.parseInt(ln.nextToken()); //assemble r

		for (int j = 0; j < 3; j++) { //assemble m
			ln = new StringTokenizer(fin.readLine());
			for (int k = 0; k < 3; k++) m[j][k] = Integer.parseInt(ln.nextToken());
		}
		fin.close();

		//bfs + dp
		int[][][] dp = new int[101][101][101]; //memoization
		LinkedList<int[]> tP = new LinkedList<>(); //process
		tP.add(new int[]{0, 0, 0});

		int[] cur = new int[3];
		while (tP.size() > 0) {
			cur = tP.pop();
			//process current
			if (cur[0] > 100 || cur[1] > 100 || cur[2] >= 100) continue; //avoid outofbound
			if (dp[cur[0]][cur[1]][cur[2]] != 0) continue; //check dp, if already visited
			dp[cur[0]][cur[1]][cur[2]] = 1; //update dp
			if (cR(cur)) break; //if solution
			else {
				//branch
				cur[0]++;
				tP.add(cur.clone());
				cur[0]--;
				cur[1]++;
				tP.add(cur.clone());
				cur[1]--;
				cur[2]++;
				tP.add(cur.clone());
				cur[2]--;
			}
		}
		System.out.println(cur[0] + " " + cur[1] + " " + cur[2]);
		fout.print(cur[0] + " " + cur[1] + " " + cur[2] + " ");
		for (int i = 0; i < 3; i++) if (curM[i] != 0) { //determine ratio
			fout.println(curM[i]/r[i]);
			break;
		}
		fout.close();
	}
}
