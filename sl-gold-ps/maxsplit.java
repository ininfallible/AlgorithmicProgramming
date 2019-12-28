//Codeforces 870 C
//DOESNT WORK (find formula for larger inputs)
import java.io.*;
import java.util.*;

public class maxsplit {
	static int[] sieve = new int[2000];
	static ArrayList<Integer> pos = new ArrayList<>();

	static void pre() {
		int K = 2000;
		for(int i=2;i<K;i++) {
			if(sieve[i]==1) continue;
			for(int j=i*2;j<K;j+=i) sieve[j]=1;
		}
		for(int i=4;i<K;i++) if(sieve[i]==1) pos.add(i);
	}

	static int[] used = new int[1100];
	static int[] dp = new int[1100];
	static int f(int x) {
		//base cases: x<4
		if(x<4) {
			if(x==0) return 0;
			return 1<<31;
		}
		if (used[x]==1) return dp[x];

		int ret = 1<<31;
		for(int a:pos) {
			if(a>x) break;
			ret = Math.max(ret,f(x-a)+1);
		}
		used[x] = 1;
		dp[x] = ret;
		return ret;
	}

	public static void main(String[] args) throws IOException{
		pre();
		for (int i=0;i<50;i++) System.out.println(f(i));
		//Scanner fin = new Scanner(System.in);
		//int cur = fin.nextInt();
		//System.out.println(f(cur));
	}
}
