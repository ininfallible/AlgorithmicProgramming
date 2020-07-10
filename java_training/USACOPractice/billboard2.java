//USACO 2018 January Bronze P1
import java.io.*;
import java.util.*;
public class billboard2 {
	public static void main(String[] args) throws IOException {
		BufferedReader fin = new BufferedReader(new FileReader("billboard.in"));
		PrintWriter fout = new PrintWriter(new BufferedWriter(new FileWriter("billboard.out")));
		StringTokenizer sT = new StringTokenizer(fin.readLine());
		int[] b1 = new int[4]; //lawnmower billboard
		for (int i = 0; i < 4; i++) b1[i] = Integer.parseInt(sT.nextToken());
		sT = new StringTokenizer(fin.readLine());
		int[] b2 = new int[4]; //feed billboard
		for (int i = 0; i < 4; i++) b2[i] = Integer.parseInt(sT.nextToken());
		fin.close();

		
	
	
		fout.close();
	}
}
