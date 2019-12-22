// 2019 December USACO gold P1
#include <iostream>
#include <fstream>
#include <cmath>
#include <vector>
#include <queue>
#include <map>
#define INF 1000000

using namespace std;

//N joints, M edges
int N, M;
map<int, int> FC[1000];
vector<vector<int>> edges[1000];

double c_r(int f, int c) 
{
	return (double)(f)/(double)(c);
}

int main () 
{
	int i, j, k;
	ifstream cin("pump.in");
	ofstream cout("pump.out");
	cin >> N >> M;
	for (i=1;i<=M;i++)
	{
		int a, b, c, f;
		cin >> a >> b >> c >> f;
		edges[a].push_back(vector<int>{b, f, c});
		edges[b].push_back(vector<int>{a, f, c});
	}

	//bfs
	queue<int> tP;
	tP.push(1);
	FC[1].insert(pair<int, int>(INF, 0));
	while (!tP.empty()) 
	{
		int ind = tP.front();
		tP.pop();
		for (auto next : edges[ind]) 
		{
			bool add_next = false;
			for (auto curr: FC[ind]) 
			{
				int next_f = min(curr.first, next[1]);
				int next_c = curr.second + next[2];
				if (!FC[next[0]].count(next_f))
				{
					FC[next[0]].insert(pair<int, int>(next_f, next_c));
					add_next = true;
				} 
				else if (next_c<curr.second) 
				{
					FC[next[0]].insert(pair<int, int>(next_f, next_c));
					add_next = true;
					continue;
				}
			}
			if (add_next) 
			{
				tP.push(next[0]);
			}
		}
	}
	
	int out = 0;
	for (pair<int, int> j: FC[N])
	{
		//cout << j.first << ' ' << j.second << endl;
		out = max(out, (int)(1000000*((double)j.first/(double)j.second)));
		continue;
	}
	cout << out << endl;

	return 0;
}

