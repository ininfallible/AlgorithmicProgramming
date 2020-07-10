import java.io.*;
import java.util.*;

public class bbreeds {
	public static void main(String[] args) throws IOException {
		BufferedReader fin = new BufferedReader(new FileReader("bbreeds.in"));
		PrintWriter fout = new PrintWriter(new BufferedWriter(new FileWriter("bbreeds.out")));
		char[] c = fin.readLine().toCharArray();
		fin.close();

		int[] pInt = new int[c.length];
		for (int i = 0; i < c.length; i++) {
			pInt[i] = (c[i] == '(')? 1 : -1; 
		}
		//h, g
		//int[][] ways = new int[501][501];
		int[][] waysO = new int[c.length/2 + 2][c.length/2 + 2];
		int[][] waysN = new int[c.length/2 + 2][c.length/2 + 2];
		waysO[0][0] = 1;
		TreeSet<Pair> curr = new TreeSet<>();	
		TreeSet<Pair> next = new TreeSet<>();

		curr.add(new Pair(0, 0));

		for (int i = 0; i < pInt.length; i++) {
			for (Pair p: curr) {
				if (pInt[i] == 1) {
					next.add(new Pair(p.h+1, p.g));
					waysN[p.h+1][p.g] += waysO[p.h][p.g]%2012;
					next.add(new Pair(p.h, p.g+1));
					waysN[p.h][p.g+1] += waysO[p.h][p.g]%2012;
				} else {
					if (p.h != 0) {
						next.add(new Pair(p.h-1, p.g));
						waysN[p.h-1][p.g] += waysO[p.h][p.g]%2012;
					}
					if (p.g != 0) {
						next.add(new Pair(p.h, p.g-1));
						waysN[p.h][p.g-1] += waysO[p.h][p.g]%2012;
					}
				}
			}
			curr = next;
			next = new TreeSet<>();
			waysO = waysN;
			waysN = new int[c.length/2 + 2][c.length/2 + 2];

			//debug
		//	for (int[] a : waysO) {
		//		for (int b : a) System.out.print(b + " ");
		//		System.out.println();
		//	}

		}
		fout.println(waysO[0][0] % 2012);
		fout.close();
	}
}
class Pair implements Comparable <Pair>{
	public int compareTo(Pair other) {
		return other.h - h;
	}
	public int h = -1;
	public int g = -1;
	public Pair (int a, int b) {
		h = a;
		g = b;
	}
}
