//January 2019 USACO Silver P3
import java.io.*;
import java.util.*;

class peak implements Comparable<peak> {
	int x;
	int y;
	public peak (int X, int Y) {
		x = X;
		y = Y;
	}
	public int compareTo(peak p2) {
		return p2.y - y;
	}
}
public class mountains {
	public static void main(String[] args) throws IOException{
		BufferedReader fin = new BufferedReader(new FileReader("mountains.in"));
		PrintWriter fout = new PrintWriter(new BufferedWriter(new FileWriter("mountains.out")));
		int N = Integer.parseInt(fin.readLine());
		ArrayList<peak> peaks = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			StringTokenizer sT = new StringTokenizer(fin.readLine());
			int x = Integer.parseInt(sT.nextToken());
			int y = Integer.parseInt(sT.nextToken());
			peaks.add(new peak(x, y));
		}
		fin.close();

		Collections.sort(peaks);
		//for (peak p: peaks) System.out.println(p.y);
		
		for (int i = 0; i < peaks.size()-1; i++) {
			int x1 = peaks.get(i).x;
			int y1 = peaks.get(i).y;
			for (int j = i+1; j < peaks.size(); j++){
				int x2 = peaks.get(j).x;
				int y2 = peaks.get(j).y;
				if ((y1+x1)>=(x2+y2) && (x1-y1)<=(x2-y2)) {
					peaks.remove(j);
					j--;
				}
			}
		}

		fout.println(peaks.size());
		fout.close();
	}
}
