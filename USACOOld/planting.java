//Jan 2019 USACO Silver P1
import java.io.*;
import java.util.*;

class pair {
	int f;
	int d; 
	public pair (int F, int D) {
		f = F;
		d = D;
	}
}
public class planting {
	public static void main (String[] args) throws IOException{
		BufferedReader fin = new BufferedReader(new FileReader("planting.in"));
		PrintWriter fout = new PrintWriter(new BufferedWriter(new FileWriter("planting.out")));
		int N = Integer.parseInt(fin.readLine());
		ArrayList<Integer>[] edges = new ArrayList[N];
		for (int i = 0; i < N; i++) edges[i] = new ArrayList<Integer>();
		for (int i = 1; i < N; i++) {
			StringTokenizer sT = new StringTokenizer(fin.readLine());	
			int a = Integer.parseInt(sT.nextToken())-1;
			int b = Integer.parseInt(sT.nextToken())-1;
			edges[a].add(b);
			edges[b].add(a);
		}
		fin.close();
		int[] fields = new int[N];
		LinkedList<Integer> tP = new LinkedList<>();
		tP.add(0); //start from Field 0
		int max = 0;

		boolean[] visited = new boolean[N];
		while (!tP.isEmpty()) {
			int cur = tP.pop();
			visited[cur] = true;
			BitSet minPossible = new BitSet();

			LinkedList<pair> bfs = new LinkedList<>();
			bfs.add(new pair(cur, 0));
			boolean[] checked = new boolean[N];
			while (!bfs.isEmpty()) {
				pair check = bfs.pop();
				checked[check.f] = true;
				minPossible.set(fields[check.f]);
				for (int i: edges[check.f]) {
					if (check.d <= 1 && !checked[i]) bfs.add(new pair(i, check.d+1));
				}
			}
			fields[cur] = minPossible.nextClearBit(1);
			max = Math.max(max, fields[cur]);

			for (int i: edges[cur]) {
				if (!visited[i]) tP.add(i);
			}
		}
		fout.println(max);
		fout.close();
		
	}
}
