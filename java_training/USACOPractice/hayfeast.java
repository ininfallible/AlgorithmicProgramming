//2017 December USACO Gold P3

import java.util.*;
import java.io.*;

public class hayfeast {
	public static void main(String[] args) throws IOException {
		BufferedReader fin = new BufferedReader(new FileReader("hayfeast.in"));
		PrintWriter fout = new PrintWriter(new BufferedWriter(new FileWriter("hayfeast.out")));
		StringTokenizer sT = new StringTokenizer(fin.readLine());
		int n = Integer.parseInt(sT.nextToken());
		long need = Long.parseLong(sT.nextToken());
		long[] f = new long[n];
		long[] s = new long[n];
		for (int i = 0; i < n; i++) {
			sT = new StringTokenizer(fin.readLine());
			f[i] = Long.parseLong(sT.nextToken());
			s[i] = Long.parseLong(sT.nextToken());
		}
		fin.close();

		long min = Long.MAX_VALUE;
		TreeMap<Long, Integer> seen = new TreeMap<>();
		long flavor = 0;

		int left = 0;
		for (int right = 0; right < n; right++) {//move right pointer right
			flavor +=  f[right];
			update(seen, s[right], 1);
			while (flavor - f[left] >= need) {//move left pointer right
				update(seen, s[left], -1);
				flavor -= f[left++];
			}
			//update min with greatest spiciness (TreeMap sorts Keys
			if (flavor >= need) min = Math.min(min, seen.lastKey());
		}

		fout.println(min);
		fout.close();
	}
	private static void update(Map<Long, Integer> m, long key, int value) {
		if (!m.containsKey(key)) m.put(key, 0);
		int multiplicity = m.get(key) + value;
		if (multiplicity == 0) m.remove(key);
		else m.put(key, multiplicity);
	}
}
