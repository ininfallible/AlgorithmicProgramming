//2020 Feb Gold P3
#include <iostream>
#include <fstream>
#include "bits/stdc++.h"

using namespace std;

const int MAXN = 1e5+1;

int N;
int sub[MAXN];
vector <int> adj[MAXN], num[MAXN];

void dfs (int par, int cur) 
{
	sub[cur]=1;
	for (auto& a: adj[cur])
	if (a != par)
	{
		dfs(cur, a);
		sub[cur]+=sub[a];
		//represents numbers returned by subtrees
		num[cur].push_back(sub[a]);
	}
	//represents the rest of tree (going up)
	if (sub[cur]!=N) num[cur].push_back(N-sub[cur]);
}

int cur[MAXN];
bool check(int K) 
{
	if ((N-1)%K!=0) return 0;
	for (int i=0;i<K;i++) cur[i]=0;
	for (int i=1;i<=N;i++) {
		int c=0;
		for (auto& a : num[i])
		{
			int r = a%K; if (r==0) continue;
			if (cur[K-r]) cur[K-r]--, c++;
			else cur[r]++, c++;
		}
		if (c) return 0;
	}
	return 1;
}

int main ()
{
	int i,j,k;
	ifstream fin("deleg.in");
	ofstream fout("deleg.out");
	fin >> N;
	for (i=0;i<N-1;i++) 
	{
		fin >> j >> k;
		adj[j].push_back(k), adj[k].push_back(j);
	}
	//indexing from 1 so par 0 works
	dfs(1, 0);
	for (i=1;i<N;i++)
	{
		if (check(i)) fout << 1;
		else cout << 0;
	}
	cout << endl;
	return 0;
}
