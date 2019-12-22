// 2019 December USACO gold P1
#include <iostream>
#include <fstream>
#include <cmath>
#include <vector>
#include <queue>

using namespace std;

//N joints, M edges
int N, M;
int C[1000];
int F[1000];
bool fv[1000];
vector<vector<int>> edges[1000];

double calc_ratio(int f, int c) {
	return (double)(f)/(double)(c);
}
double calc_ratio(int ind) {
	return calc_ratio(F[ind], C[ind]);
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
	fv[1] = true;
	while (!tP.empty()) {
		int cur = tP.front();
		tP.pop();
		for (auto next : edges[cur]) {
			//travel to neighbors
			if (!fv[next[0]] ||
				calc_ratio(min(F[next[0]],next[1]),C[next[0]]+next[2])>calc_ratio(next[0])) 
			{
				if (!fv[next[0]]) {
					C[next[0]] = C[cur]+next.at(2);
					F[next[0]] = next.at(1);
					tP.push(next[0]);
				} else if(calc_ratio(min(F[cur],next[1]),C[cur]+next[2])>calc_ratio(next[0]))
				{
					C[next[0]] = C[cur]+next.at(2);
					F[next[0]] = min(F[cur], next.at(1));
					tP.push(next[0]);
				}
				fv[next[0]] = true;
			}

			continue;
		}
		/*
		for (i=1;i<=N;i++) {
			cout << F[i] << " " << C[i] << endl;
		}
		cout << endl;
		*/
	}
	
	cout << (int)(calc_ratio(N) * 1000000) << endl;

	return 0;
}

