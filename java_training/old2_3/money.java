package old2_3;
/*
ID: derekl3
LANG: JAVA
TASK: money
 */
import java.io.*;
import java.util.*;

public class money {
	static int N;
	static long[] ways = new long[15000];
	static int[] c;

	public static void main (String[] args) throws IOException {
		BufferedReader fin = new BufferedReader(new FileReader("money.in"));
		PrintWriter fout = new PrintWriter(new BufferedWriter(new FileWriter("money.out")));
		StringTokenizer sT = new StringTokenizer(fin.readLine());
		int cn = Integer.parseInt(sT.nextToken());
		N = Integer.parseInt(sT.nextToken());
		int[] c = new int[cn];
		sT = new StringTokenizer(fin.readLine());
		for (int i = 0; i < cn; i++) {
			if(sT.hasMoreTokens()) c[i] = Integer.parseInt(sT.nextToken());
			else {sT = new StringTokenizer(fin.readLine()); i--;}
		}
		
		
		
		
		fin.close();
		ways[0] = 1;
		for (int i = 0; i < c.length; i++) {
			for (int j = c[i]; j <= N; j++) {
				ways[j] += ways[j-c[i]];
			}
		}
		fout.println(ways[N]);
		fout.close();
		//for (int i = 0; i <= N; i++) System.out.print(ways[i] + " ");
	}
}
