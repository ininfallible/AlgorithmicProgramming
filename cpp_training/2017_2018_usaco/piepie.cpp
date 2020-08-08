// 2017 Dec Gold P1
// Implementing sol - bin search with multiset ._. and use of iterators
#include <bits/stdc++.h>
using namespace std;

#define pb push_back
#define mp make_pair
#define fi first
#define se second
typedef vector<int> vi;
typedef pair<int, int> pii;
typedef vector<pair<int, int>> vpii;
typedef long long ll;
const int INF = 1<<20;
const int MAXN = 1e5;

int N, D;
int B[2*MAXN];
int E[2*MAXN];
int dist[2*MAXN];

// Start from 0 value pie, other value results in set of possible
// pies other cow could have given
// eg. (0, N) -> (M, N-a) , where A<=D, etc.
// sort Elsie's pies by Bessie's value of them
struct cmpE
{
	bool operator()(int a, int b) const
	{
		return B[a]<B[b];
	}
};
// sort Bessie's pies by Elsie's value of them
struct cmpB
{
	bool operator()(int a, int b) const
	{
		return E[a]<E[b];
	}
};

multiset <int, cmpB> setB;
multiset <int, cmpE> setE;

int que[2*MAXN];
int cur, len; //len represents length of que

int main()
{
	int i,j,k;
	ifstream fin("piepie.in");
	ofstream fout("piepie.out");
	fin >> N >> D;
	for(i=0;i<2*N;i++)
	{
		fin >> B[i] >> E[i];
		//negative so multisets are sorted by descending value
		B[i]=-B[i], E[i]=-E[i]; 
		dist[i]=-1;
	}
	//puts all pies in either setB, setE, or in queue (if value of opp. is 0)
	for(i=0;i<N;i++)
	{
		if(E[i]==0)
			que[len++] = i, dist[i] = 1;
		else
			setB.insert(i);
		if(B[N+i]==0)
			que[len++] = i, dist[N+i] = 1;
		else
			setE.insert(N+i);
	}
	multiset<int,cmpB>::iterator itB;
	multiset<int,cmpE>::iterator itE;
	// BFS
	while (cur<len)
	{
		int i = que[cur];
		if (i<N)
			while(1)
			{
				itE = setE.lower_bound(i); // find the index corresponding to value
				// if opposing value corresponding to i is too small (big bc negative), break
				// eg. if B perceives value to be N, E can give a pie with Bvalue of between N-D and N
				if (itE == setE.end() || B[*itE] > B[i]+D) break;
				dist[*itB] = dist[i] + 1;
				que[len++] = *itB; // add index to queue
				setE.erase(itB); // visits node
			}
		else 
			while(1)
			{
				itB = setB.lower_bound(i); // find the index corresponding to value
				if (itB == setB.end() || E[*itB] > B[i]+D) break;
				dist[*itB] = dist[i] + 1;
				que[len++] = *itB; // add index to queue
				setE.erase(itB); // visits node
			}
		cur++;
	}
	for (i=0;i<N;i++)
		fout << dist[i] << '\n';
	return 0;
}

