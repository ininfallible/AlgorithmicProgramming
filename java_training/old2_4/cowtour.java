/*
ID: derekl3
LANG: JAVA
TASK: cowtour
*/

import java.util.*;
import java.io.*;

public class cowtour {
	public static double cDist(int[] a, int[] b) {
		return Math.sqrt(Math.pow(a[0] - b[0], 2) + Math.pow(a[1] - b[1], 2));
	}
	static int P; //number of pastures
	static double[][] C; //connections, 0 is no connection
	static int[][] L; //locations
	static double INF = 2000000;
	static double[] mD; //maximum diameter for each pasture
	public static void main(String[] args) throws IOException {
		//read file
		BufferedReader fin = new BufferedReader(new FileReader("cowtour.in"));
		PrintWriter fout = new PrintWriter(new BufferedWriter(new FileWriter("cowtour.out")));
		P = Integer.parseInt(fin.readLine());
		StringTokenizer sT;
		//assemble locations
		L = new int[P][2];
		for (int i = 0; i < P; i++) {
			sT = new StringTokenizer(fin.readLine());
			L[i][0] = Integer.parseInt(sT.nextToken());
			L[i][1] = Integer.parseInt(sT.nextToken()); 
		}
		//assemble (C)onnections;
		C = new double[P][P];
		for (double[] i : C) {Arrays.fill(i, INF);} 
		for (int j = 0; j < P; j++) {
			String ln = fin.readLine();
			for (int k = 0; k < P; k++){
				if(ln.charAt(k) == '1') {
					C[j][k] = cDist(L[j], L[k]);
				}
			}
		}
		fin.close();
	
		//build C using Floyd-Warshall
		for (int l = 0; l < P; l++) { //intermediate
			for (int m = 0; m < P; m++) { //first
				for (int n = 0; n < P; n++) { //second
					if (C[m][l] + C[l][n] < C[m][n]) {
						C[m][n] = C[m][l] + C[l][n];
					}
				}
			}
		//connections to itself is 0
		for (int k = 0; k < P; k++) {
			C[k][k] = 0;
		}}

		//build mD
		double out = INF;
		mD = new double[P];
		for (int p = 0; p < P; p++) {
			for (int q = 0; q < P; q++){
				if (C[p][q] > mD[p] && C[p][q] != INF) {
					mD[p] = C[p][q];
				}
			}
		}
		//for every pair, makes sure in different pastures, calculate
		/*
		for (double[] t1 : C) {
			for (double t2 : t1) {
				System.out.print(t2 == INF ? "0" : "1");
				//System.out.print(" ");
			}
			System.out.println();
		}
		*/
		for (int p = 0; p < P; p++) {
			for (int q = 0; q < P; q++) {
				if (C[p][q] == INF) { //different pastures
					double mP = mD[p];
					double mQ = mD[q];
					out = Math.min(out, mP + mQ + cDist(L[p], L[q]));
					//System.out.println(out);
				}
			}
		}
		//if existing diameters are already greater than out
		for (double r : mD) {
			if (r > out) out = r;
		}
		out = (double) Math.round(out * 1000000)/1000000;
		fout.print(out);
		if (out % 1 == 0) {fout.print("00000");}
		fout.println();
		fout.close();
	}
}
