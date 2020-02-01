import java.io.*;
import java.util.*;

public class cowcode {
	public static void main(String[] args) throws IOException {
		BufferedReader fin = new BufferedReader(new FileReader("cowcode.in"));
		PrintWriter fout = new PrintWriter(new BufferedWriter(new FileWriter("cowcode.out")));
		StringTokenizer sT = new StringTokenizer(fin.readLine());
		String s = sT.nextToken();
		long N = Long.parseLong(sT.nextToken());
		fin.close();

		long l = s.length();
		while (l*2 < N) l *= 2;
		while (N > s.length()) {
			//System.out.println(l);
			N -= l; 
			N--; 
			if (N <= 0) N += s.length();
			l /= 2;
		}
		fout.println(s.charAt((int)(N-1)));
		fout.close();
	}

}
