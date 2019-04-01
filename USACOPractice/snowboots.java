//2018 February USACO Gold P1
import java.io.*;
import java.util.*;

class boot implements Comparable<boot> {
	public int s; //max snowdepth
	public int d; //max step dist
	public int p = 0; //possible
	
	public boot(int S, int D) {
		s = S;
		d = D;
	}
	
	public int compareTo(boot b) { //sort descending
		if (s > b.s) return -1;
		else if (s == b.s) return 0;
		return 1;
	}

}
public class snowboots {
	public static void main(String[] args) throws IOException {
		BufferedReader fin = new BufferedReader(new FileReader("snowboots.in"));
		PrintWriter fout = new PrintWriter(new BufferedWriter(new FileWriter("snowboots.out")));
		StringTokenizer sT = new StringTokenizer(fin.readLine());
		int N = Integer.parseInt(sT.nextToken()); //number of tiles
		int B = Integer.parseInt(sT.nextToken()); //number of boots

		//doubly linked list
		sT = new StringTokenizer(fin.readLine());
		int[] d = new int[N]; //depths
		int[] next = new int[N];
		int[] prev = new int[N];
		for (int i = 0; i < N; i++) {
			d[i] = Integer.parseInt(sT.nextToken()); 
			next[i] = i+1;
			prev[i] = i-1;
		}
		int n = N-1; //tiles left
		

		//boot and sort
		boot[] sb = new boot[B];//sorted
		boot[] ub = new boot[B];//unsorted
		for (int i = 0; i < B; i++) {
			sT = new StringTokenizer(fin.readLine());
			boot temp = new boot(Integer.parseInt(sT.nextToken()), Integer.parseInt(sT.nextToken()));
			sb[i] = temp;
			ub[i] = temp;
		}
		Arrays.sort(sb);
		fin.close();

		//debug
		
		int maxDist = 1; //running total
		
		for (int i = 0; i < B; i++) { //for each boot
			boot cur = sb[i];
			for (int j = 0; j < N; j = next[j]) { //go through all tiles
				if (d[j] > cur.s) { //remove tile and update maxDist
					prev[next[j]] = prev[j];
					next[prev[j]] = next[j];
					maxDist = Math.max(maxDist, next[j]-prev[j]);
				}
			}
			if (maxDist <= cur.d) cur.p = 1;
		}

		for (boot b: ub) fout.println(b.p);
		fout.close();
	}
}
