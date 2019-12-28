/*
ID: derekl3
LANG: JAVA
TASK: fracdec
*/

import java.io.*;
import java.util.*;

public class fracdec {

	public static void main(String[] args) throws IOException {
		
		BufferedReader fin = new BufferedReader(new FileReader("fracdec.in"));
		PrintWriter fout = new PrintWriter(new BufferedWriter(new FileWriter("fracdec.out")));
		StringTokenizer sT = new StringTokenizer(fin.readLine());
		int N = Integer.parseInt(sT.nextToken()); //Numerator (top)
		int D = Integer.parseInt(sT.nextToken()); //Denominator (bottom)
		fin.close();
		//find integer in front
		StringBuilder out = new StringBuilder(50000);
		int tA = N / D; //to append
		int r = N % D;
		out.append(tA); //integer at front
		out.append(".");
		HashMap<Integer, Integer> rI = new HashMap<>(); //maps remainders to their index
		int pos = out.length() - 1;
		while (r > 0) {		
			r *= 10;
			tA = r / D;
			r %= D;
			pos++;
			if (rI.containsKey(r) && out.charAt(rI.get(r)) - '0' == tA) {
				out.append(')');
				out.insert(rI.get(r), "(");
				break;
			}
			System.out.println("r " + r);
			rI.put(r, pos); //put first occurence of this remainder with index
			out.append(tA);
		}
		if (out.charAt(out.length() - 1) == '.') out.append('0');
		System.out.println(out.toString());
		String outStr = out.toString();
		while (outStr.length() > 76) {
			fout.println(outStr.substring(0, 76));
			outStr = outStr.substring(76);
		}
		fout.println(outStr);
		fout.close();
	}
}
