import java.util.*;
import java.io.*;

public class fairphoto {
    public static void main(String[] args) throws IOException{
        BufferedReader fin = new BufferedReader(new FileReader("fairphoto.in"));
        PrintWriter fout = new PrintWriter(new BufferedWriter(new FileWriter("fairphoto.out")));
        final int N = Integer.parseInt(fin.readLine());
        StringTokenizer sT;
        int[][] c = new int[N][2];
        for (int i = 0; i < N; i++) {
            sT = new StringTokenizer(fin.readLine());
            c[i][0] = Integer.parseInt(sT.nextToken());
            c[i][1] = sT.nextToken().equals("H")? 1 : -1;
        }
        fin.close();
        Arrays.sort(c, (a, b) -> Double.compare(a[0], b[0]));

        long[] ps = new long[N];
        ps[0] = c[0][1];
        for (int i = 1; i < N; i++) {
            ps[i] = ps[i-1] + c[i][1];
        }

        for (long i: ps) System.out.print(i + " ");
        System.out.println();
        for (int i[]: c) System.out.println(i[0] + " " + i[1]);

        int out = 0;
        for (int i = 0; i < c.length; i++) {
            for (int j = 0; j < i; j++) {
                if (ps[i] == ps[j]) {
                    out = Math.max(out, c[i][0] - c[j+1][0]);
                } else if (ps[i] - ps[j] == i-j && c[j][1] == 1||
                           ps[i] - ps[j] == j-i && c[j][1] == -1
                ) {
                    out = Math.max(out, c[i][0] - c[j][0]);
                }

            }
        }
        fout.println(out);
        fout.close();
    }
}