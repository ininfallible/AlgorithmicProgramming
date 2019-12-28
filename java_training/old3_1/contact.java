package old3_1;/*
ID: derekl3
LANG: JAVA
TASK: old3_1.contact
*/

import java.io.*;
import java.util.*;

class Pair implements Comparable<Pair> { //container variable
		String a;
		int b;
		Pair(String a, int b) {
			this.a = a;
			this.b = b;
		}
		public int compareTo(Pair other) {
			int tInt = this.getInt(), oInt = other.getInt();
			String tStr = this.getStr(), oStr = other.getStr();
			if (tInt != oInt) return tInt<oInt? 1 : -1;
			if (tStr.length() != oStr.length()) return tStr.length()>oStr.length()? 1 : -1;
			for (int i = 0; i < Math.min(tStr.length(), oStr.length()); i++) {
				if (tStr.charAt(i) != oStr.charAt(i)) return tStr.charAt(i)>oStr.charAt(i)? 1 : -1;
			}
			return 0;
		}
		public String getStr() {
			return a;
		}
		public int getInt() {
			return b;
		}
	}

public class contact {

	public static void main(String[] args) throws IOException {
		//read input
		BufferedReader fin = new BufferedReader(new FileReader("old3_1.contact.in"));
		PrintWriter fout = new PrintWriter(new BufferedWriter(new FileWriter("old3_1.contact.out")));
		StringTokenizer sT = new StringTokenizer(fin.readLine());
		int min = Integer.parseInt(sT.nextToken()); //min length
		int max = Integer.parseInt(sT.nextToken()); //max length
		int N = Integer.parseInt(sT.nextToken()); //numbers to display
		StringBuilder in = new StringBuilder(200000);
		String temp = fin.readLine();
		while(temp != null) {
			in.append(temp);
			temp = fin.readLine();
		}
		fin.close();
		//find patterns in in
		HashMap<String, Integer> patterns = new HashMap<>();
		for (int i = 0; i <= in.length(); i++) { //position
			for (int j = min; j <= max; j++) {//length
				if (i + j <= in.length()) {
					String cur = in.substring(i, i + j);
					if (!patterns.containsKey(cur)) {
						patterns.put(cur, 1);	
					} else {
						patterns.put(cur, patterns.get(cur) + 1);
					}
				}
			}
		}
		//unload HashMap as array of pairs
		ArrayList<Pair> mP = new ArrayList<>(); //maxPattern
		for (String k : patterns.keySet()) {
			mP.add(new Pair(k, patterns.get(k)));
		}
		//sort by frequency, then binary value
		Collections.sort(mP);
		//print out
		//for (old3_1.Pair l: mP) {System.out.println(l.getInt() + " " + l.getStr());}
		int curF = mP.get(0).getInt();
		int c = 0;
		int d = 1;
		String ln = "";
		for (int l = 0; l < mP.size(); l++) {
			ln += mP.get(l).getStr();
			if(d % 6 == 0) {ln += "\n";}
			else {ln += " ";}
			d++;
			if (c == N) break;
			if (l == mP.size()-1 || mP.get(Math.min(l+1, mP.size()-1)).getInt() != curF) { //if end or next freq is different
				fout.println(curF);
				fout.println(ln.trim());
				ln = "";
				curF = mP.get(Math.min(mP.size()-1, l+1)).getInt();
				c++;
				d = 1;
			}
		}
		fout.close();
	}
}
