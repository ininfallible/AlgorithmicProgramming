/*
ID: derekl3
LANG: JAVA
TASK: maze1
*/

import java.io.*;
import java.util.*;

public class maze1 {
	static boolean inBounds(int row, int col) {
		return row >= 0 && row < h && col >= 0 && col < w;
	}
	static int w, h;
	final static int[] DR = new int[] {-1,0,1,0};
	final static int[] DC = new int[] {0,1,0,-1};
	static boolean[][][] g;
	static int[][] depths; 
	static boolean[][] v;
	static LinkedList<int[]> tP = new LinkedList<>();
	//test
	public static void main(String[] args) throws IOException {
		BufferedReader fin = new BufferedReader(new FileReader("maze1.in"));
		PrintWriter fout = new PrintWriter(new BufferedWriter(new FileWriter("maze1.out")));
		StringTokenizer sT = new StringTokenizer(fin.readLine());
		
		w = Integer.parseInt(sT.nextToken());
		h = Integer.parseInt(sT.nextToken());

		//iterate for maze to find connections
		String[][] f = new String[h * 2 + 1][]; //conversion into array
		for (int i = 0; i < h * 2 + 1; i++) {
			f[i] = fin.readLine().split("");
		}
		
		depths = new int[h][w];//distance to closest exit
		for (int[] i : depths) Arrays.fill(i, 10000);

		int[] e1 = new int[2], e2 = new int[2]; //exits
		//set up edge table
		g = new boolean[h][w][4]; // nesw
		boolean temp = false;
		System.out.println("p1");
		for (int j = 0; j < h; j++) { 
			for (int k = 0; k < w; k++) {
				int cR = j * 2 + 1;
				int cC = k * 2 + 1; //so it's on the right part of input
				for (int i = 0; i < 4; i++){//look at all four sides
					if(f[cR + DR[i]][cC + DC[i]].equals(" ")) {
						g[j][k][i] = true;
				//		System.out.println(j + " " + k + " p2");
					}
				}
				//check edges
				if (j == 0 && g[j][k][0] || //north
				    j == h-1 && g[j][k][2] || //south
				    k == 0 && g[j][k][3] || //west
				    k == w-1 && g[j][k][1]) { //east
					if (!temp) {
						temp = true;
						e1 = new int[] {j, k};
					} else {
						e2 = new int[] {j, k};
					}
  			   	 }
			}
		}
		if (e2[0] == 0 && e2[1] == 0) e2 = e1.clone();
		System.out.println("p3 " + depths.length + " " + depths[0].length);
		v = new boolean[h][w];
		tP.add(new int[] {e1[0], e1[1], 1});
		tP.add(new int[] {e2[0], e2[1], 1});
		while (!tP.isEmpty()){ //flood fill to assign depths
			int[] cur = tP.pop();
			int row = cur[0];
			int col = cur[1];
			int d = cur[2];
			v[row][col] = true;
			depths[row][col] = Math.min(depths[row][col], d);
			for (int i  = 0; i < 4; i++){
				int nR = row + DR[i];
				int nC = col + DC[i];
				if(g[row][col][i] && inBounds(nR, nC) && !v[nR][nC]) {
					v[nR][nC] = true;
					tP.add(new int[]{nR, nC, d + 1});
				}
			}	
		}
		int max = 0;
		for (int[] l : depths) { //calculate maximum depth
			for (int m : l) {System.out.print(m + " "); max = Math.max(max, m);}
			System.out.println();
		}
		System.out.println("Max: " + max);
		System.out.println("e1 " + e1[0] + e1[1] + " e2 " + e2[0] + e2[1]);
		fout.println(max);
		fout.close();
	}
}
