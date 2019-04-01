/*
ID: derekl3
LANG: JAVA
TASK: holstein
 */

import java.io.*;
import java.util.*;

public class holstein {
	static int v;
	static int f;
	static int[] req;
	static int[][] fT;
	
	static boolean vC(BitSet in) { //validCombo
		int[] a = req.clone();
		for (int i = 0; i < f; i++) {
			if (in.get(i)) {
				for (int j = 0; j < v; j++) {
					a[j] -= fT[i][j];
				}
			}
		}
		
		for (int k : a) {
			if (k > 0) return false;
		}
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader fin = new BufferedReader(new FileReader("holstein.in"));
		PrintWriter fout = new PrintWriter(new BufferedWriter(new FileWriter("holstein.out")));
		v = Integer.parseInt(fin.readLine()); //num vitamins	
		req = new int[v];
		StringTokenizer sT = new StringTokenizer(fin.readLine());
		int c = 0; //temp counter
		while (sT.hasMoreTokens()) {
			req[c] = Integer.parseInt(sT.nextToken());
			c++;
		}
		f = Integer.parseInt(fin.readLine()); //num feed
		fT = new int[f][v]; //feedtype 
		for (int c2 = 0; c2 < f; c2++) {
			c = 0;
			sT = new StringTokenizer(fin.readLine());
			while (sT.hasMoreTokens()) {
				fT[c2][c] = Integer.parseInt(sT.nextToken());
				c++;
			}
		}
		
		fin.close();
		
		
		BitSet temp = new BitSet(v);
		LinkedList<BitSet> tP = new LinkedList<>(); //toProcess
		tP.add(temp);
		ArrayList<BitSet> ans = new ArrayList<>();
		boolean found = false;
		
		while (!tP.isEmpty()) {
			BitSet cur = tP.pop();
			
			found = found || vC(cur); //check cur state
			//System.out.println(vC(cur) + " " + found + " " + cur.cardinality() + " " + cur.size()); //debug
			
			if (cur.cardinality() <= v && !found) {//select avalible bit, toggle
				//System.out.println("cur processed");
				int i = 0; // sets i
				if (cur.cardinality() > 0) {
					i = cur.previousSetBit(cur.size())+1;
					//System.out.println("set");
				}
				
				for(;i < f; i++) {
					if (cur.get(i) == false) {
						cur.set(i);
						tP.add((BitSet) cur.clone()); //repeat bfs
						cur.clear(i);
					}
				}	
			}
			if (vC(cur)) {
				ans.add(cur);
			}
			
			//for (int j = 0; j < f; j++) System.out.print(cur.get(j) + " ");
			//System.out.println();
			
		}
		
		/*for (BitSet i : ans) {
			for (int j = 0; j < f; j++) System.out.print(i.get(j) + " ");
			System.out.println();
		}*/
		String out = "";
		int count = 0;
		for (int j = 0; j < f; j++) {
			if (ans.get(0).get(j)) {
				count++;
				out += Integer.toString(j+1) + " ";
			}
		}
		out = Integer.toString(count) + " " + out;
		out = out.trim();
		System.out.println(out);
		fout.println(out);
		fout.close();
		
	}
}
