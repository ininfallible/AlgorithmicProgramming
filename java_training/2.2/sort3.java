/*
ID: derekl3
LANG: JAVA
TASK: sort3
 */



import java.util.*;
import java.io.*;

public class sort3 {
	static int solve(int[] in) {
		int[][] graph = new int[4][4];
		int one = 0, two = 0, three = 0, out = 0;
		for (int i : in) {
			if (i == 1) one++;
			if (i == 2) two++;
			if (i == 3) three++;
		}
		
		for (int j = 1; j < in.length; j++) { //creates graph
			if (j <= one) { //fill diff[1]
				graph[1][in[j]]++;
			}
			if (j > one && j <= one + two) { //fill diff[2]
				graph[2][in[j]]++;
			}
			if (j > one + two) { //fill diff[3]
				graph[3][in[j]]++;
			}
		
		}
		for (int x = 0; x < 4; x++) { //debug
			for (int y : graph[x]) System.out.print(y + " ");
			System.out.println();
		}
		//sort graph
		while (graph[1][1] < one || graph[2][2] < two || graph[3][3] < three) {
			for (int a = 1; a < 4; a++) {
				for (int b = 0; b < 4; b++) {
					int o = 6 - (a + b);
					if (a == b) continue;
					if (graph[a][b] > 0 && graph[b][a] > 0) { //simple switch
						graph[a][b]--;
						graph[b][a]--;
						graph[a][a]++;
						graph[b][b]++;
						out++;
					}
					if (graph[a][b] > 0 && graph[o][a] > 0 && graph[b][o] > 0 && graph[b][a] == 0  ) { //no simple switch
						graph[a][b]--;
						graph[o][a]--;
						graph[b][a]--;
						graph[a][a]++;
						graph[b][b]++;
						graph[o][o]++;
						out += 2;
					}
				}
			}
		}
		return (int) Math.ceil((double)out);
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader fin = new BufferedReader(new FileReader("sort3.in"));
		PrintWriter fout = new PrintWriter(new BufferedWriter(new FileWriter("sort3.out")));
		int c =  Integer.parseInt(fin.readLine());
		int[] in = new int[c+1];
		for (int k = 1; k <= c; k++) { //indexing from 1
			in[k] = Integer.parseInt(fin.readLine());
		}
		fin.close();
		
		fout.println(solve(in));
		fout.close();
		
		//System.out.println(solve(new int[]{0,2,2,3,3,1,1}));

	}
}
