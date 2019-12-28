package springlight;

import java.io.*;
import java.util.StringTokenizer;

public class mixmilk {
    public static void main(String[] args) throws IOException {
        BufferedReader fin = new BufferedReader(new FileReader("mixmilk.in"));
        PrintWriter fout = new PrintWriter(new BufferedWriter(new FileWriter("mixmilk.out")));

        //read file
        StringTokenizer sT = new StringTokenizer(fin.readLine());
        int ACap = Integer.parseInt(sT.nextToken());
        int A = Integer.parseInt(sT.nextToken());
        sT = new StringTokenizer(fin.readLine());
        int BCap = Integer.parseInt(sT.nextToken());
        int B = Integer.parseInt(sT.nextToken());
        sT = new StringTokenizer(fin.readLine());
        int CCap = Integer.parseInt(sT.nextToken());
        int C = Integer.parseInt(sT.nextToken());
        fin.close();

        int toPour;
        for (int i = 0; i < 100; i++) {
            if (i % 3 == 0) { //1 > 2
                toPour = Math.min(A, BCap - B);
                B += toPour;
                A -= toPour;
            } else if (i % 3 == 1) { // 2 > 3
                toPour = Math.min(B, CCap - C);
                C += toPour;
                B -= toPour;
            } else if (i % 3 == 2) { // 3 > 1
                toPour = Math.min(C, ACap - A);
                A += toPour;
                C -= toPour;
            }
        }
        fout.println(A);
        fout.println(B);
        fout.println(C);
        fout.close();
    }
}
