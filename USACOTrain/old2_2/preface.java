package old2_2;
/*
ID: derekl3
LANG: JAVA
TASK: preface
*/
import java.io.*;

public class preface {						
	static final int[] V = new int[] {1,4,5,9,10,40,50,90,100,400,500,900,1000}; //values
	static final int[][] R = new int[][]{{0},{0,1},{1},{0,2},{2},{2,3},{3},{2,4},{4},{4,5},{5},{4,6},{6}}; //roman (13)
	static final String[] S = new String[] {"I","V","X","L","C","D","M"};
	static int[] out = new int[7];
	static void romanFreq (int in) {
		for (int i = 12; i >= 0; i--) {//add
			if (in - V[i] >= 0) {
				in -= V[i];
				for (int j : R[i]) out[j]++;
				if(R[i].length == 1) i++;//multiple times
			} 
		}
		System.out.println("P1");
		return;
	}
	int[] freq = new int[7]; //I V X L C D M
	public static void main (String[] args) throws NumberFormatException, IOException{
		BufferedReader fin = new BufferedReader(new FileReader("preface.in"));
		PrintWriter fout = new PrintWriter(new BufferedWriter(new FileWriter("preface.out")));
		int in = Integer.parseInt(fin.readLine());
		fin.close();
		for (int i = 1; i <= in; i++) {
			romanFreq(i);
		}
		for (int j = 0 ; j < 7; j++) if (out[j]!=0) fout.println(S[j] + " " + out[j]);
		fout.close();
	} 
}
