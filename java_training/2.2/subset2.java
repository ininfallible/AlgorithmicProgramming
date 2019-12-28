/*
ID: derekl3
LANG: JAVA
TASK: subset
*/
import java.io.*;
import java.util.*;
public class subset2 {
	public static int cAS(int in) { //calculateArithsequenceSum
		return ((1+in)*in)/2;
	}
	public static void main(String[] args) throws IOException {
		BufferedReader fin = new BufferedReader(new FileReader("subset.in"));
		PrintWriter fout = new PrintWriter(new BufferedWriter(new FileWriter("subset.out")));
		int in = Integer.parseInt(fin.readLine()), out = 0;
		fin.close();
		LinkedList<int[]> tP = new LinkedList<>();
		int[] first = new int[in];
		first[0] = 1;
		tP.add(first);
		if (cAS(in) % 2 == 1) {
			fout.println(0);
		} else {
			//implement bfs to determine sums
			while (!tP.isEmpty()) {
				int[] cur = tP.pop();
				int sum = 0, lIP = cur.length-1; // lastIntPos
				while(cur[lIP] == 0 && lIP > 0) lIP--;
				lIP++;
				for (int i : cur) sum += i; //find sum
				//for (int k : cur) System.out.print(k + " ");
				//System.out.println();
				//System.out.println("P1, " + lIP);
				if (sum == cAS(in)/2) out++; //handle base
				if (sum < cAS(in)/2) {//if not base, add children
					for (int j = lIP; j < cur.length; j++) {
						cur[j] = j+1;
						tP.add(cur.clone());
						cur[j] = 0;
					}
				}
			}
			fout.println(out);
		}
		fout.close();
	}
}
