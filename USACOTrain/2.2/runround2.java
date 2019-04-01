/*
ID: derekl3
LANG: JAVA
TASK: runround
 */
import java.io.*;
import java.util.*;

public class runround2 {
	static boolean check(int[] in) { //I: int array, C: checked
		int j = 0;
		while (in[j] != 0) j++;
		int[] I = new int[j+1];
		j = 0;
		while (in[j] != 0) {I[j] = in[j]; j++;}
		if (j == 0) return false;
		boolean[] C = new boolean[j+1];
		
		int cur = 0; //start with leftmost digit
		C[0] = true;
		
		for (int c = 0; c < 11; c++) {
			cur += I[cur];
			cur %= I.length;
			if (C[cur] == true) break;
			C[cur] = true;
		}
		
		for (boolean k : C) if (!k) return false;
		
		return true;
	}
	public static void main (String[] args) throws IOException {
		//BufferedReader fin = new BufferedReader(new FileReader("runround.in"));
		//PrintWriter fout = new PrintWriter(new BufferedWriter(new FileWriter("runround.out")));
		//int in = Integer.parseInt(fin.readLine());
		//fin.close();
		int in = 500;
		int l = Integer.toString(in).length();
		//int in = 90000000;
		LinkedList<int[]> tP = new LinkedList<>();
		int[] first = new int[l];
		tP.add(first);
		boolean found = false;
		int[] out = new int[9];
		
		System.out.println(check(first));
		while (!found) {
			int[] cur = tP.pop();
			boolean[] used = new boolean[10];
			boolean greater = false;
			String m = "";
			for (int k: cur) m += Integer.toString(k);
			System.out.println(m);
			int n = Integer.parseInt(m);
			if (n > in) greater = true;
			
			int i;
			if (check(cur) && greater) {found = true; out = cur.clone(); break;}
			for (i = 0; cur[i] != 0; i++) {
				used[cur[i]] = true;
			}//find rightmost available int
			for (int j = 1; j <= 9; j++) {
				if (!used[j]) {
					cur[i] = j;
					tP.add(cur.clone());
				}
			}
		}
		System.out.println(Arrays.toString(out));
		
		//fout.println();
		//fout.close();
	}
}
