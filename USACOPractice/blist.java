//2018 December Bronze contest
import java.io.*;
import java.util.*;

public class blist {
	public static void main(String[] args) throws IOException {
		BufferedReader fin = new BufferedReader(new FileReader("blist.in"));
		PrintWriter fout = new PrintWriter(new BufferedWriter(new FileWriter("blist.out")));
		int i = Integer.parseInt(fin.readLine());

		int out = 0;
		int[] t = new int[1001];

		for (int j = 0; j < i; j++) {
			StringTokenizer sT = new StringTokenizer(fin.readLine());
			int si = Integer.parseInt(sT.nextToken());
			int ti = Integer.parseInt(sT.nextToken());
			int b = Integer.parseInt(sT.nextToken());

			for (si = si; si <= ti; si++)
				t[si] += b;
		}
		fin.close();
		for (int k: t)
			if (k > out)
				out = k;
		fout.println(out);
		fout.close();
	}
}
