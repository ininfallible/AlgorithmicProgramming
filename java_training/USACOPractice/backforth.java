//2018 December contest Question 3 Bronze
import java.io.*;
import java.util.*;

public class backforth {
	static TreeSet<Integer> p = new TreeSet<>();

	public static void DFS(ArrayList<Integer> b1, ArrayList<Integer> b2, int v, int d) {
		if (d == 4) {
			p.add(v);
			return;
		}
		if (d == 0 || d == 2) { //Tues, Thurs : b1 -> b2
			for (int i = 0; i < b1.size(); i++) {
				ArrayList<Integer> nB1 = (ArrayList<Integer>) b1.clone();
				ArrayList<Integer> nB2 = (ArrayList<Integer>) b2.clone();
				int temp = b1.get(i);

				nB1.remove(i);
				nB2.add(temp);
				DFS(nB1, nB2, v - temp, d+1);
			}
		} else { //Wed, Fri : b2 -> b1
			for (int i = 0; i < b2.size(); i++) {
				ArrayList<Integer> nB1 = (ArrayList<Integer>) b1.clone();
				ArrayList<Integer> nB2 = (ArrayList<Integer>) b2.clone();
				int temp = b2.get(i);

				nB2.remove(i);
				nB1.add(temp);
				DFS(nB1, nB2, v + temp, d+1);
			}
		}
	}

	public static void main(String[] args) throws IOException{
		BufferedReader fin = new BufferedReader(new FileReader("backforth.in"));
		PrintWriter fout = new PrintWriter(new BufferedWriter(new FileWriter("backforth.out")));
		
		ArrayList<Integer> b1 = new ArrayList<>();
		ArrayList<Integer> b2 = new ArrayList<>();

		StringTokenizer sT1 = new StringTokenizer(fin.readLine());
		StringTokenizer sT2 = new StringTokenizer(fin.readLine());
		for (int i = 0; i < 10; i++) {
			b1.add(Integer.parseInt(sT1.nextToken()));
			b2.add(Integer.parseInt(sT2.nextToken()));
		}
		fin.close();
		DFS(b1, b2, 1000, 0);
	
		System.out.println(p);
		fout.println(p.size());
		fout.close();
	}
}
	
