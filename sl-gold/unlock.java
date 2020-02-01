//2012 US Open Silver P1

import java.io.*;
import java.util.*;

public class unlock {
	static int S = 28;
	static int N1, N2, N3;
	static int[] n = new int[4];
	static LinkedList<State> tp = new LinkedList<>();
	static int[][][] startbb = new int[4][2][2];
	static int[][] start = new int[S][S];
	public static void main(String[] args) throws IOException{
		BufferedReader fin = new BufferedReader(new FileReader("unlock.in"));
		PrintWriter fout = new PrintWriter(new BufferedWriter(new FileWriter("unlock.out")));
		StringTokenizer sT = new StringTokenizer(fin.readLine());

		N1 = Integer.parseInt(sT.nextToken());
		n[1] = N1;
		N2 = Integer.parseInt(sT.nextToken());
		n[2] = N2;
		N3 = Integer.parseInt(sT.nextToken());
		n[3] = N3;
		//x1 y1 min
		//x2 y2 max
		for(int i=1;i<=3;i++){
			startbb[i][0][0]=1<<30;
			startbb[i][0][1]=1<<30;
			for(int j=0;j<n[i];j++) {
				sT = new StringTokenizer(fin.readLine());
				int x = Integer.parseInt(sT.nextToken())+9;
				int y = Integer.parseInt(sT.nextToken())+9;
				start[x][y] = i;
				startbb[i][0][0] = Math.min(x,startbb[i][0][0]);
				startbb[i][0][1] = Math.min(y,startbb[i][0][1]);
				startbb[i][1][0] = Math.max(x,startbb[i][1][0]);
				startbb[i][1][1] = Math.max(y,startbb[i][1][1]);
			}
		}
		fin.close();
		/*
		for(int i=1;i<=3;i++) {
			System.out.println(bb[i][0][0] + "  " + bb[i][0][1]);
			System.out.println(bb[i][1][0] + "  " + bb[i][1][1]);
			System.out.println();
		}
		for(int[] i: start){
			for(int j:i) System.out.printf("%2d",j);
			System.out.println();
		}
		*/
		tp.add(new State(start, startbb, 0));
		ArrayList<int[][][]> visited = new ArrayList<>();
		int out = -1;
		while(!tp.isEmpty()){
			State cur = tp.pop();
			//debug
			for(int[] i: cur.grid){
				for(int j:i) System.out.printf("%2d",j);
				System.out.println();
			}

			if(cur.d == 3)break;
			if(valid(cur.bb)) {
				out = cur.d;
				break;
			}
			//s1, s2, s3, NESW
			boolean[][] possible = new boolean[4][4];
			for(boolean[] i:possible) Arrays.fill(i, true);
			for(int i=0;i<S;i++) {
				for(int j=0;j<S;j++){
					if(cur.grid[i][j]==0) continue;
					//cases NESW for edges
					if(i==0) possible[cur.grid[i][j]][0]=false;
					if(j==S-1) possible[cur.grid[i][j]][1]=false;
					if(i==S-1) possible[cur.grid[i][j]][2]=false;
					if(j==0) possible[cur.grid[i][j]][3]=false;
					//cases NESW part collision
					if(cur.grid[i-1][j]!=0&&cur.grid[i-1][j]!=cur.grid[i][j]) possible[cur.grid[i][j]][0]=false;
					if(cur.grid[i][j+1]!=0&&cur.grid[i][j+1]!=cur.grid[i][j]) possible[cur.grid[i][j]][1]=false;
					if(cur.grid[i+1][j]!=0&&cur.grid[i+1][j]!=cur.grid[i][j]) possible[cur.grid[i][j]][2]=false;
					if(cur.grid[i][j-1]!=0&&cur.grid[i][j-1]!=cur.grid[i][j]) possible[cur.grid[i][j]][3]=false;
				}
			}
			for(int i=1;i<=3;i++){
				for(int j=0;j<4;j++){
					if(!possible[i][j]) continue;
					//debug
					System.out.println("moving: " + i + " " + j);
					State next = move(cur,i,j);
					for(int[][][] s:visited) 
						if (Arrays.equals(s, next.bb)) continue;
					System.out.println("success");
					visited.add(next.bb);
					tp.add(next);
				}	
			}
		}


		fout.close(); 
	}

	public static boolean valid (int[][][] in) {
		for(int i=1;i<3;i++){
			for(int j=i+1;j<=3;j++) {
				if((in[j][0][0]<=in[i][1][0]&&in[i][1][0]<=in[j][1][0] ||
					in[j][0][0]<=in[i][0][0]&&in[i][0][0]<=in[j][1][0]) && 
					(in[j][0][1]<=in[i][1][1]&&in[i][1][1]<=in[j][1][1] ||
					in[j][0][1]<=in[i][0][1]&&in[i][0][1]<=in[j][1][1]))
					return false;
			}
		}
		return true;
	}
	public static State move(State in, int s, int dir) {
		//System.out.printf("%d, %d%n",s,dir);
		int[][] nextgrid = new int[S][S];
		int[][][] nextbb = new int[4][2][2];
		for(int i=0;i<S;i++){
			for(int j=0;j<S;j++){
				/*
				//debug
				for(int[] a: in.grid){
					for(int b:a) System.out.printf("%2d",b);
					System.out.println();
				}
				System.out.println();
				for(int[] a: nextgrid){
					for(int b:a) System.out.printf("%2d",b);
					System.out.println();
				}
				System.out.println();
				System.out.println();
				*/
				if(in.grid[i][j]!=s&&in.grid[i][j]!=0)nextgrid[i][j]=in.grid[i][j];
				//NESW
				if(in.grid[i][j]==s){
					if(dir==0) {
						nextgrid[i-1][j]=in.grid[i][j];
					}else if(dir==1) {
						nextgrid[i][j+1]=in.grid[i][j];
					}else if(dir==2) {
						nextgrid[i+1][j]=in.grid[i][j];
					}else if(dir==3) {
						nextgrid[i][j-1]=in.grid[i][j];
					}
				}
			}
		}
		for(int i=0;i<4;i++){
			for(int j=0;j<2;j++){
				for(int k=0;k<2;k++){
					nextbb[i][j][k]=in.bb[i][j][k];
				}
			}
		}
		if(dir==0){
			nextbb[s][0][0]--;
			nextbb[s][1][0]--;
		}else if(dir==1){
			nextbb[s][0][1]++;
			nextbb[s][1][1]++;
		}else if(dir==2){
			nextbb[s][0][0]++;
			nextbb[s][1][0]++;
		}else if(dir==3){
			nextbb[s][0][1]--;
			nextbb[s][1][1]--;
		}
		return new State(nextgrid,nextbb,in.d+1);
	}
}

class State {
	public int[][] grid;
	public int[][][] bb;
	public int d; 
	public State(int[][] a, int[][][] b, int c) {
		this.grid=a;
		this.bb=b;
		this.d=c;
	}
}
