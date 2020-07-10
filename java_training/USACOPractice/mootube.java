//January 2018 USACO Silver P3
//improvment: redo data 
import java.io.*;
import java.util.*;

class edge {
	int d;
	int w;
	public edge (int D, int W) {
		d = D;
		w = W;
	}
}
public class mootube {
	public static void main(String[] args) throws IOException {
		BufferedReader fin = new BufferedReader(new FileReader("mootube.in"));
		PrintWriter fout = new PrintWriter(new BufferedWriter(new FileWriter("mootube.out")));
		StringTokenizer sT = new StringTokenizer(fin.readLine());
		int N = Integer.parseInt(sT.nextToken());
		int Q = Integer.parseInt(sT.nextToken());
		ArrayList<edge>[] edges = new ArrayList[N+1];
		for (int i = 1; i < N; i++) {
			sT = new StringTokenizer(fin.readLine());
			int p = Integer.parseInt(sT.nextToken());
			int q = Integer.parseInt(sT.nextToken());
			int r = Integer.parseInt(sT.nextToken());
			edges[p].add(new edge(q, r));
			edges[q].add(new edge(p, r));
		}

		for (int j = 0; j < Q; j++) {
			sT = new StringTokenizer(fin.readLine());
			int k = Integer.parseInt(sT.nextToken());
			int v = Integer.parseInt(sT.nextToken());

			boolean[] visited = new boolean[N + 1];
			LinkedList<Integer> tP = new LinkedList<>();
			int count = -1;
			//BFS
			tP.add(v);
			while(!tP.isEmpty()) {
				/*
				for (int[] x: paths) {//debug
					for (int y : x) System.out.print(y + " ");
					System.out.println();
				}
				System.out.println();
				*/

				count++;
				int cur = tP.pop();
				ArrayList<edge> curEdges = edges[cur];
				visited[cur] = true;
				for (edge i : curEdges) {
					if (i.w >= k && !visited[i.d]) {
						tP.add(i.d);
					}
				}
			}
			fout.println(count);
		}
		fin.close();
		fout.close();
	}
}
