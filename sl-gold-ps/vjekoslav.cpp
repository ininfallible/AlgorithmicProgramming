//2009-2010 Croatian Computing olympiad
//http://hsin.hr/coci/archive/2009_2010/contest2_tasks.pdf

#include <iostream>
#include <fstream>
#include <cmath>
#include <queue>

using namespace std;

queue <pair<int, int>> tP;
queue <pair<int, int>> cur_pos;
queue <pair<int, int>> next_pos;

int field[1100][1100];
bool visited[1100][1100];
bool visited2[1100][1100];
int startr, startc, endr, endc;
int r, c;
int dr[4] = {0, 0, 1, -1};
int dc[4] = {1, -1, 0, 0};
int ans;


void read_input() {
	int i, j;
	//read input
	ifstream fin("vjekoslav.in");
	//probably won't be used
	ofstream fout("vjekoslav.out");
	fin >> r >> c;
	cout << r << c << endl;

	for (i=0;i<r;i++)
		for (j=0;j<c;j++) 
		{
			char cur;
			fin >> cur;
			if (cur == 'V') 
			{
				startr = i;
				startc = j;
				cur_pos.push(pair<int, int>(i, j));
			}
			if (cur == 'J') 
			{
				endr = i;
				endc = j;
			}
			if (cur == '+')
				tP.push(pair<int, int>(i, j));
		}
}

int expand() {
	int i;
	while (!cur_pos.empty()) {
		pair<int, int> cur = cur_pos.front();
		cur_pos.pop();

		//if (visited2[cur.first][cur.second]) continue;

		visited2[cur.first][cur.second] = true;
		for (i=0;i<4;i++)
		{

			pair<int, int> next = pair<int, int>(cur.first+dr[i], cur.second+dc[i]);
			//debug
			//cout << ans << " vs. " << field[next.first][next.second]<< endl;
			if (next.first>=r||next.first<0||next.second>=c||next.second<0||
				visited2[next.first][next.second]||field[next.first][next.second]<ans) 
			{
				continue;
			}
			visited2[next.first][next.second]=true;
			cur_pos.push(next);
			next_pos.push(next);
		}
	}
	return 0;
}

void bfs_sol() {
	int i;
	while (!tP.empty()) {
		pair<int, int> cur = tP.front();
		tP.pop();

		visited[cur.first][cur.second] = true;

		for (i=0;i<4;i++)
		{
			pair<int, int> next = pair<int, int>(cur.first+dr[i], cur.second+dc[i]);
			if (next.first>=r||next.first<0||next.second>=c||next.second<0||
				visited[next.first][next.second])
			{
				continue;
			}
			visited[next.first][next.second]=true;
			field[next.first][next.second] = field[cur.first][cur.second]+1;
			tP.push(next);
		}
	}

	ans = field[startr][startc];
	while (!visited2[endr][endc] && ans > -2) {
		cur_pos.push(pair<int, int>(startr, startc));
		ans--;
		expand();
		cur_pos.swap(next_pos);
		queue<pair<int, int>>().swap(next_pos);
	}
}


int main() {
	int i, j;
	read_input();
	bfs_sol();

	cout << endr << " " << endc << endl;
	//debug
	for (i=0;i<r;i++) 
	{
		for (j=0;j<c;j++)
			cout << field[i][j] << ' ';
		cout << endl;
	}
	cout << ans << endl;

	return 0;
}
