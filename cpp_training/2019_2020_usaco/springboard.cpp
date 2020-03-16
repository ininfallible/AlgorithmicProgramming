//2020 Jan Gold P3
/*
 *	"->" used to assign the value of a pointer to a variable
 */
#include <iostream>
#include <fstream>
#include <map>
#include <vector>
#include <algorithm>

using namespace std;

#define f first 
#define s second
typedef pair<int, int> pii;

int ans[(int)1e5+5];
int N, P;
map <int, int > m; 

void ins(int y,int v)
{
	auto it = prev(m.upper_bound(y));
	if (it->s <= v) return;
	it ++;
	while (it != end(m) && it->s>v) m.erase(it++);
	m[y]=v;
}
int main()
{
	int i,j,k;
	ifstream fin("springboard.in");
	ofstream fout("springboard.out");
	fin >> N >> P;
	m[0]=0;
	
	vector<pair<pii, pii>> pts;
	for (i=0;i<P;i++)
	{
		pii a,b;
		fin >> a.f >> a.s >> b.f >> b.s;
		pts.push_back({a,{i, -1}}); // start point
		pts.push_back({b,{i, 1}}); // end point
	}
	sort(begin(pts),end(pts));
	for (auto& t: pts)
	{
		//t.s.s represents ans[]
		if (t.s.s==-1)
		{
			//ans[springboard] = start point + min(ans[j]-x2(j)-y2(j))
			//upperbound of s means max y such that y_k < y_i
			//we can do this b/c as y_k increases m[y_k] decreases
			//b/c more springboards available
			ans[t.s.f] = t.f.f+t.f.s+prev(m.upper_bound(t.f.s))->s;
		} 
		else
		{
			ins(t.f.s,ans[t.s.f]-t.f.f-t.f.s);
		}
	}
	cout << m.rbegin()->s+2*N;
	return 0;
}
