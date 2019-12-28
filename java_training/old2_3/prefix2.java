package old2_3;
/*
ID: derekl3
LANG: JAVA
TASK: prefix
*/
import java.io.*;
import java.util.*;

public class prefix2 {
	static ArrayList<String> prefixes = new ArrayList<>();
	static int max = 0;
	static int numPf= 0;
	
	static class lenComp implements Comparator<String>{
		public int compare(String a, String b) {
			return (a.length() < b.length())? 1: -1;
		}
	}
		
	public static void main(String[] args) throws IOException {
		BufferedReader fin = new BufferedReader(new FileReader("prefix.in"));
		PrintWriter fout = new PrintWriter(new BufferedWriter(new FileWriter("prefix.out")));
		boolean p = false;
		numPf = 0;
		while (!p) {
			StringTokenizer sT = new StringTokenizer(fin.readLine());
			while (sT.hasMoreTokens()){
				String t = sT.nextToken();
				if (t.equals(".")) p = true;
				else prefixes.add(t);
				numPf++;
			}
		}
		String s = fin.readLine();
		String j;
		while ((j = fin.readLine()) != null) s += j;
		fin.close();
		String mP;
		Collections.sort(prefixes, new lenComp());
		while (s.length() > 0) {
			System.out.println(s.length());
			mP = "";
			for (int t = 0; t < numPf-1; t++) {
				String cur = prefixes.get(t);
				if (cur.length() <= s.length() && cur.equals(s.substring(0, cur.length())) && cur.length() > mP.length()) {mP = cur; break;}
			}
			s = s.substring(mP.length());
			max += mP.length();
			if (mP.length() == 0) break;
		}
		fout.println(max);
		fout.close();
	}
}
