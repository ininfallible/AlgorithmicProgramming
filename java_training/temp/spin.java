package temp;/*
ID: derek.l3
LANG: JAVA
TASK: temp.spin
*/
import java.util.*;
import java.io.*;

public class spin {
	public static void main(String[] args) throws IOException {
		BufferedReader fin = new BufferedReader(new FileReader("temp.spin.in"));
		PrintWriter fout = new PrintWriter(new BufferedWriter(new FileWriter("temp.spin.out")));
		
		//read file
		int[] speeds = new int[5];
		int[][] starts = new int[5][];
		int[][] extents = new int[5][];
		for (int i = 0; i < 5; i++) {
			StringTokenizer sT = new StringTokenizer(fin.readLine());
			speeds[i] = Integer.parseInt(sT.nextToken());
			int nS = Integer.parseInt(sT.nextToken());
			starts[i] = new int[nS];
			extents[i] = new int[nS];
			for (int j = 0; j < nS; j++) {
				starts[i][j] = Integer.parseInt(sT.nextToken());
				extents[i][j] = Integer.parseInt(sT.nextToken());
			}
		}
		fin.close();

		//calculations
		int t;
		for (t = 0; t < 500; t++) { //seconds
			boolean valid = false;
			for (int angle = 0; angle < 720; angle++) {
				boolean[] found = new boolean[5];
				for (int i = 0; i < 5; i++) { //for each disk
					for (int j = 0; j < starts[i].length; j++) {
						int start = (starts[i][j] + t*speeds[i])%360;
						int end = start + extents[i][j];
						if ((angle%360 >= start && angle%360 <= end) ||
							(angle >= start && angle <= end)) {
							found[i] = true;
							break;
						}
					}
					if (found[0]&&found[1]&&found[2]&&found[3]&&found[4]) valid = true;
				}
			}
			if (valid) break;
		}
		fout.println(t==500? "none" : t);
		fout.close();
	}
}
