//2017 January Gold P2
import java.io.*;
import java.util.*;

public class hps {
	static int N, K;
	static String F = "";
	static int[][][] dp;

	static char[] m = new char[]{'H','P','S'};
	static int w(char a, char b) {
		if(a=='H'&&b=='S') return 1;
		if(a=='P'&&b=='H') return 1;
		if(a=='S'&&b=='P') return 1;
		return 0;
	}
	public static void main(String[] args) throws IOException {
		BufferedReader fin = new BufferedReader(new FileReader("hps.in"));
		PrintWriter fout = new PrintWriter(new BufferedWriter(new FileWriter("hps.out")));
		StringTokenizer sT = new StringTokenizer(fin.readLine());
		N = Integer.parseInt(sT.nextToken());
		K = Integer.parseInt(sT.nextToken());
		for (int i=0;i<=N;i++) {
			F += fin.readLine();
		}
		fin.close();
		dp = new int[N+2][K+1][3];
		for (int[][] i:dp) {
			for (int[] j:i){
				Arrays.fill(j, -1);
			}
		}

		//static int f(int ind, int k, int prev)
		//iterate through possible indexes, wincount, 
		dp[0][0][0]=dp[0][0][1]=dp[0][0][2]=0;
		int out = 0;
		for (int ind=0;ind<N;ind++) {
		for (int k=0;k<=K;k++) {
		for (int prev=0;prev<3;prev++){
			for (int next=0;next<3;next++) {
				if(dp[ind][k][prev]==-1)continue;
				//case stay the same
				if(next==prev) {
					dp[ind+1][k][next] = Math.max(dp[ind+1][k][next], dp[ind][k][prev]+w(m[next],F.charAt(ind)));
					out = Math.max(out, dp[ind+1][k][next]);
				//case switch to another
				} else if (k!=K){
					dp[ind+1][k+1][next] = Math.max(dp[ind+1][k+1][next], dp[ind][k][prev]+w(m[next],F.charAt(ind)));
					out = Math.max(out, dp[ind+1][k][next]);
				}
			}
		}
		}
		}
		fout.println(out);
		fout.close();
	}
}
