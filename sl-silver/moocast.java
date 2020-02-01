import java.io.*;
import java.util.*;

public class moocast {
	static Cow[] cows;
	static boolean[] v;
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader fin = new BufferedReader(new FileReader("moocast.in"));
		PrintWriter fout = new PrintWriter(new BufferedWriter(new FileWriter("moocast.out")));
		N = Integer.parseInt(fin.readLine());

		cows = new Cow[N];
		StringTokenizer sT;
		for (int i = 0; i < N; i++) {
			sT = new StringTokenizer(fin.readLine());
			cows[i] = new Cow(
				Integer.parseInt(sT.nextToken()),
				Integer.parseInt(sT.nextToken()),
				Integer.parseInt(sT.nextToken())
			);
		}
		fin.close();

		int out = 0;
		for (int i = 0; i < N; i++){ 
			v = new boolean[N];
			out = Math.max(out, dfs(i, 0));
			//System.out.println();
		}

		fout.println(out);
		fout.close();
	}

	public static int dfs (int c, int count) { 
		//debug
		//System.out.println(c);

		v[c] = true;
		int out = count;
		for (int d = 0; d < N; d++) {
			if (!v[d] && cows[c].dist(cows[d]) <= cows[c].s) {
				v[d] = true;
				out += dfs(d, count);
			}
		}
		return out + 1;
	}
}

class Cow {
	public int x;
	public int y;
	public int s;

	public Cow(int a, int b, int c) {
		x = a;
		y = b;
		s = c;
	}

	public double dist(Cow other) {
		return Math.sqrt(Math.pow(x-other.x, 2) + Math.pow(y-other.y, 2));
	}
}
