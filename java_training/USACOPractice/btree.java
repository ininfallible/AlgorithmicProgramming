import java.io.*;
import java.util.*;

public class btree {
	public static void main(String[] args) throws IOException {
		BufferedReader fin = new BufferedReader(new FileReader("btree.in"));
		PrintWriter fout = new PrintWriter(new BufferedWriter(new FileWriter("btree.out")));
		final int N = Integer.parseInt(fin.readLine());
		edges = new HashMap<>();
		
		if (N == 1) {
			fout.println(0);
			fout.close();
			return;
		}	

		for (int i = 2; i <= N; i++) {
			int j = Integer.parseInt(fin.readLine());
			if (edges.get(i) == null) edges.put(i, new ArrayList<Integer>());
			if (edges.get(j) == null) edges.put(j, new ArrayList<Integer>());
			edges.get(i).add(j);
			edges.get(j).add(i);
		}

		nodes = new int[N+1];
		for (int i = 1; i <= N; i++) {
			nodes[i] = (int)fin.readLine().charAt(0) == (int)'('? 1 : -1;
		}
		fin.close();

		//debug
		/*
		for (int i : edges.keySet()) {
			System.out.println(i + " " + edges.get(i));
		}
		*/
		
		//dfs for every node
		out = 0;
		for (int i = 1; i <= N; i++) {
			if(nodes[i] == -1) continue;
			ps = new int[N+1];
			visited = new boolean[N+1];
			o = i;
			dfs(0, i, 0);
		}

		fout.println(out);
		fout.close();
		return;
	}

	static boolean[] visited;
	static int[] ps; //prefix sum array
	static int[] nodes;
	static HashMap<Integer, ArrayList<Integer>> edges;
	static int o;
	static int out = 0;

	public static void dfs (int p, int c, int r) {
		visited[c] = true;
		ps[c] = ps[p] + nodes[c];
		r = Math.max(ps[c], r);
		if (ps[c] < 0) return;
		if (ps[c] == 0) out = Math.max(r, out);
		for (int n : edges.get(c)) if (!visited[n]) dfs(c, n, r);
		
		return;
	}
}

