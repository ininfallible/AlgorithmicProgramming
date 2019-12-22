import java.io.*;
import java.util.*;

public class cbs {
	public static void main(String[] args) throws IOException{
		BufferedReader fin = new BufferedReader(new FileReader("cbs.in"));	
		PrintWriter fout = new PrintWriter(new BufferedWriter(new FileWriter("cbs.out")));
		StringTokenizer sT = new StringTokenizer(fin.readLine());
		int K = Integer.parseInt(sT.nextToken()); //K lines
		int N = Integer.parseInt(sT.nextToken()); //N cows
		HashMap<Integer, ArrayList<Integer>> bMap = new HashMap<>(); //balanced pairs
		int[][] ps = new int[K][N+1]; //prefix sums
		for (int i = 0; i < K; i++) {
			char[] cur = fin.readLine().toCharArray();
			for (int j = 0; j < cur.length; j++) {
				ps[i][j+1] = ps[i][j] + (cur[j] == '('?1:-1);
			}
		}
		fin.close();

		//debug
		/*
		for (int[] i : ps) {
			for (int j : i) System.out.print(j + " ");
			System.out.println();
			System.out.println();
		}
		*/
		HashSet<Pair>[] valid = new HashSet[K];
        for (int i = 0; i < K; i++) { 
            valid[i] = new HashSet<Pair>(); 
        } 
		for (int i = 0; i < K; i++) {
			HashMap<Integer, ArrayList<Integer>> possible = new HashMap<>();
			possible.put(0, new ArrayList<Integer>());
			possible.get(0).add(1);
			for (int j = 1; j <= N; j++) {
				if (ps[i][j] > ps[i][j-1]) { //case (
					if (possible.get(ps[i][j]) == null) 
						possible.put(ps[i][j], new ArrayList<Integer>());
					possible.get(ps[i][j]).add(j);
				} else { //case )
					if (possible.get(ps[i][j]+1) != null) {
						for (int k : possible.get(ps[i][j]+1)) {
							valid[i].add(new Pair(k-1, j-1));
						}
					}
					possible.remove(ps[i][j]+2);
				}
			}
		}

		//debug
		/*
		for (HashSet<Pair> i : valid) {
			for (Pair p : i) {
				System.out.print(p.a+" "+p.b+", ");
			}
			System.out.println();
		}
		*/

		HashSet<Pair> out = valid[0];
		for (int i = 1; i < valid.length; i++) out.retainAll(valid[i]);

		//out.retainAll(valid[1]);
		//System.out.println(out.contains(new Pair(10, 13)));
		//fout.println(out);
		
		//debug
		//System.out.println(out.size());
		fout.println(out.size());
		fout.close();
	}
}

class Pair implements Comparable<Pair> {
	public int a;
	public int b;
	public int compareTo(Pair o) {
		return (a == o.a) ? o.b-b : o.a-a;
	}
	@Override
	public boolean equals(Object o) {
		Pair p = (Pair) o;
		return a == p.a && b == p.b;
	}
	@Override
    public int hashCode() {
        return Objects.hash(a, b);
    }
	public Pair (int a, int b) {
		this.a = a;
		this.b = b;
	}
}
