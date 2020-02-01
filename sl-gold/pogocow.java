//USACO 2013 November Silver P3
import java.io.*;
import java.util.*;

public class pogocow {
	public static void main(String[] args) throws IOException{
		BufferedReader fin = new BufferedReader(new FileReader("pogocow.in"));
		PrintWriter fout = new PrintWriter(new BufferedWriter(new FileWriter("pogocow.out")));
		int N = Integer.parseInt(fin.readLine());
		target[] t = new target[N];
		StringTokenizer sT;
		for (int i=0;i<N;i++){
			sT = new StringTokenizer(fin.readLine());
			t[i] = new target(Integer.parseInt(sT.nextToken()), 
								Integer.parseInt(sT.nextToken()));
		}
		fin.close();

		Arrays.sort(t);
		//for (target i:t) System.out.println(i.l); //debug
		
		//dp part
		//might need to do it twice for forwards/back

		//dp: stores cur loc, prev loc
		int[][] dp = new int[N][N];

		int sol = 0;
		for (int l=0;l<N;l++) {
			for (int p=0;p<=l;p++) {
				//System.out.printf("%2d,%2d:%3d%n",l, p, dp[l][p]);
				if (l==p) dp[l][p]=t[l].pts;
				if (dp[l][p]==0) continue;
				//min dist to jump
				int d = (t[l].dist(t[p]));
				for (int n=l+1;n<N;n++) {
					if (t[n].dist(t[l])<=d) dp[n][l]=Math.max(dp[n][l], dp[l][p]+t[n].pts);
				}
				sol = Math.max(sol, dp[l][p]);
			}
		}

		System.out.println(sol);
		fout.println(sol);
		fout.close();
	}
}

class target implements Comparable<target>{
	public int loc;
	public int pts;
	public target(int loc, int pts) {
		this.loc=loc;
		this.pts=pts;
	}
	public int dist(target other) {
		if (other.equals(this)) return 1<<30;
		return Math.abs(this.loc-other.loc);
	}
	public int compareTo(target other){
		return this.loc-other.loc;
	}
}
