//2013 US Open Silver P1
import java.io.*;
import java.util.*;

public class gravity {
	static int N, M;
	static char[][] ar;
	static int up[][];
	static int down[][];

	static int pre() {
		int i,j,k;
		for(i=0;i<N;i++){
			for(j=0;j<M;j++){
				if(ar[i][j]=='#') continue;
				if(i==0){
					up[i][j] = -1;
					continue;
				}
				if(ar[i-1][j]=='#')
					up[i][j]=i;
				else
					up[i][j]=up[i-1][j];
			}
		}
		
		for(i=N-1;i>=0;i--){
			for(j=0;j<M;j++){
				if(ar[i][j]=='#') continue;
				if(i==N-1){
					down[i][j] = -1;
					continue;
				}
				if(ar[i+1][j]=='#')
					down[i][j]=i;
				else
					down[i][j]=down[i+1][j];
			}
		}
		return 0;
	}

	static int SX,SY,EX,EY;
	static int[][][] used;
	static ArrayList<Pair> tp = new ArrayList<>();
	static int G;

	static int BFS() {
		int ret;
		int startx, endx;
		tp.add(new Pair(SX, SY));
		used[SX][SY][0] = 1;
		G = 0;
		int c=0;
		while(expand()==0){
			G=G==0?1:0;
			c++;
		}
		return c;
	}



	static int expand() {
		int x,y;
		int nx, ny;
		for(int i=0;i<tp.size();i++) {
			x = tp.get(i).a;
			y = tp.get(i).b;
			if(x==EX&&y==EY) {
				return 1;
			}
			nx = x;
			ny = y-1;
			if (ny>=0&&ny<M&&ar[nx][ny]!='#') {
				if(G==0) nx = down[nx][ny];
				else nx = up[nx][ny];
				if(nx!=-1 && used[nx][ny][G]==0) {
					tp.add(new Pair(nx,ny));
					used[nx][ny][G]=1;
				}
			}
			nx = x;
			ny = y+1;
			if (ny>=0&&ny<M&&ar[nx][ny]!='#') {
				if(G==0) nx = down[nx][ny];
				else nx = up[nx][ny];
				if(nx!=-1 && used[nx][ny][G]==0) {
					tp.add(new Pair(nx,ny));
					used[nx][ny][G]=1;
				}
			}
		}
		return 0;
	}

	public static void main (String[] args) throws IOException{
		BufferedReader fin = new BufferedReader(new FileReader("gravity.in"));
		PrintWriter fout = new PrintWriter(new BufferedWriter(new FileWriter("gravity.out")));
		StringTokenizer sT = new StringTokenizer(fin.readLine());
		N = Integer.parseInt(sT.nextToken());
		M = Integer.parseInt(sT.nextToken());
		ar = new char[N+1][];
		up = new int[N+1][M+1];
		down = new int[N+1][M+1];
		used = new int[N+1][M+1][2];
		for(int i=0;i<N;i++){
			ar[i] = fin.readLine().toCharArray();
		}
		fin.close();
		//pre-calculate edges upside-down/right-side up
		pre();
		for(int i=0;i<N;i++){
			for(int j=0;j<M;j++){
				if(ar[i][j]=='C'){
					SX=i;
					SY=j;
				}
				if(ar[i][j]=='D'){
					EX=i;
					EY=j;
				}
			}
		}	
		fout.println(BFS());

		
		fout.close();
	}
}

class Pair implements Comparable<Pair>{
	public int a, b;
	public Pair(int a, int b) {
		this.a = a;
		this.b = b;
	}
	public int compareTo(Pair other) {
		return this.a-other.a;
	}
}
