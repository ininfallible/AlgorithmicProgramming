package temp;/*
ID: derek.l3
LANG: JAVA
TASK: temp.spin
*/
import java.io.*;
import java.util.*;

public class spin {
	public static void main(String[] args) throws IOException {
		//read file
		BufferedReader fin = new BufferedReader(new FileReader("temp.spin.in"));
		PrintWriter fout = new PrintWriter(new BufferedWriter(new FileWriter("temp.spin.out")));
		
		StringTokenizer sT;
		
		ArrayList<BitSet> discs = new ArrayList<>(); //discs
		int[] P = new int[5]; //Position of each disc
		int[] S = new int[5]; //Speed of each disc
		
		for (int i = 0; i < 5; i++) {
			sT = new StringTokenizer(fin.readLine());
			S[i] = Integer.parseInt(sT.nextToken()); //set speed
			int nW = Integer.parseInt(sT.nextToken()); //number of wedges
			BitSet curDisc = new BitSet(360);
			for (int j = 0; j < nW; j++) {
				int start = Integer.parseInt(sT.nextToken());
				int extent = Integer.parseInt(sT.nextToken());
				for (int k = 0; k <= extent; k++) curDisc.set((start + k + 359) % 360, true);
			}
			discs.add(curDisc);
		}
		fin.close();
		//debug
		for (int l = 0; l < discs.size(); l++) {
			for (int m = 0; m < 360; m++) 
				System.out.print((discs.get(l).get(m)? "T" : "F") + "");
			System.out.println("\n");
		}
		
		//calculations
		BitSet check = new BitSet(360); //disc to check
		int i;
		for (i = 0; i < 20000; i++) {
			check.set(0, 360);
			for (int j = 0; j < 5; j++) { //try each disc
				BitSet curDisc = discs.get(j); 
				for (int k = P[j]; k < 360 + P[j]; k++) { //go through all values
					if (!curDisc.get(k % 360)) { //account for rotation
						check.set(k % 360, false);
					}
				}
				P[j] = (P[j] + S[j]) % 360; //rotate that disc 
			}
			//debug
			if (i == 357) {
				for (int m = 0; m < 360; m++) 
					System.out.print((check.get(m)? "T" : "F"));
				System.out.println();
			}
			if (check.cardinality() > 0) break;
		}

		if (i == 20000) fout.println("none");
		else fout.println(i);
		fout.close();	
	}
}
//read file
//represent wheels
//bitset of length 360
//detect cardinality
