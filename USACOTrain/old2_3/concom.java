package old2_3;
/*
ID: derekl3
LANG: JAVA
TASK: concom
 */
import java.io.*;
import java.util.*;

public class concom {
	static int[][] hs = new int[101][101], in;
	static int n;
	static boolean[] v = new boolean[101];
	static int[] c = new int[101];
	public static void dfs(int in) { //dfs for all companies for ownership
		v[in] = true;
		int[] i = hs[in];
		for (int j = 1; j <= n; j++) { //iterate through ownerships company "in" has
			c[j] += i[j];
			if (c[j] >= 50 && !v[j] && j != in) dfs(j);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader fin = new BufferedReader(new FileReader("concom.in"));
		PrintWriter fout = new PrintWriter(new BufferedWriter(new FileWriter("concom.out")));
		n = Integer.parseInt(fin.readLine());
		for (int i = 0; i < n; i++) {
			StringTokenizer sT = new StringTokenizer(fin.readLine());
			int a = Integer.parseInt(sT.nextToken());
			int b = Integer.parseInt(sT.nextToken());
			hs[a][b] += Integer.parseInt(sT.nextToken());
		}
		fin.close();
		n = 100;
	
		for (int i = 1; i <= n; i++) {
			for (int k = 0; k <= 100; k++) {c[k] = 0; v[k] = false;}
			dfs(i);
			for (int j = 1; j <= n; j++) {
				if (c[j] >= 50 && i != j) {fout.println(i + " " + j);}
			}
		}
		fout.close();
	}
}
