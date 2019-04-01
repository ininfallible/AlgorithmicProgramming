/*
ID: derekl3
LANG: JAVA
TASK: hamming
 */
import java.io.*;
import java.util.*;

public class hamming {
	static int n = 16, b = 7, d = 3; //number of words, length, difference required
	public static int cH(int x, int y) { //calcHamming
		int out = 0;
		int temp = 0;
		int c = x ^ y;
		while (temp < b) {
			out += c & 1;
			c >>= 1;
			temp++;
		}
		return out;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader fin = new BufferedReader(new FileReader("hamming.in"));
		PrintWriter fout = new PrintWriter(new BufferedWriter(new FileWriter("hamming.out")));
		StringTokenizer sT = new StringTokenizer(fin.readLine());
		n = Integer.parseInt(sT.nextToken());
		b = Integer.parseInt(sT.nextToken());
		d = Integer.parseInt(sT.nextToken());
		fin.close();
		int[] ans = new int[64];
		int l = 1;
		//iterate through all bytes (256 possiblities);
		for (int cur = 0x0; cur < 256; cur++) {
			if (l == n) break;
			boolean v = true;
			for (int i = 0; i < l; i++) {
				if (cH (cur, ans[i]) < d) {v = false; break;}
			}
			if (v) {ans[l] = cur; l++;}
		}
		/*for (int j = 0; j < l; j++) {
			System.out.println(ans[j]);
		}*/
		String out = "";
		for (int j = 0; j < l; j++) {
			out += Integer.toString(ans[j]);
			if(( j+1) % 10 == 0) out += "\n";
			else{out += " ";}
		}
		out = out.trim();
		System.out.println(out);
		fout.println(out);
		fout.close();
		
	}
}
