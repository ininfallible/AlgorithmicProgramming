//December 2017 USACO Silver P1
import java.io.*;
import java.util.*;

public class homework { 
	static int[] Q;
	static int minQ = 0;
	static int minIndex = 0;
	static long sum = 0;

	private static void updateMin (int start) { //inclusive
		sum += minQ;
		minQ = 100000;
		for (int i = start; i < Q.length; i++)
		if (Q[i] <= minQ) { //will determine index of last min if tie
			minQ = Q[i];
			minIndex = i;
		}
		sum -= minQ;
		return;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader fin = new BufferedReader(new FileReader("homework.in"));
		PrintWriter fout = new PrintWriter(new BufferedWriter(new FileWriter("homework.out")));

		int N = Integer.parseInt(fin.readLine());
		StringTokenizer sT = new StringTokenizer(fin.readLine());
		fin.close();
		Q = new int[N];
		for (int i = 0; i < N; i++) {
			Q[i] = Integer.parseInt(sT.nextToken());
			sum += Q[i];
		}
		
		//algorithm
		/* debug
		System.out.println(minQ);
		System.out.println(minIndex);
		System.out.println(sum);
		*/
		int numQ = N-1;

		ArrayList<Integer> maxList = new ArrayList<>();
		double maxAvg = 0;
		for (int k = 0; k < Q.length - 2; k++) {
			if (k == minIndex) {//if at minimum
				updateMin(k + 1);
			}
			
			sum -= Q[k];
			numQ--;

			double avg = (double) sum / numQ;

			//debug
			
			//System.out.printf("k: %3d, minI: %3d, sum: %3d, numQ: %3d, avg: %3.1f\n", k, minIndex, sum, numQ, avg);
			
			if (avg > maxAvg) {
				maxList.clear();
				maxAvg = avg;
				maxList.add(k+1);
			} else if (avg == maxAvg) {
				maxList.add(k+1);
			}
		}
		System.out.println(maxList); //debug

		for (int i : maxList) fout.println(i);
		fout.close();
	}
}
