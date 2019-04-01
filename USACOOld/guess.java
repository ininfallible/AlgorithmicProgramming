//2019 January USACO Bronze P3
import java.io.*;
import java.util.*;

class animal {
	public String name = "";
	public String[] traits;

	public animal(String N, String[] T) {
		name = N;
		traits = (String[]) T.clone();
	}

	public boolean hasTrait(String in) {
		for (String s: traits)
			if (s.equals(in)) return true;
		return false;
	}

}


public class guess {
	public static void main(String[] args) throws IOException {
		BufferedReader fin = new BufferedReader(new FileReader("guess.in"));
		PrintWriter fout = new PrintWriter(new BufferedWriter(new FileWriter("guess.out")));
		
		int N = Integer.parseInt(fin.readLine());
		StringTokenizer sT;

		animal[] animals = new animal[N];
		TreeSet<String> allTraits = new TreeSet<>();
		
		for (int i = 0; i < N; i++) { //fill out animals
			sT = new StringTokenizer(fin.readLine());
			String n = sT.nextToken(); //name
			int k = Integer.parseInt(sT.nextToken()); // number of traits
			//fout.println("k: " + k);//debug
			String[] t = new String[k];
			for (int l = 0; l < k; l++) { // read traits from line
				//fout.println(sT.nextToken()); //debug
				t[l] = sT.nextToken();
				allTraits.add(t[l]); //add to set of traits;
			}
			animals[i] =  (new animal(n, t)); //add entry to animals
		}
		fin.close();

		//fout.println("P0");
		int max = 0;
		for (int i = 0; i < animals.length - 1; i++) {
			int total = 0;
			for (int j = i + 1; j < animals.length; j++) {
				total = 0;
				for (String t: animals[i].traits) {
					if (animals[j].hasTrait(t)) total++;
				}
				max = Math.max(total, max);
			}
		}
		fout.println(max + 1);
		fout.close();
	}
}
