package old2_3;
/*
ID: derekl3
LANG: JAVA
TASK: prefix
*/
import java.io.*;
import java.util.*;


public class prefix3 {
	static String[] prefixes = new String[200];
	static int max = 0;
	static int count = 0;
	
	static boolean possible(String in, String pf) { //prefix, possible
		//System.out.println(in + " " + pf);
		if (pf.length() <= in.length()) return pf.equals(in.substring(0, pf.length()));
		return false;
	}
	//greedy
	static void search(String in, int l) {
		String mS = "";
		for (int t = 0; t < count-1; t++) {
			if (possible(in, prefixes[t]) && prefixes[t].length() > mS.length()) mS = prefixes[t];
		}
		if (mS.length() > 0) search(in.substring(mS.length()), l + mS.length());
		else max = l;
	}
	/* branches for all possible
	static void search(String in, int l) {
		for (int t = 0; t < count-1; t++) {
			String i = prefixes[t];
			if (possible(in, i)) {
				if (l + i.length() > max) {
					max = l + i.length();
					search(in.substring(i.length(), in.length()), l + i.length); // branches when more than one is available
				}
			}
		}
	}
	*/
	public static void main(String[] args) throws IOException {
		BufferedReader fin = new BufferedReader(new FileReader("prefix.in"));
		PrintWriter fout = new PrintWriter(new BufferedWriter(new FileWriter("prefix.out")));
		boolean p = false;
		count = 0;
		while (!p) {
			StringTokenizer sT = new StringTokenizer(fin.readLine());
			while (sT.hasMoreTokens()){
				String t = sT.nextToken();
				if (t.equals(".")) p = true;
				else prefixes[count] = t;
				count++;
			}
		}
		String s = fin.readLine();
		for (int i = 0; i < 2667; i++) s += fin.readLine();
		fin.close();
		//prefixes = new String[]  {"A", "AB", "BA", "CA", "BBC"};
		//String s = "ABABACABAABC";
		search(s, 0);
		
		fout.println(max);
		fout.close();
	}
}
