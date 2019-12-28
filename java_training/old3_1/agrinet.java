package old3_1;/*
ID: derekl3
LANG: JAVA
TASK: old3_1.agrinet
*/

import java.io.*;
import java.util.*;

public class agrinet {
	static int INF = 1000000;
	public static void main(String[] args) throws IOException {
		BufferedReader fin = new BufferedReader(new FileReader("old3_1.agrinet.in"));
		PrintWriter fout =  new PrintWriter(new FileWriter("old3_1.agrinet.out"));
		int F = Integer.parseInt(fin.readLine());
		StringTokenizer sT;
		//assemble graph
		int [][] G = new int[F][F];
		for (int i = 0; i < F; i++) {
			sT = new StringTokenizer(fin.readLine());
			for (int j = 0; j < F; j++) {
				if (!sT.hasMoreTokens()) sT = new StringTokenizer(fin.readLine());
				G[i][j] = Integer.parseInt(sT.nextToken());
			}
		}
		fin.close();
		//set up tree
		int[][] T = new int[F][3]; //distance, intree, source
		for (int[] k : T) k[0] = INF;
		//use Prim's algorithm
		int tS = 1; //treeSize
		int tC = 0; //treeCost
		T[0][1] = 1; // adds node 0 to tree
		for (int l = 0; l < F; l++){
			T[l][0] = G[0][l]; //update neighbors in tree	
		}
		while (tS < F) {
			//check for closest node to tree
			int minNode = INF;
			int minDist = INF;
			for (int m = 0; m < F; m++) {
				if (T[m][0] < minDist && T[m][1] == 0) {
					minDist = T[m][0];
					minNode = m;
				}
			}
			tS++;
			tC += minDist;
			T[minNode][1] = 1;
			for (int n = 0; n < F; n++) {
				//update nodes connected to minNode
				if (G[minNode][n] < T[n][0]) {
					T[n][0] = G[minNode][n];
					T[n][2] = minNode;
				}
			}
		}
		System.out.println(tC);
		fout.println(tC);
		fout.close();
	}
}
