//2019 Dec Silver P1
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

int N;
long intoN(long in)
{
	return in - in/3 - in/5 + in/15;
}
long bs(long lo, long hi)
{
	while (lo < hi)
	{
		cout << lo << hi << endl;
		long mid = (lo + hi)/2;
		if (intoN(mid) < N)
			lo = mid+1;
		else
			hi = mid;
	}
	return lo;
}

int main()
{
	//cout << intoN(7) << endl;
	ifstream fin("moobuzz.in");
	ofstream fout("moobuzz.out");
	fin >> N;
	fout << bs(0l, 5e10l);
	return 0;
}

