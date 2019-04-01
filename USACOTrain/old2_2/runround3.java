package old2_2;
/*
ID: derekl3
LANG: JAVA
TASK: runround
 */

import java.util.*;
import java.io.*;

public class runround3 {
	static boolean check (int[] in) {
		boolean[] C = new boolean[in.length]; //Check
		int digit = 0;
		for (int i = 0; i < in.length; i++) {
			C[digit] = true;
			digit += in[digit];
			digit %= in.length;
		}
		if (digit != 0) return false;
		for (boolean j : C) if (!j) return false;
		return true;
	}
	public static void main(String[] args) throws IOException {
		BufferedReader fin = new BufferedReader(new FileReader("runround.in"));
		PrintWriter fout = new PrintWriter(new BufferedWriter(new FileWriter("runround.out")));
		int in = Integer.parseInt(fin.readLine());
		fin.close();
		//int in = 500;
		int out = 0;
		//boolean found = false;
		LinkedList<Integer> tP = new LinkedList<>();
		tP.add(0);
		
		while (true) {
			int cur = tP.pop();
			String[] temp = Integer.toString(cur).split("");
			int[] curArray = new int [temp.length];
			for (int j = 0; j < temp.length; j++) curArray[j] = Integer.parseInt(temp[j]);
			
			if (check(curArray) && cur > in) {out = cur; break;}
			boolean[] used = new boolean[10];
			for (int k : curArray) used[k] = true;
			for (int i = 1; i <= 9; i++) { //branch
				if (!used[i]) {
					tP.add(cur*10+i); //add integer to left
				}
			}
		}
		
		fout.println(out);
		fout.close();
	}
}
