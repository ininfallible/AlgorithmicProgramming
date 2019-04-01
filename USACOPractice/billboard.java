//USACO 2017 Bronze December Q1
import java.io.*;
import java.util.*;

public class billboard {
	public static void main(String[] args) throws IOException {
		Scanner fin = new Scanner(new File("billboard.in"));
		PrintWriter fout = new PrintWriter(new BufferedWriter(new FileWriter("billboard.out")));
		int[] b1 = new int[4];
		for (int i = 0; i < 4; i++) b1[i] = fin.nextInt();
		int[] b2 = new int[4];
		for (int i = 0; i < 4; i++) b2[i] = fin.nextInt();
		int[] t = new int[4];
		for (int i = 0; i < 4; i++) t[i] = fin.nextInt();
		fin.close();

		int out = 0;
		for (int i = b1[0]; i < b1[2]; i++) //x
		for (int j = b1[1]; j < b1[3]; j++) { //y
			if (i >= t[0] && i < t[2] && j >= t[1] && j < t[3])
				continue;
			else out++;
		}
		for (int i = b2[0]; i < b2[2]; i++) //x
		for (int j = b2[1]; j < b2[3]; j++) { //y
			if (i >= t[0] && i < t[2] && j >= t[1] && j < t[3])
				continue;
			else out++;
		}

		fout.println(out);
		fout.close();
	}
}
