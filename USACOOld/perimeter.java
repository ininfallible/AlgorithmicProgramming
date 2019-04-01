//January 2019 USACO Silver P2
import java.io.*;
import java.util.*;

class pair {
	int x;
	int y;
	public pair (int X, int Y) {
		x = X;
		y = Y;
	}
}
public class perimeter {
	public static void main(String[] args) throws IOException {
	 	BufferedReader fin = new BufferedReader(new FileReader("perimeter.in"));
		PrintWriter fout = new PrintWriter(new BufferedWriter(new FileWriter("perimeter.out")));
		int N = Integer.parseInt(fin.readLine());
		int[][] blobs = new int[N+2][N+2];
		for (int i = 1; i < N + 1; i++) {
			String line = fin.readLine();
			for (int j = 1; j < N + 1; j++) {
				if (line.charAt(j-1) == '#') {
					blobs[i][j] = 1;
				}
			}
		}
		fin.close();

		pair maxBlob = new pair(0, 1000000);
		//floodfill
		for (int i = 1; i < blobs.length-1; i++)
		for (int j = 1; j < blobs.length-1; j++) {
			if (blobs[i][j] == 1) {
				int p = 0;
				int a = 0;
				//floodfill loop
				LinkedList<pair> tP = new LinkedList<>();
				tP.add(new pair(i, j));
				boolean[][] v = new boolean[N+2][N+2];
				while (!tP.isEmpty()) {
					pair cur = tP.pop();
					int x = cur.x;
					int y = cur.y;
					if (v[x][y]) continue;
					v[x][y] = true;
					blobs[x][y] = -1;
					a++;
					if (blobs[x-1][y] == 0) p++;
					if (blobs[x][y+1] == 0) p++;
					if (blobs[x+1][y] == 0) p++;
					if (blobs[x][y-1] == 0) p++;

					if (blobs[x-1][y] == 1 && !v[x-1][y]) tP.add(new pair(x-1, y));
					if (blobs[x][y+1] == 1 && !v[x][y+1]) tP.add(new pair(x, y+1));
					if (blobs[x+1][y] == 1 && !v[x+1][y]) tP.add(new pair(x+1, y));
					if (blobs[x][y-1] == 1 && !v[x][y-1]) tP.add(new pair(x, y-1));

				}
				if (a >= maxBlob.x) {
					if (a > maxBlob.x) maxBlob.y = 10000000;
					maxBlob.x = a;
					if (p < maxBlob.y) maxBlob.y = p;
				}
				/*
				for (int[] test1 : blobs) {//debug
					for (int test2: test1) System.out.print(test2);
					System.out.println();
				}
				System.out.println(a + " " + p);
				*/
			}
		}
		
		fout.println(maxBlob.x + " " + maxBlob.y);
		fout.close();
	}
}

