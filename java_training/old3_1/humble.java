package old3_1;/*
ID: derekl3
LANG: JAVA
TASK: old3_1.humble
*/

import java.io.*;
import java.util.*;

public class humble {

	public static void main(String[] args) throws IOException {
		BufferedReader fin = new BufferedReader(new FileReader("old3_1.humble.in"));
		PrintWriter fout = new PrintWriter(new BufferedWriter(new FileWriter("old3_1.humble.out")));
		StringTokenizer sT = new StringTokenizer(fin.readLine());
		int numPrime = Integer.parseInt(sT.nextToken());
		int maxHum = Integer.parseInt(sT.nextToken());
		int[] primes = new int[numPrime];
		long[] humbles = new long[maxHum+1];
		sT = new StringTokenizer(fin.readLine());
		for (int i = 0; i < numPrime; i++) primes[i] = Integer.parseInt(sT.nextToken());
		fin.close();
		//read file
		int nHum = 0;
		humbles[0] = 1;
		long min;
		int minp;
		int[] pIndex = new int[numPrime]; //stores last old3_1.humble number used by each prime
		while (nHum < maxHum) {
			min = 0x7FFFFFFF;
			minp = -1;
			//System.out.println("nhum: " + nHum);
			for (int j = 0; j < numPrime; j++) { //for each prime
				while ((long)primes[j] * humbles[pIndex[j]] <= humbles[nHum]) pIndex[j]++;
				//System.out.println("checking: " + primes[j]);
				//System.out.println("multiplying by:" + humbles[pIndex[j]]);
				if ((long) primes[j] * humbles[pIndex[j]] < min) {
					min = primes[j] * humbles[pIndex[j]]; //updates minimum old3_1.humble greater than current
					minp = j; //minimum prime index
				}
			}	
			nHum++;
			humbles[nHum] = min;
			pIndex[minp]++;
		}
		
		fout.println(humbles[maxHum]);
		fout.close();
	}
}
