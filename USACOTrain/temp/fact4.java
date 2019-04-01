package temp;/*
ID: derekl3
LANG: JAVA
TASK: temp.fact4
*/
import java.io.*;
import java.util.*;

public class fact4 {
    public static void main(String[] args) throws IOException{
        BufferedReader fin = new BufferedReader(new FileReader("temp.fact4.in"));
        PrintWriter fout = new PrintWriter(new BufferedWriter(new FileWriter("temp.fact4.out")));
        int fact = Integer.parseInt(fin.readLine());
        fin.close();
        long cur = 1;
        for (int i = 2; i <= fact; i++){
            cur = (cur * i) % 100000;
            while (cur % 10 == 0) cur /= 10;
        }
        fout.println(cur % 10);
        fout.close();
    }
}
