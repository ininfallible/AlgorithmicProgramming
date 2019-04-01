/*
ID: derekl3
LANG: JAVA
TASK: fracdec
*/

import java.io.*;
import java.util.*;

public class fracdec2 {
	static void cP (String in) { //checkPattern;
		for (int s = in.indexOf('.') + 1; s < 10; s++) { //start location
			for (int l = 1; l < Math.min((in.length() - s)/ 2 - 1, 60000); l++) { //length
				boolean valid = true;
				for (int t = 1; t < 4; t++) { //number of times to try and repeat
					if (s + (l * t) > in.length()) break; //if it will be too long to check
					if (!in.substring(s, s+l).equals(in.substring(s + l*(t-1), s + l*t))) {
						valid = false; 
						break;
					}	
				}
				if (valid && pattern.length() == 0) {
					pattern = in.substring(s, s+l);
					return;
				}
			}
		}
	}
	
	static String pattern = "";
	public static void main(String[] args) throws IOException {
		
		BufferedReader fin = new BufferedReader(new FileReader("fracdec.in"));
		PrintWriter fout =  new PrintWriter(new BufferedWriter(new FileWriter("fracdec.out")));
		StringTokenizer sT = new StringTokenizer(fin.readLine());
		int top = Integer.parseInt(sT.nextToken());
		int bottom = Integer.parseInt(sT.nextToken());
		fin.close();
		
//		int top = 1;
//		int bottom = 7;
		int r, q; //remainder, quotient
		boolean found = false;
		String dStr = "";
		r = top % bottom;
		q = top / bottom;
		StringBuilder sB = new StringBuilder(20000);
		for (int i = 0; i < 60000; i++) {
			sB.append(q);
			if (i == 0) sB.append("."); //add decimal point
			if (r == 0) break; //if decimal terminates
			r *= 10;
			q = r / bottom;
			r = r % bottom;
		}
		dStr = sB.toString();
		cP(dStr);
		System.out.println("pattern: " + pattern);
		String out = dStr;
		if (pattern.length() > 0) {
			for (int s = 0; s < dStr.length() - pattern.length(); s++) {
				if (dStr.substring(s, s + pattern.length()).equals(pattern)) {
					out = out.substring(0,s);
					break;
				}
			}
			out += "(" + pattern + ")";
		}
		if (out.charAt(out.length() - 1) == '.') out += "0";
		StringBuilder out2 = new StringBuilder(10000);
		out2.append(out);
		for (int i = out2.length(); i > 0; i--) {
			if (i % 76 == 0) out2.insert(i, '\n');
		}
		fout.println(out2.toString());
		fout.close();
	}
}
