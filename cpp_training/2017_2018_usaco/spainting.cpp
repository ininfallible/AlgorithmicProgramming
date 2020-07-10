//2018 Jan Gold P3
//implenting sol from website
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
const int MAXN = 1e6+1;
const int MOD = 1000000007;

int N, M, K;
int s[MAXN]; //s[n] = sum of dp(n)
int main()
{
	ifstream fin("spainting.in");
	ofstream fout("spainting.out");
	int i,j,k;
	int N,M,K;
	fin >> N >> M >> K;
	
	s[0]=0;
	for (i=1;i<K;i++)
		s[i] = M*((ll)s[i-1]+M) % MOD;
	for (i=K;i<=N;i++)
		s[i] = (M*(ll)s[i-1]) + MOD - ((M-1)*((ll)s[i-K])%MOD)%MOD;
	
	int ans = 1;
	for (i=1;i<=N;i++)
		ans = (M*((ll)ans))%MOD;
	cout << (((ll) ans ) + MOD - ((ll)s[N]) + s[N-1]) % MOD << '\n';
	
	return 0;
}

