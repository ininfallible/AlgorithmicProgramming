//Feb. 2014 Silver P3
import java.io.*;
import java.util.*;

public class scode {
	static String S;
	static int[][] dp = new int[101][101];
	static int f(int start, int end) {
		String cur = S.substring(start, end);
		//System.out.println(cur);
		//if (end-start==2)return 1;
		if (dp[start][end]!=-1) return dp[start][end];
		int ret=0,i,j,k;
		int sl = cur.length();
		String str1, str2;
		for(i=1;i<sl;i++) {
			str1 = cur.substring(0, i);
			str2 = cur.substring(i);
			//make str2 longer
			int nStart = start+i;
			int nEnd = end;
			if(str1.length()==str2.length()) continue;
			if(str1.length()>str2.length()) {
				//swap
				String temp = str1;
				str1 = str2;
				str2 = temp;
				nStart = start;
				nEnd = start+i;
			}
			//case prefix
			if (str2.startsWith(str1)) {
				ret += f(nStart,nEnd)+1;
			}
			//case suffix
			if (str2.endsWith(str1)) {
				ret += f(nStart,nEnd)+1;
			}
		}
		ret %= 2014;
		dp[start][end]=ret;
		return ret;
	}

	public static void main(String[] args) throws IOException {
		for (int[] i : dp) Arrays.fill(i, -1);
		BufferedReader fin = new BufferedReader(new FileReader("scode.in"));
		PrintWriter fout = new PrintWriter(new BufferedWriter(new FileWriter("scode.out")));
		S = fin.readLine();
		fin.close();
		fout.println(f(0, S.length()));
		fout.close();
	}
}
