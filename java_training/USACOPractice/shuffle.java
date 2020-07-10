//USACO Bronze December 2017 #2
import java.io.*;
import java.util.Scanner;

public class shuffle {
	public static void main(String[] args) throws IOException {
		Scanner fin = new Scanner(new File("shuffle.in"));
		PrintWriter fout = new PrintWriter(new BufferedWriter(new FileWriter("shuffle.out")));
		int N = fin.nextInt();
		int[] s = new int[N]; //shuffle
		int[] c = new int[N]; //cows
		int[] sInv = new int[N];
		int[] cNew = new int[N];

		for (int i = 0; i < N; i++) //fill in cows
			s[i] = fin.nextInt() - 1;
		for (int i = 0; i < N; i++) { //inverse shuffle
			sInv[s[i]] = i;
		}

		for (int i = 0; i < N; i++) //fill in cows
			c[i] = fin.nextInt();
		fin.close();
		
		for (int i = 0; i < 3; i++) { //shuffle using inverse
			for (int j = 0; j < N; j++) 
				cNew[sInv[j]] = c[j];
			c = (int[]) cNew.clone();
		}
		
		for (int i: cNew) fout.println(i);
		fout.close();

	}	
}
