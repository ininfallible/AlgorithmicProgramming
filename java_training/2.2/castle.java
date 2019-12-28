/*
ID: derekl3
LANG: JAVA
TASK: castle
 */


import java.util.*;
import java.io.*;

public class castle {
	static int[][] castle;
	static int R;
	
	static int floodfill (int cur) {
		//System.out.println("checking " + cur);
		castle[cur][4] = R;
		int total = 1;
		for (int i = 0; i < 4; i++) { //next
			if (castle[cur][i] != -1 && castle[castle[cur][i]][4] == -1) {
				total += floodfill(castle[cur][i]); //walls && checkedAlready
			}
		}
		return total;
	}
		
	public static void main(String[] args) throws IOException {
		int W; // W/H
		int H;
		BufferedReader fin = new BufferedReader(new FileReader("castle.in"));
		PrintWriter fout = new PrintWriter(new BufferedWriter(new FileWriter("castle.out")));
		StringTokenizer sT = new StringTokenizer(fin.readLine());
		W = Integer.parseInt(sT.nextToken());
		H = Integer.parseInt(sT.nextToken());
		
		//create edge/vertices graph
		castle = new int[W*H][];				//first index is vertex num, array is neighbors 
		int num = 0; 							//which tile?
		int[] add; 				//directions with neighbors
		for (int row = 0; row < H; row ++) {	//iterates through rows
			sT = new StringTokenizer(fin.readLine());//next row
			while (sT.hasMoreTokens()) {		//processes each tile in row
				int cur = Integer.parseInt(sT.nextToken()); //cur is value of tile
				add = new int[] {num - 1, num - W, num + 1, num + W, -1}; //-1 acts as no neighbor, WNES, which room (-1 means none yet)
				if (cur >= 8) {cur -= 8; add[3] = -1;} //south	
				if (cur >= 4) {cur -= 4; add[2] = -1;} //east
				if (cur >= 2) {cur -= 2; add[1] = -1;} //north
				if (cur >= 1) {cur -= 1; add[0] = -1;} //west
				castle[num] = add;
				num++;
			}
		}
		fin.close();
		
		int temp = 0; //debug 
		for (int[] i : castle) {
			System.out.print(temp + ": ");
			for(int j : i) System.out.print(j + " ");
			System.out.println(); 
			temp++;
		}
		
		//floodfill
		R = 0; //static, room number
		int maxRoom = 0; //max room size
		int[] rooms = new int[W*H + 1];
		for (int T = 0; T < castle.length; T++) {
			System.out.println(castle.length);
			System.out.println(T);	
			if (castle[T][4] == -1) {
				R++; 
				int temp2 = floodfill(T);
				rooms[R] = temp2;
				maxRoom = Math.max(maxRoom, temp2);
			}
		}
		System.out.println("P2");

		//find adjacent rooms
		int[][] whichWall = new int[6254][]; // row, col, dir, room1, room2, total; dir is WNES starting from 0;
		int count = 0;
		for (int col = 0; col < W; col++) {
			for(int row = H-1; row >= 0; row--) {
				
				//try north
				add = new int[6];
				int[] cur = castle[row*W + col]; //converts rowCol to index

				if(row > 0 && cur[4] != castle[(row-1) * W + col][4]) { 
					add[0] = row;
					add[1] = col;
					add[2] = 1;
					add[3] = cur[4]; //this room
					add[4] = castle[(row-1) * W + col][4]; //other room
					add[5] = rooms[cur[4]] + rooms[castle[(row-1) * W + col][4]];
					whichWall[count] = add;
					count++;	
				}
				
				//try west
				add = new int[6];
				cur = castle[row * W + col]; //converts rowCol to index

				if(col < W - 1 && cur[4] != castle[row * W + col + 1][4]) { 
					add[0] = row;
					add[1] = col;
					add[2] = 2;
					add[3] = cur[4]; //this room
					add[4] = castle[row * W + col + 1][4]; //other room
					add[5] = rooms[cur[4]] + rooms[castle[row * W + col + 1][4]];
					whichWall[count] = add;
					count++;	
				}
			}
		}
		
		int max2 = 0;
		for (int i = 0; i < count; i++) {
			if (Math.max(whichWall[max2][5], whichWall[i][5]) > whichWall[max2][5]) max2 = i;
		}
		
		System.out.println("P3");
		
		fout.println(R);
		fout.println(maxRoom);
		fout.println(whichWall[max2][5]);
		whichWall[max2][0]++;
		whichWall[max2][1]++;
		fout.print(whichWall[max2][0] + " " + whichWall[max2][1] + " ");
		if (whichWall[max2][2] == 1) fout.println("N");
		if (whichWall[max2][2] == 2) fout.println("E");
		fout.close();
		
		
		/*
		//System.out.println("rooms " + R);
		//System.out.println("max " + maxRoom);
		
		temp = 0;
		for (int[] i : whichWall) {
			if (temp == count) {
				break;
			}
			System.out.println(i[3] + " " + i[4] + " " + i[5]);
			temp++;
		}
		*/
		

		
		/*
		for (int[] N : whichWall) {
			if (N[3] == cur[4] && N[4] == castle[row * W + col + 1][4]) { 
				already = true;
			}
		}
		*/
	}

}
