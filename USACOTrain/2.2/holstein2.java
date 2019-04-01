/*
ID: derekl3
LANG: JAVA
TASK: holstein
*/


import java.io.*;
import java.util.*;

public class holstein2 {
	static int[] req;
	
	static int fB (int[][] choices) { //returns index of best choice
		int max = 0;
		int maxT = 0;
		for (int t = 0; t < choices.length; t++) { //t is type
			int[] cur = choices[t];
			int total = 0;
			for (int v = 0; v < req.length; v++) {
				total += Math.min(req[v], cur[v]);
			}
			if (total > maxT) {max = t; maxT = total;}
		}
		for (int i = 0; i < req.length; i++) {
			req[i] = Math.max(req[i] - choices[max][i], 0);
		}
		return max;
	}
	public static void main(String[] args) throws IOException {
		BufferedReader fin = new BufferedReader(new FileReader("holstein.in"));
		PrintWriter fout = new PrintWriter(new BufferedWriter(new FileWriter("holstein.out")));
		int V = Integer.parseInt(fin.readLine()); //num vitamins	
		req = new int[V];
		StringTokenizer sT = new StringTokenizer(fin.readLine());
		int c = 0; //temp counter
		while (sT.hasMoreTokens()) {
			req[c] = Integer.parseInt(sT.nextToken());
			c++;
		}
		int N = Integer.parseInt(fin.readLine()); //num feed
		int[][] fT = new int[N][V]; //feedtype 
		for (int c2 = 0; c2 < N; c2++) {
			c = 0;
			sT = new StringTokenizer(fin.readLine());
			while (sT.hasMoreTokens()) {
				fT[c2][c] = Integer.parseInt(sT.nextToken());
				c++;
			}
		}
		
		fin.close();
		
		ArrayList<Integer> scoops = new ArrayList<>();
		boolean nF = true;
		
		while (nF) {
			scoops.add(fB(fT));
			
			nF = false;
			for (int i: req) {
				System.out.println(i);
				if (i > 0) nF = true;
			}
		}
		
		Collections.sort(scoops);
		String out = Integer.toString(scoops.size()) + " ";
		for (int j : scoops) out += Integer.toString(j+1) + " ";
		out = out.trim();
		fout.println(out);
		fout.close();
		/*
		//use bfs to search, use cardinality
		v = 4; //vitamin
		f = 3; //feedtypes
		//req = new int[v];
		//fT = new int[f][v];
		req = new int[] {100,200,300,400};
		fT = new int[][] {
			{50,50,50,50},
			{200,300,200,300},
			{900,150,389,399}
		};
		*/
	}

}
