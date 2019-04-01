/*
ID: derekl3
LANG: JAVA
TASK: ttwo
*/

import java.io.*;
import java.util.*;

public class ttwo { //simulate walk until they meet
	static boolean inBounds(int[] rC) { //index from 0
		return rC[0] >= 0 && rC[0] < 10 && rC[1] >= 0 && rC[1] < 10 && !f[rC[0]][rC[1]].equals("*");
	}
	static String[][] f = new String[10][10]; //field
	static int[] C = new int[2], F = new int[2]; //positions
	final static int[] DR = new int[] {-1,0,1,0};
	final static int[] DC = new int[] {0,1,0,-1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader fin = new BufferedReader(new FileReader("ttwo.in"));
		PrintWriter fout = new PrintWriter(new BufferedWriter( new FileWriter("ttwo.out")));
		for (int i = 0; i < 10; i++) f[i] = fin.readLine().split("");
		fin.close();
		
		for (String[] x : f) System.out.println(String.join("", x));
		for (int r = 0; r < 10; r++) { //row
			for (int c = 0; c < 10; c++) { //col
				if (f[r][c].equals("F")) {F[0] = r; F[1] = c;}
				if (f[r][c].equals("C")) {C[0] = r; C[1] = c;}
			}
		}
		int min;
		int cd = 0; //nesw, 0123
		int fd = 0;
		for (min = 1; min <= 5000; min++) {
			//System.out.println("C: " + C[0] + " " + C[1] + " F: " + F[0] + " " + F[1]);
			int[] nC = new int[] {C[0] + DR[cd], C[1] + DC[cd]};
			int[] nF = new int[] {F[0] + DR[fd], F[1] + DC[fd]};
			
			if (!inBounds(nC)) {cd += 1; cd %= 4;}
			else {C = nC.clone();}
;
			if (!inBounds(nF)) {fd += 1; fd %= 4;}
			else {F = nF.clone();}
				
			if (Arrays.equals(C, F)) break;
		}
		fout.println(min % 5001);
		fout.close();
	}
}

