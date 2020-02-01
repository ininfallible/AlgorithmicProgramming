//USACO 2014 Dec P2

import java.io.*;
import java.util.*;

public class marathon {
	static int dist(int a, int b) {
		return Math.abs(cp[a][0]-cp[b][0]) + Math.abs(cp[a][1]-cp[b][1]);
	}
	final static int INF = 2000000000;
	static int[][] cp;
	public static void main(String[] args) throws IOException{
		BufferedReader fin = new BufferedReader(new FileReader("marathon.in"));
		PrintWriter fout = new PrintWriter(new BufferedWriter(new FileWriter("marathon.out")));
		StringTokenizer sT = new StringTokenizer(fin.readLine());
		int N = Integer.parseInt(sT.nextToken());
		int K = Integer.parseInt(sT.nextToken());
		cp = new int[N][2];
		for (int i=0;i<N;i++) {
			sT = new StringTokenizer(fin.readLine());
			cp[i][0] = Integer.parseInt(sT.nextToken());
			cp[i][1] = Integer.parseInt(sT.nextToken());
		}
		
		int[][] dp = new int[K+1][N];
		for (int[] i: dp) Arrays.fill(i, INF);
		dp[0][0] = 0;
		for (int i=0;i<=K;i++) { //original point
			for (int j=0;j<N;j++) { //skip a certain number of times
				for(int l=j+1;j<N && i+l-j-1<=K;l++) { //total no. of skips
					//l-j-1 is the number of points you are jumping forwards
					int nextI = i + (l-j-1);
					//total number of skips
					int nextJ = l;
					dp[nextI][nextJ] = Math.min(dp[nextI][nextJ], dp[i][j] + dist(j, l));
				}	
			}
		}
		fin.close();
		fout.println(dp[K][N-1]);
		fout.close();
	}
}
