//2019 January USACO Bronze P1
import java.io.*;
import java.util.*;

public class sleepy {
	public static void main(String[] args) throws IOException {
		BufferedReader fin = new BufferedReader(new FileReader("sleepy.in"));
		PrintWriter fout = new PrintWriter(new BufferedWriter(new FileWriter("sleepy.out")));
		int N = Integer.parseInt(fin.readLine());
		StringTokenizer sT = new StringTokenizer(fin.readLine());
		int[] cows = new int[N];
		for (int i = 0; i < N; i++) cows[i] = Integer.parseInt(sT.nextToken());
		fin.close();
		
		int count = N;
		int cur = cows[cows.length-1];
		for (int j = cows.length - 1; j >= 0; j--) {
			if (cows[j] <= cur) {
				count--;
				cur = cows[j];
			} else {
				break;
			}
		} 
		System.out.println(count);
		fout.println(count);
		fout.close();
	
	}


}
