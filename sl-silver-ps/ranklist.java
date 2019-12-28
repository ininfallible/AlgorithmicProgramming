import java.util.*;
import java.io.*;

public class ranklist {
	public static void main(String[] args) throws IOException {
		//Scanner fin = new Scanner(new File("ranklist.txt"));
		Scanner fin = new Scanner(System.in);

		int n = fin.nextInt();
		int k = fin.nextInt();

		team[] teams = new team[n];
		for (int i = 0; i < n; i++)
			teams[i] = new team(fin.nextInt(), fin.nextInt());
		fin.close();
		Arrays.sort(teams);

		/*
		for (team t : teams) {
			System.out.println(t.p + " " + t.t);
		}
		*/

		int out = 0;
		team target = teams[k-1];
		for (team t: teams)
			if (t.compareTo(target) == 0)
				out++;
		System.out.println(out);
	}
}

class team implements Comparable<team> {
	int p;
	int t;
	public team(int P, int T) {
		p = P;
		t = T;
	}
	public int compareTo (team T) {
		return (p != T.p)? T.p - p: t - T.t;
	}
}


