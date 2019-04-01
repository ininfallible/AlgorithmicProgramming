//USACO Bronze December 2017 #3
import java.io.*;
import java.util.*;

public class measurement {
	public static void main(String[] args) throws IOException{
		BufferedReader fin = new BufferedReader(new FileReader("measurement.in"));
		PrintWriter fout = new PrintWriter(new BufferedWriter(new FileWriter("measurement.out")));
		int B = 7, E = 7, M = 7;
		int N = Integer.parseInt(fin.readLine());

		int[] t = new int[N]; //time
		String[] c = new String[N]; //cow
		int[] a = new int[N]; //amount
 
		for (int i = 0; i < N; i++) { //read file
			StringTokenizer sT = new StringTokenizer(fin.readLine());
			t[i] = Integer.parseInt(sT.nextToken());
			c[i] = sT.nextToken();
			a[i] = Integer.parseInt(sT.nextToken());
		}
	
		fin.close();

		int out = 0;
		boolean[] d = new boolean[3]; //hanging
		d[0] = true; d[1] = true; d[2] = true;
		for (int i = 0; i <= 100; i++) //time
		for (int j = 0; j < N; j++) {//go through all changes
			if (t[j] == i) {
				//modify cows
				if (c[j].equals("Bessie")) B += a[j];
				else if (c[j].equals("Elsie")) E += a[j];
				else M += a[j];
			
				//determine maximum
				int max = Math.max(B, Math.max(E, M));
				boolean changed = false;
				
				if (B >= max) {
					if (!d[0]) changed = true;
					d[0] = true;
				}
				if (B < max) {
					if (d[0]) changed = true;
					d[0] = false;
				}
				if (E >= max) {
					if (!d[1]) changed = true;
					d[1] = true;
				}
				if (E < max) {
					if (d[1]) changed = true;
					d[1] = false;
				}
				if (M >= max) {
					if (!d[2]) changed = true;
					d[2] = true;
				}
				if (M < max) {
					if (d[2]) changed = true;
					d[2] = false;
				}




				//update out
				if (changed) {
					System.out.println(i);
					out++;
				}
			}
		}
		


		fout.println(out);
		//fout.println();
		fout.close();
	
	}

}

