import java.io.*;
import java.util.*;

public class party {
	public static void main(String[] args) throws IOException{
		Scanner in = new Scanner(System.in);
		//Scanner in = new Scanner(new File("party.in"));

		final int N = in.nextInt();
		ArrayList<Integer> edges[] = new ArrayList[N+1];
		for (int i = 0; i < N+1; i++) edges[i] = new ArrayList<>();
 		boolean[] visited = new boolean[N+1];

		//read input
		for (int i = 1; i <= N; i++) {
			int p = in.nextInt();
			if (p == -1) p++;
			edges[p].add(i);
		}

		LinkedList<Pair> q = new LinkedList<>();
		q.offer(new Pair(0, -1));
		int out = 0;
		while(!q.isEmpty()) {
			Pair cur = q.pop();
			visited[cur.a] = true;
			out = Math.max(out, cur.b+1);
			for (int c : edges[cur.a]) {
				if (!visited[c]) {
					q.offer(new Pair(c, cur.b+1));
				}
			}
		}
		System.out.println(out);
	}
}

class Pair {
	public int a;
	public int b;
	public Pair (int a, int b) {
		this.a = a;
		this.b = b;
	}	
}
