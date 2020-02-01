//2016 USACO Gold P2

import java.io.*;
import java.util.*;

public class checklist {
	static int dist (int ax, int ay, int bx, int by) {
		return (int)(Math.pow(ax-bx,2)+Math.pow(ay-by,2));
	}
	public static void main(String[] args) throws IOException {
		BufferedReader fin = new BufferedReader(new FileReader("checklist.in"));	
		PrintWriter fout = new PrintWriter(new BufferedWriter(new FileWriter("checklist.out")));
		StringTokenizer sT = new StringTokenizer(fin.readLine());
		int H = Integer.parseInt(sT.nextToken());
		int G = Integer.parseInt(sT.nextToken());
		int[] hx = new int[H+1];
		int[] hy = new int[H+1];
		int[] gx = new int[G+1];
		int[] gy = new int[G+1];
		for (int i=1;i<=H;i++) {
			sT = new StringTokenizer(fin.readLine());
			hx[i] = Integer.parseInt(sT.nextToken());
			hy[i] = Integer.parseInt(sT.nextToken());
		}
		for (int i=1;i<=G;i++) {
			sT = new StringTokenizer(fin.readLine());
			gx[i] = Integer.parseInt(sT.nextToken());
			gy[i] = Integer.parseInt(sT.nextToken());
		}
		fin.close();
		
		//debug
		//for (int i=0;i<hx.length;i++) System.out.printf("H%d:%3d,%3d\n",i, hx[i], hy[i]);
		//for (int i=0;i<gx.length;i++) System.out.printf("G%d:%3d,%3d\n",i, gx[i], gy[i]);

		//h, g, last was h (0), or g (1)
		long[][][] dp = new long[H+1][G+1][2];
		for (long[][] i: dp) for (long[]j:i) Arrays.fill(j, 1<<62);
		dp[1][0][0] = 0;
		for (int i=0;i<=H;i++) {
			for (int j=0;j<=G;j++) {
				//System.out.printf("%2d, %2d: %10d, %10d \n",i , j, dp[i][j][0], dp[i][j][1]);
				//case H to H
				if(i!=H)
				dp[i+1][j][0] = Math.min(dp[i+1][j][0],dp[i][j][0]+dist(hx[i],hy[i],hx[i+1],hy[i+1]));
				//case H to G
				if(j!=G)
				dp[i][j+1][1] = Math.min(dp[i][j+1][1],dp[i][j][0]+dist(hx[i],hy[i],gx[j+1],gy[j+1]));
				//case G to H
				if(i!=H)
				dp[i+1][j][0] = Math.min(dp[i+1][j][0],dp[i][j][1]+dist(gx[j],gy[j],hx[i+1],hy[i+1]));
				//case G to G
				if(j!=G)
				dp[i][j+1][1] = Math.min(dp[i][j+1][1],dp[i][j][1]+dist(gx[j],gy[j],gx[j+1],gy[j+1]));
			}
		}
		//System.out.println(dp[H][G][0]);
		//System.out.println(dp[H][G][1]);
		fout.println(dp[H][G][0]);
		fout.close();
	}
}
