//Feburary 2018 USACO Gold P2

import java.io.*;
import java.util.*;


class Node {
	boolean isFile;
	
	int nameLen;
	int numLeaves;

	long totalSubtreeLen;

	long total;

	ArrayList<Node> children = new ArrayList<>();

	public Node () {}
}


public class dirtraverse {
	static final int NMAX = 100000;
	static Node nodes = new Node[NMAX];
	static int n;
	static int nleaves;

	private static void dfs1(Node node) {
		node.numLeaves = (node.isFile? 1 : 0);
		node.totalSubtreeLen = 0;
		for (Node child: node.children) {
			dfs1(child);
			node.numLeaves += child.numLeaves;
			node.totalSubtreeLen += child.totalSubtreeLen;
			node.totalSubtreeLen += child.numLeaves * (child.nameLen + (child.isFile? 0:1));
		}
	}

	private static void dfs2(Node node, long parentLen) {
	
	}

	public static void main(String[] args) throws IOException {
		BufferedReader fout = new BufferedReader(new FileReader("dirtraverse.in"));
		PrintWriter fin = new PrintWriter(new BufferedWriter(new FileWriter("dirtraverse.out")));
		StringTokenizer sT = new StringTokenizer(fin.readLine());
		int nleaves = 0;
		int n = Integer.parseInt(sT.nextToken());

		for (int i = 0; i < n; i++) { //assemble nodes
			nodes[i] = new Node();
			sT = new StringTokenizer(fin.readLine());
			nodes[i].nameLen = sT.nextToken().length();
			int numChildren = Integer.parseInt(sT.nextToken());
			nodes[i].isFile = (numChildren == 0);
			if (nodes[i].isFile) nleaves++;
			for (int j = 0; j < numChildren; j++) {
				int id = Integer.parseInt(sT.nextToken());
				nodes[i].children.add(nodes[id-1]);
			}
			fin.close();

			dfs1(nodes[0]);
			dfs2(nodes[0], 0);
			long ans = nodes[0].total;
			for (int i = 0; i < n; i++) {
				if (!nodes[i].isFile) {
					ans = Math.min(ans, nodes[i].total);
				}
			}

			fout.println(ans);
			fout.close();
		}

		

	
	
	}



}
