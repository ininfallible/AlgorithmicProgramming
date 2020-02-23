//2016 December Gold P3
#include <iostream>
#include <fstream>
#include <algorithm>
#include <map>
#include <vector>
#include <set>
#include <queue>

using namespace std;

int N, xl, yl, xb, yb;
const int MAXN = 10000;

struct line 
{
	line(int p, bool i, int d):pos(p),ish(i),dist(d){}
	int pos;
	bool ish;
	int dist;	
};

int main()
{
	int i,j,k;
	ifstream fin("lasers.in");
	ofstream fout("lasers.out");

	fin >> N >> xl >> yl >> xb >> yb;
	int H=0, V=0;
	set<int> uh, uv;
	map<int, vector<int>> vh, hv;
	vh[xl].push_back(yl);
	hv[yl].push_back(xl);
	for (i=0;i<N;i++)
	{
		fin >> j >> k;
		//if (vh.count(j)==0) vh.insert(make_pair(k,vector<int>()));
		//if (hv.count(k)==0) hv.insert(make_pair(j,vector<int>()));
		vh[j].push_back(k);
		hv[k].push_back(j);
	}
	queue<line> Q;
	Q.push(line(xl,false,0));
	uv.insert(xl);
	Q.push(line(yl,true,0));
	uh.insert(yl);
	int out = 1<<20;
	while (!Q.empty())
	{
		line cur = Q.front(); Q.pop();
		if ((cur.ish&&cur.pos==yb)||(!cur.ish&&cur.pos==xb)) 
		{
			out = min(out,cur.dist);
			break;
		}
		if (cur.ish)
		{
			for(i=0;i<hv[cur.pos].size();i++)
			{
				int nxt = hv[cur.pos][i];
				if (uv.count(nxt)==0)
				{
					Q.push(line(nxt,!cur.ish,cur.dist+1));
					uv.insert(nxt);
				}

			}
		} else {
			for(i=0;i<vh[cur.pos].size();i++)
			{
				int nxt = vh[cur.pos][i];
				if (uh.count(nxt)==0)
				{
					Q.push(line(nxt,!cur.ish,cur.dist+1));
					uh.insert(nxt);
				}

			}
		}
	}
	if (out==1<<20) out = -1;
	fout << out << endl;
	return 0;
}
