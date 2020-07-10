//2018 Feb Gold P1
#include <bits/stdc++.h>
using namespace std;

#define pb push_back
#define mp make_pair
#define fi first
#define se second
typedef vector<int> vi;
typedef pair<int, int> pii;
typedef long long ll;
const int INF = 1<<20;
const int MAXN = 1e5+1;

int N, B;
pii t[MAXN];
pair<pii, int> b[MAXN];
int ans[MAXN];
int dln[MAXN], dlp[MAXN];

int remove_tile(int ind)
{
	dln[dlp[ind]] = dln[ind];
	dlp[dln[ind]] = dlp[ind];
	return dln[ind] - dlp[ind];
}


int main()
{
	ifstream fin("snowboots.in");
	ofstream fout("snowboots.out");
	int i,j,k;
	fin >> N >> B;
	for (i=0;i<N;i++) 
	{
		fin >> t[i].fi; // snow depth
		t[i].se = i; // index of tile
	}
	for (i=0;i<B;i++)
	{
		fin >> b[i].fi.fi; // maxdepth
		fin >> b[i].fi.se; // step size
		b[i].se = i; // index of boot
	}

	sort(t, t+N);
	sort(b, b+B);
	//doubly linked list
	for (i=0;i<N;i++)
	{
		dlp[i] = i-1;
		dln[i] = i+1;
	}
	//dlp[0] = 0;
	//dln[N-1] = N-1;
	
	//debug sort
	//for (i=0;i<B;i++) cout << b[i].fi.fi << b[i].fi.se << " ";
	//cout<< endl;
	//debug linkedlist

	//algo
	int maxgap = 0; //gap
	int maxdepth = N-1; //ind of max depth
	for (i=B-1;i>=0;i--) //for each boot
	{
		while (t[maxdepth].fi>b[i].fi.fi && maxdepth>-1)
		{
			maxgap = max(remove_tile(t[maxdepth].se), maxgap);
			maxdepth--;
		}
		ans[b[i].se] = maxgap <= b[i].fi.se;
		
		/*
		cout << "maxgap: " << maxgap << endl; //debug
		for(j=0;j<N;) 
		{
			cout << j << " ";
			j = dln[j];
		}
		cout << endl;
		for(j=N-1;j>=0;) 
		{
			cout << j << " ";
			j = dlp[j];
		}
		cout << endl;
		*/
	}

	for (i=0;i<B;i++) fout << ans[i] << '\n';

	return 0;
}

