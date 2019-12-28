package old2_3;
/*
ID: derekl3
LANG: JAVA
TASK: zerosum
*/

import java.util.*;
import java.io.*;

public class zerosum {
	static int N;
	static ArrayList<int[]> out;
	//in prev: 1 is join, 2 is sum, 3 is subtract
	//i starts at 1 because 1 is always positive
	public static void sol(int n, int s, int[] prev) { //pos, current sum, prev operation
		if (n == 1) {  //base case
			if (prev[0] == 1) {s += 11;} //if first two terms are joined
			if (s == 0) out.add(prev); 
			return;}
		
		int i = n - 2; //index goes from N-2 to 0, n goes from N to 2, ends at beginning of 1
		int diff = n;
		if (i <= N) {
			if (prev[i + 1] == 1) {diff = n * 11 + 1;}
			if (prev[i + 1] != 1) {prev[i] = 1; sol(n - 1, s, prev.clone());}
		}
		prev[i] = 2; //add
		sol(n - 1, s + diff, prev.clone());
		prev[i] = 3; //subtract
		sol(n - 1, s - diff, prev.clone());
		return;
	}
	static class cComp implements Comparator<int[]>{
		public int compare(int[] a, int[] b) {
			for (int i = 0; i < a.length; i++) {
					if (Integer.compare(a[i], b[i]) != 0) return Integer.compare(a[i], b[i]);
				}
			return 0;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader fin = new BufferedReader(new FileReader("zerosum.in"));
		PrintWriter fout = new PrintWriter(new BufferedWriter(new FileWriter("zerosum.out")));
		N = Integer.parseInt(fin.readLine());
		fin.close();
		out = new ArrayList<>();
		sol(N, 1, new int[N]);
		Collections.sort(out, new cComp());
		for (int[] i : out) {
			int j;
			for (j = 0; j < i.length; j++) {
				fout.print(j + 1);
				switch (i[j]) {
				case 1: fout.print(" ");
				break;
				case 2: fout.print("+");
				break;
				case 3: fout.print("-");
				break;
				}
			}
			fout.println();
		}
		fout.close();
	}
	/*
	public static void main(String[] args) throws IOException {
		N = 5;
		out = new ArrayList<>();
		sol(N, 1, new int[N]);
		Collections.sort(out, new cComp());
		for (int[] i : out) {
			int j;
			for (j = 0; j < i.length; j++) {
				System.out.print(j + 1);
				switch (i[j]) {
				case 1: System.out.print(" ");
				break;
				case 2: System.out.print("+");
				break;
				case 3: System.out.print("-");
				break;
				}
			}
			System.out.println();
		}
		System.out.close();
	}
	*/
}
