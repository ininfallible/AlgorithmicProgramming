/*
ID: derekl3
LANG: JAVA
TASK: cowtour
*/

import java.io.*;
import java.util.*;

public class cowtour2 {
	static double cDist(int[] a, int[] b){
		return Math.sqrt(
				Math.pow(Math.abs(a[0]-b[0]), 2) + 
				Math.pow(Math.abs(a[1]-b[1]), 2));
	}
	static void dfs(int in, int n) {
		PN[in] = n; //updates pasture grouping
		for (int i = 0; i < P; i++) {
			if (PN[i] == 0 && C[in][i] != 0) {
				dfs(i, n);
			}
		}
	}
		
	static int[] PN; //field number per pasture
	static int P; //number of pastures
	static double[][] C; //connection: [a][b] = dist from a -> b, 0 means not connected
	static int[][] L; //location
	public static void main(String[] args) throws IOException {
		//read file
		BufferedReader fin = new BufferedReader(new FileReader("cowtour.in"));
		PrintWriter fout = new PrintWriter(new BufferedWriter(new FileWriter("cowtour.out")));	
		P = Integer.parseInt(fin.readLine()); //number of pastures	
		StringTokenizer sT;
		C = new double[P][P];
		for (double[] i : C) {Arrays.fill(i, 0);} //sets up C
		L = new int[P][2]; //coordinate pairs
		for (int i = 0; i < P; i++) {//assembles locations
			sT = new StringTokenizer(fin.readLine());
			L[i][0] = Integer.parseInt(sT.nextToken());
			L[i][1] = Integer.parseInt(sT.nextToken());
		}
		for (int j = 0; j < P; j++) {//calculates distances for edge chart
			String[] ln = fin.readLine().split("");
			for (int k = 0; k < P; k++) {
				if (ln[k].equals("1")) {
					C[j][k]	= cDist(L[j], L[k]); 
				}	
			}
		}
		fin.close();
		//finished reading file
		System.out.println("creating fields");
		//group pastures into fields
		int m = 1; //pasture num
		PN = new int[P]; //PN[x] returns field number of pasture x;
		for (int l = 0; l < P; l++) {
			if (PN[l] == 0) {
				dfs(l, m++);
			} 
		}	
		System.out.println("starting f-W");
		//connect everything to everything else using floyd warshall
		for (int n = 0; n < P; n++) { //intermediate pasture
			for (int o = 0; o < P; o++) { //first pasture
				for (int p = 0; p < P; p++) { //second pasture
					if (C[o][n] + C[n][p] < C[o][p] && C[o][n] != 0 && C[n][p] != 0) {
						C[o][p] = C[o][n] + C[n][p];
					}
				}
			}
		}
		System.out.println("connecting pastures");
		//try connecting stuff and calculating diameter
		double out = 1000000;
		for (int p = 0; p < P; p++) {
			for (int q = 0; q < P; q++) {
				if (PN[p] != PN[q]) {
					double pF = 0;
					double qF = 0;
					for (double r : C[p]) {pF = Math.max(r, pF); }
					for (double s : C[q]) {qF = Math.max(s, pF); }
					out = Math.min(out, pF + qF + cDist(L[p], L[q]));
					System.out.println(out);
				}
			}	
		}
		System.out.println(out);
		fout.println(out);
		fout.close();
	}
}
