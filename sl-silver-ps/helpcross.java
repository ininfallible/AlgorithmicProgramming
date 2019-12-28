import java.util.*;
import java.io.*;

public class helpcross {
    public static void main(String[] args) throws IOException {
        BufferedReader fin = new BufferedReader(new FileReader("helpcross.in"));
        PrintWriter fout = new PrintWriter(new BufferedWriter(new FileWriter("helpcross.out")));
        StringTokenizer sT = new StringTokenizer(fin.readLine());
        final int C = Integer.parseInt(sT.nextToken()); //no. chickens
        final int N = Integer.parseInt(sT.nextToken()); //no. cows
        final int BIG_INT = 2000000000;
        int[] c = new int[C];
        for (int i = 0; i < C; i++) {
            c[i] = Integer.parseInt(fin.readLine());
        }
        int[][] n = new int[N][2];
        for (int i = 0; i < N; i++) {
            sT = new StringTokenizer(fin.readLine());
            n[i][0] = Integer.parseInt(sT.nextToken());
            n[i][1] = Integer.parseInt(sT.nextToken());
        }
        fin.close();
        int out = 0;

        Arrays.sort(c);
        
        for (int cur : c) {
            int min = BIG_INT; //min index that the chicken can serve
            int minIndex = -1;
            for (int i = 0; i < n.length; i++) {
                if (n[i][0] <= cur && n[i][1] >= cur && n[i][1] < min) {
                    min = n[i][1];
                    minIndex = i;
                }
            }
            if (minIndex != -1) {
                out++;
                n[minIndex][0] = BIG_INT;
            }
        }

        fout.println(out);
        fout.close();
    }
}