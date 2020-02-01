//2014 US Open Silver P3
//Given Solution DP strategy
import java.io.*;
import java.util.*;

public class odometer {
	final static int maxn = 50;
	
		
	//ind: represents current position, und: whether below actual number, 
	//counter for a digit,  has leading zeroes
	static long[][][][] dp = new long[maxn][2][maxn][2];
	static long f(String s) {
		int n = s.length();
		long ret = 0;
		for (int targ1=0;targ1<=9;targ1++) {
			ret += getDP(s, n, targ1, -1);
			for (int targ2=targ1+1;targ2<=9;targ2++) {
				ret -=getDP(s, n, targ1, targ2);
			}
		}
		return ret;
	}
	//targ1 is the majority number, targ2 is possible secondary 
	static long getDP(String s, int n, int targ1, int targ2) {
		dp = new long[maxn][2][maxn][2];
		dp[0][0][25][1] = 1; //base case
		for (int i=0;i<n;i++) { 
			for (int und=0;und<2;und++) { //under number means
				for (int k = 0; k < maxn; k++) {
					for (int is0=0;is0<2; is0++) {
						long cur = dp[i][und][k][is0];
						for (int nxt=0;nxt<=9;nxt++){
							if (targ2!=-1&&(nxt!=0||is0==0)&&nxt!=targ1&&nxt!=targ2) continue;
							if (und==0&&nxt>s.charAt(i)-'0') continue;
							int nis0 = is0; //see if next digit will also be a leading 0
							if (nxt!=0) nis0 = 0;
							int nk = k; //see if next digit will contribute to dominant digit
							if (nis0==0) {
								if(targ2!=-1) {
									if(nxt==targ1) nk--;
									else if (nxt==targ2) nk++;
								}
								else {
									if(nxt==targ1) nk--;
									else nk++;
								}
							}
							int nund = und;
							nund = (nund==1||(nxt<s.charAt(i)-'0'))?1:0;
							if(nk>=0&&nk<50) {
								dp[i+1][nund][nk][nis0]+=cur;
							}
						}
					}
				}
			}
		}

		long ret = 0;
		if (targ2 != -1) {
			for (int i=0;i<2;i++) {
				ret+=dp[n][i][25][0];
			}
			return ret;
		}
		for(int i=0;i<2;i++) {
			for (int k=0;k<25;k++) {
				ret+=dp[n][i][k][0];
			}
		}
		return ret;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader fin = new BufferedReader(new FileReader("odometer.in"));
		PrintWriter fout = new PrintWriter(new BufferedWriter(new FileWriter("odometer.out")));
		StringTokenizer sT = new StringTokenizer(fin.readLine());
		String A = Long.toString(Long.parseLong(sT.nextToken())-1);
		String B = sT.nextToken();
		fin.close();

		long ans1 = f(A);
		long ans2 = f(B);
		fout.println(ans2-ans1);
		fout.close();
	}
}
