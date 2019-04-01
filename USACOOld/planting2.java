//Jan 2019 USACO Silver P1
import java.io.*;
import java.util.*;

public class planting2 {
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

			boolean[] checked = new boolean[N];
			checked[cur] = true;
			for (int i: edges[cur]) {
				checked[i] = true;
				minPossible.set(fields[i]);
				for (int j: edges[i]) {
					checked[j] = true;
					minPossible.set(fields[j]);
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
