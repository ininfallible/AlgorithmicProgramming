/*
ID: derekl3
LANG: JAVA
TASK: subset
*/

import java.io.*;

public class subset {
	static int cA (int in) {
		return (in * (in+1))/2;
	}
	public static void main(String[] args) throws IOException {
		BufferedReader fin = new BufferedReader(new FileReader("subset.in"));
		PrintWriter fout = new PrintWriter(new BufferedWriter(new FileWriter("subset.out")));
		int in = Integer.parseInt(fin.readLine());
		fin.close();
		//int in = 39;
		int sum = cA(in);
		if (sum % 2 == 1) {
			fout.println(0);
		} else {
			long[] ways = new long[sum + 1];
			ways[0] = 1;
			for (int i = 1; i <= in; i++) { //i iteration is also opposite of dynamic recurrence
				for (int j = sum / 2 - i; j >= 0; --j) { //sum is n*(n+1)/2
			        ways[j + i] += ways[j]; //ways j is the past answer, j+i is opposite of the dynamic recurrence
			    }
			}
			fout.println(ways[sum/2]/2);
		}
		fout.close();	
	}
}
