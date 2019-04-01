/*
ID: derekl3
LANG: JAVA
TASK: comehome
*/

import java.io.*;
import java.util.*;

public class comehome {	
	static int lN(String in) {
		int out = 0;
		if (Character.isUpperCase(in.charAt(0))) out += 26;
		out += Character.toLowerCase(in.charAt(0)) - 'a';
		return out;
	}
	
	static char nL(int in) {
		return (char) (in + 39);
	}
	
	public static void main(String[] args) throws IOException {

		BufferedReader fin = new BufferedReader(new FileReader("comehome.in"));
		PrintWriter fout = new PrintWriter(new BufferedWriter(new FileWriter("comehome.out")));

		int[][] G = new int[52][52]; //graph
		for (int[] temp : G) {Arrays.fill(temp, 1000000);}
		boolean[] C = new boolean[52]; //cows
		int P = Integer.parseInt(fin.readLine());
		StringTokenizer sT;
		System.out.println(lN("Z"));
		//create adjacency graph
		for (int k = 0; k < P; k++) {		
			sT = new StringTokenizer(fin.readLine());
			String a = sT.nextToken();
			String b = sT.nextToken();
			int c = Integer.parseInt(sT.nextToken());
			if (!a.toLowerCase().equals(a)) {C[lN(a)] = true;}
			G[lN(a)][lN(b)] = Math.min(G[lN(a)][lN(b)], c);
			G[lN(b)][lN(a)] = Math.min(G[lN(b)][lN(a)], c);
		}
		C[51] = false;
		fin.close();
		//perform floyd-warshall
		for (int m = 0; m < 52; m++) { //intermediate vertex
			for (int n = 0; n < 52; n++) { //first vertex
				for (int o = 0; o < 52; o++) { // second vertex
					if (G[n][m] + G[m][o] < G[n][o]) {
						G[n][o] = G[n][m] + G[m][o];
//						G[o][n] = G[n][m] + G[m][o];
					}
				}
			}	
		}
		int out = 1000000;
		int out2 = 0;
		for (int p = 0; p < 52; p++) {
			if(C[p] && G[51][p] < out) {
				out = Math.min(G[51][p], out);
				out2 = p;
			}
		}
		for (int q : G[51]) {System.out.print(q + " ");}
		System.out.println();
		for (boolean r : C) {System.out.print(r + " ");}
		System.out.print("\nout2 " + out2);

		fout.println(nL(out2) + " " + out);
		fout.close();
	} 
}
