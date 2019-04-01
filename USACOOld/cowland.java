//2019 February USACO Gold P1

import java.io.*;
import java.util.*;

class Node {
	int index, parent, fun;
	public Node(int A, int B, int C) {
		index = A;
		parent = B;
		fun = C;
	}
}
public class cowland {
	public static void main(String[] args) throws IOException {
		BufferedReader fin = new BufferedReader(new FileReader("cowland.in"));
		PrintWriter fout = new PrintWriter(new BufferedWriter(new FileWriter("cowland.out")));
		StringTokenizer sT = new StringTokenizer(fin.readLine());
		int A = Integer.parseInt(sT.nextToken());
		int Q = Integer.parseInt(sT.nextToken());
		
		int[][] funVals = new int[A][A];
		for (int[] i : funVals) Arrays.fill(i, -1);
		int[] attr = new int[A];
		sT = new StringTokenizer(fin.readLine());
		for (int i = 0; i < A; i++) {
 			int temp = Integer.parseInt(sT.nextToken());
			attr[i] = temp;
			funVals[i][i] = temp;
		}

		for (int i = 0; i < A-1; i++) {
			sT = new StringTokenizer(fin.readLine());
			int a = Integer.parseInt(sT.nextToken())-1;
			int b = Integer.parseInt(sT.nextToken())-1;
			funVals[b][a] = funVals[a][b] = attr[a]^attr[b];
		}
		//fill out funVals
		for (int i = 0; i < 3; i++) {
			LinkedList<Node> tP = new LinkedList<>();
			tP.add(new Node(i, i, attr[i]));
			boolean[] visited = new boolean[A];
			visited[i] = true;

			while (!tP.isEmpty()) {
				Node cur = tP.pop();
				//visited[cur.index] = true;
				int curFun = cur.fun ^ funVals[cur.parent][cur.index] ^ attr[cur.parent];
				//System.out.println(cur.fun + " " + funVals[cur.parent][cur.index] + " " + attr[cur.parent]);
				//System.out.println(curFun);
				funVals[cur.index][i] = funVals[i][cur.index] = curFun;

				for (int j = 0; j < A; j++) {
					if (funVals[cur.index][j] != -1 && !visited[j]) {
						visited[j] = true;
						tP.add(new Node(j, cur.index, curFun));
					}
				}
			}
		}

		//debug
		/*
		for (int[] i: funVals) {
			for (int j: i) System.out.printf("%3d ", j);
			System.out.println();
		}
		*/
		for (int i = 0; i < Q; i++) {
			sT = new StringTokenizer(fin.readLine());
			if (sT.nextToken().equals("1")) {
				int change = Integer.parseInt(sT.nextToken())-1; 
				int oldFun = funVals[change][change];
				int newFun = Integer.parseInt(sT.nextToken());
				for (int j = 0; j < A; j++) {
					funVals[change][j] = funVals[j][change] = funVals[j][change]^oldFun^newFun;
				}
			} else {
				fout.println(funVals[Integer.parseInt(sT.nextToken())-1][Integer.parseInt(sT.nextToken())-1]);
			}
		}
		fin.close();
		fout.close();
	}
}

