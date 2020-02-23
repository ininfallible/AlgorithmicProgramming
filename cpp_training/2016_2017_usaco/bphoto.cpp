//2017 January Gold P1

#include <iostream>
#include <fstream>
#include <map>

using namespace std;

const int MAXN=100001;

int N;
int TS;
int tree[MAXN];

int sum(int k)
{
	int out = 0;
	while (k>=1) 
	{
		out += tree[k];
		k -= k&-k;
	}
	return out;
}

void add(int k, int n)
{
	while (k <= TS)
	{
		tree[k] += n;
		k += k&-k;
	}
	return;
}

int main()
{
	int i,j,k;
	//p&-p
	ifstream fin("bphoto.in");
	ofstream fout("bphoto.out");
	fin >> N;
	TS = N;
	map<int,int> cows;
	for (i=1;i<=N;i++) 
	{
		fin >> j;
		cows.insert(make_pair(-j, i));
	}
	int ret = 0;
	k=0;
	for (auto a: cows)
	{
		//query tree
		int cf = sum(a.second);
		if ((k-cf)>2*cf||cf>2*(k-cf)) ret++;
		//update tree
		add(a.second, 1);
		k++;
	}
	//cout << ret << endl;
	fout << ret << endl;
	return 0;
}
