/*
ID: derekl3
LANG: JAVA
TASK: lamps
*/

import java.io.*;
import java.util.*;

public class lamps {                       //0   1   2      3      4
	static int[][] possible  = new int[][] {{0},{1},{0,2},{1,3},{0,2,4}};
	static ArrayList<BitSet> tP = new ArrayList<>();
	
	static void gP(BitSet in, int card, int p) { // generate possible on/off combos based on numbers in array
		if (in.cardinality() == card) {
			tP.add(in);
			return;
		} else if (p <= 3) {
			gP((BitSet) in.clone(), card, p+1); //light on at p
			in.set(p);
			gP((BitSet) in.clone(), card, p+1); //light off at p
		}
		return;
	} 
	
	static BitSet flip(BitSet in, BitSet lamps) {
		BitSet s = (BitSet) in.clone();
		BitSet l = (BitSet) lamps.clone();
		//can be optimized
		if (s.get(0)) {
			l.flip(0, l.size()); //all lamps
		}
		if (s.get(1)) {
			for (int i = 0; i < l.size(); i += 2) l.flip(i); // even lamps
		}
		if (s.get(2)) {
			for (int i = 1; i < l.size(); i += 2) l.flip(i); //odd lamps
		}
		if (s.get(3)) {
			for (int i = 0; i < l.size(); i += 3) l.flip(i); // every third lamp
		}
		return l;
	}
	static boolean check(int[] req, BitSet lamps) {
		for (int i = 0; i < req.length; i++) {
			if (req[i] == 0 && lamps.get(i)) {
				return false;
			}
			if (req[i] == 1 && !lamps.get(i)) {
				return false;
			}
		}
		return true;
	}

	static class lComp implements Comparator<BitSet> {
		public int compare(BitSet a, BitSet b) {
			for (int i = 0; i < a.size(); i++) {
				if (a.get(i) && !b.get(i)) return 1;
				if (!a.get(i) && b.get(i)) return -1;
			}
			System.out.println("exit");
			return 0;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader fin = new BufferedReader(new FileReader("lamps.in"));
		PrintWriter fout = new PrintWriter(new BufferedWriter(new FileWriter("lamps.out")));
		int N = Integer.parseInt(fin.readLine());
		int C = Integer.parseInt(fin.readLine());
		//int C = 1;
		//int N = 10;
		int[] req = new int[N]; //indexing from 0
		Arrays.fill(req, -1); //-1 is default for req, means no req
		StringTokenizer sT = new StringTokenizer(fin.readLine());
		//System.out.println("P1");

		while (sT.hasMoreTokens()) {
			int temp = Integer.parseInt(sT.nextToken())-1;
			if (temp != -2) req[temp] = 1;
		} 
		sT = new StringTokenizer(fin.readLine());
		while (sT.hasMoreTokens()) {
			int temp = Integer.parseInt(sT.nextToken())-1;
			if (temp != -2) req[temp] = 0;
		}
		fin.close();
		//System.out.println("P1");
		
		BitSet lamps = new BitSet(N); //finish making variables
		lamps.set(0,lamps.size());
		ArrayList<BitSet> out = new ArrayList<>();
		
		int[] p; //get all possible switch configurations 
		if (C < 5) {
			p = possible[C];
		} else {
			p = possible[3 + (C % 2)];
		}
		
		for (int q : p) gP(new BitSet(4), q, 0);
		
		for (BitSet i : tP) { //add valid lamp configs
			if (check(req, flip(i, lamps)))out.add(flip(i, lamps));
			
		}
		
		Collections.sort(out, new lComp());
		
		if (out.size() > 0) {
			for (BitSet i : out) { // debug
				for (int j = 0; j < N; j++) {
					if(i.get(j)) fout.print(1);
					else fout.print(0);
				}
				fout.println();
			}
		}else {
			fout.println("IMPOSSIBLE");
		}
		//System.out.println();
		fout.close();
	}
}
