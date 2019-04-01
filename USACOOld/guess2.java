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
	private static int search(ArrayList<animal> aList, TreeSet<String> tSet) {
		if (aList.size() <= 1) return 0;
 		//determine maximum traits
		int max = 0;
		ArrayList<String> maxList = new ArrayList<>();
		for (String t: tSet) {
			int count = 0;
			for (animal a: aList) {
				if (a.hasTrait(t)) count++;
			}
			if (count > max) {
				maxList.clear(); //reset ArrayList
				maxList.add(t);
				max = count; //update maximum
			}
			else if (count == max) maxList.add(t);
		}
		//recursive step
		int maxY = 0;
		for (String t: maxList) {
			ArrayList<animal> nAList = (ArrayList<animal>) aList.clone();
			int i  = 0;
			while (i < nAList.size()) {
				if (!nAList.get(i).hasTrait(t)) nAList.remove(i);
				else i++;
			}

			TreeSet<String> nTSet = (TreeSet<String>) tSet.clone();
			nTSet.remove(t);

			maxY = Math.max(maxY, search(nAList, nTSet));
		}
		return maxY + 1;
	}
	public static void main(String[] args) throws IOException {
		BufferedReader fin = new BufferedReader(new FileReader("guess.in"));
		PrintWriter fout = new PrintWriter(new BufferedWriter(new FileWriter("guess.out")));
		
		int N = Integer.parseInt(fin.readLine());
		StringTokenizer sT;

		ArrayList<animal> animals = new ArrayList<>();
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
			animals.add(new animal(n, t)); //add entry to animals
		}
		fin.close();

		//fout.println("P0");

		fout.println(search(animals, allTraits));
		fout.close();
	}
}
