import java.io.*;
import java.util.*;

public class nubianmarket {
	public static void main(String[] args) throws IOException {
		Scanner fin = new Scanner(System.in);
		//Scanner fin = new Scanner(new File("nubianmarket.txt"));
		int N = fin.nextInt();
		int B = fin.nextInt();
		int[] s = new int[N]; //souveniers
		s[0] = fin.nextInt() + 1;
		for (int i = 1; i < N; i++) {
			s[i] = s[i-1] + fin.nextInt() + i*(i+1)/2+ (i+1)*(i+1) ;
		}
	
		
		/*
		for (int i = 0; i < N; i++) {
			System.out.println(s[i] + " ");
		}
		*/
		
		
		fin.close();
		int low = -1, high = N, mid = 0;
		while (low + 1 < high) {
			mid = (low + high) / 2;
			//System.out.println(mid);
			if (s[mid] <= B)
				low = mid;
			else
				high = mid;
		}
		System.out.println((1+low) + " " + s[low]);
	}
}
