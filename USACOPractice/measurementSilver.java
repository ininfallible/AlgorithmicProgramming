import java.io.*;
import java.util.*;
class pair {
	int a = 0;
	int b = 0;
	public pair(int A, int B) {
		a = A;
		b = B;
	}
}
public class measurementSilver {
	public static void main(String[] args) throws IOException {
		BufferedReader fin = new BufferedReader(new FileReader("measurement.in"));
		PrintWriter fout = new PrintWriter(new BufferedWriter(new FileWriter("measurement.out")));
		StringTokenizer sT = new StringTokenizer(fin.readLine());
		int N = Integer.parseInt(sT.nextToken());
		int G = Integer.parseInt(sT.nextToken());

		TreeMap<Integer, pair> log = new TreeMap<>();
		HashSet<Integer> top = new HashSet();
		HashMap<Integer, Integer> cows = new HashMap<>();

		cows.put(-1, G); //all the other cows
		top.add(-1);
		for (int i = 0; i < N; i++) {
			sT = new StringTokenizer(fin.readLine());
			int temp = Integer.parseInt(sT.nextToken());
			pair temp2 = new pair(Integer.parseInt(sT.nextToken()), Integer.parseInt(sT.nextToken()));
			cows.put(temp2.a, G);
			top.add(temp2.a);
			log.put(temp, temp2);
		}
		fin.close();
		
		int max = G; //will never be below
		int c = 0;
		for (int i: log.keySet()) { //iterate days in ascending order
			boolean changed = false;
			int id = log.get(i).a;
			int diff = log.get(i).b;
			cows.put(id, cows.get(id) + diff); //make change
/*
			System.out.println("day: " + i); //debug
			System.out.println("id: " + id + " diff: " + diff);
			System.out.println("max: " + max);
			System.out.println(top);
*/
			if (cows.get(id) > max) {
				max = cows.get(id);
				if (!top.contains(id)) changed = true;
				else if (top.size() > 1) changed = true; //since everything else removed
				top.clear();
				top.add(id);
			}
			else if (cows.get(id) == max && !top.contains(id)) {
				top.add(id);
				changed = true;
			}
			else if (cows.get(id) < max && top.contains(id)) {
				if (top.size() == 1) { //update max 
					max = 0;
					for (int j : cows.keySet()) { //for each cow
						if (cows.get(j) > max) {
							max = cows.get(j);
							top.clear();
							top.add(j);
						} else if (cows.get(j) == max) {
							top.add(j);
						}
					}
					if (!top.contains(id) || top.size() > 1) changed = true;
				} else {
					top.remove(id);
					changed = true;
				}
			}

			if (changed) c++;
			//System.out.println(top);
		}

		fout.println(c);
		fout.close();
	
	}

}
