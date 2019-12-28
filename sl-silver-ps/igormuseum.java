//Codeforces 598 D
import java.io.*;
import java.util.*;

public class igormuseum {
	static int[][] museum;
	static int[] group = new int[10000000];
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		//Scanner sc = new Scanner(new File("igormuseum.in"));
		StringTokenizer sT = new StringTokenizer(sc.nextLine());
		final int row = Integer.parseInt(sT.nextToken());
		final int col = Integer.parseInt(sT.nextToken());
		final int q = Integer.parseInt(sT.nextToken());
		museum = new int[row][col];
		for (int i = 0; i < row; i++) {
			char[] temp = sc.nextLine().trim().toCharArray();
			for (int j = 0; j < temp.length; j++) {
				museum[i][j] = (int)temp[j];
			}
		}
		
		int n = 50;
		for (int i = 0; i < q; i++) {
			sT = new StringTokenizer(sc.nextLine());
			int r = Integer.parseInt(sT.nextToken())-1;
			int c = Integer.parseInt(sT.nextToken())-1;
			if (museum[r][c] == 46) {
				ff(r, c, n);
				n++;
			}
			System.out.println(group[(int) museum[r][c]]);

			/*
			//debug
			for (int[] x : museum) {
				for (int y : x) System.out.print(y + " "); 
				System.out.println();
			}
			*/
		}
	}

	public static void ff (int row, int col, int n) {
		if (museum[row+1][col] == 42) group[n]++;
		if (museum[row-1][col] == 42) group[n]++;
		if (museum[row][col+1] == 42) group[n]++;
		if (museum[row][col-1] == 42) group[n]++;

		museum[row][col] = n;

		if (museum[row+1][col] == 46) ff(row+1, col, n);
		if (museum[row-1][col] == 46) ff(row-1, col, n);
		if (museum[row][col+1] == 46) ff(row, col+1, n);
		if (museum[row][col-1] == 46) ff(row, col-1, n);

		return;
	}
}
