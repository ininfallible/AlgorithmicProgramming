/*
ID: derekl3
LANG: JAVA
TASK: frac1
*/



import java.io.*;
import java.util.*;

public class frac1 {
	static boolean rP(int a, int b) {//checks whether a and b are relatively prime
		//System.out.println(a + " " + b);
		while (a != 0 && b != 0){
			if (b == 0 || a == 0) break;
			if (a >= b) a = a % b;
			else if (b > a) b = b % a;
		} 
		
		if (b == 1 || a == 1) return true;
		return false;
	}
	
	static class C implements Comparator<int[]> {
		public int compare(int[] a, int[] b) {
			if ((double)a[0]/a[1] < (double)b[0]/b[1]) return -1;
			if ((double)a[0]/a[1] > (double)b[0]/b[1]) return 1;
			return 0;
		}
		
	} 
	
	public static void main(String[] args) throws IOException {
		//System.out.println(rP(1 , 1));
		BufferedReader fin = new BufferedReader(new FileReader("frac1.in"));
		PrintWriter fout = new PrintWriter(new BufferedWriter(new FileWriter("frac1.out")));
		int N = Integer.parseInt(fin.readLine());
		fin.close();
		
		//int N = 5;

		ArrayList<int[]> ans = new ArrayList<>(); 	//implementation
		for (int i = 0; i <= N; i++) {		//numerator
			for (int j = N; j >= i; j--) {	//denominator
				if (rP(i, j)) {
					ans.add(new int[] {i, j});
				}
			}
		}
		Collections.sort(ans, new C());
		
		for (int[] cur : ans) {
			fout.println(cur[0] + "/" + cur[1]);
		}
		fout.close();
	} 
}
