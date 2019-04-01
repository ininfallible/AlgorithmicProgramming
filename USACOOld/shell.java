//2019 January USACO Bronze P1
import java.io.*;
import java.util.*;

public class shell {
	public static void main(String[] args) throws IOException{
		BufferedReader fin = new BufferedReader(new FileReader("shell.in"));
		PrintWriter fout = new PrintWriter(new BufferedWriter(new FileWriter("shell.out")));
		int N = Integer.parseInt(fin.readLine());
		int[] shells = new int[] {1, 2, 3};
		int A = 0, B = 0, C = 0;
		StringTokenizer sT;
		for (int i = 0; i < N; i++) {
			sT = new StringTokenizer(fin.readLine());
			//make switch
			int a = Integer.parseInt(sT.nextToken())-1;
			int b = Integer.parseInt(sT.nextToken())-1;
			int g = Integer.parseInt(sT.nextToken())-1;
			int copy = shells[a];
			shells[a] = shells[b];
			shells[b] = copy;
				
			if (shells[g]== 1)
				A++;
			else if(shells[g] == 2)
				B++;
			else if(shells[g] == 3)
				C++;
		}
		fin.close();


		fout.println(Math.max(A, Math.max(B, C)));
		fout.close();
	}
}
