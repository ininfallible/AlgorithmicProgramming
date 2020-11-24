//2020 US Open Gold P3
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

const int MAXN = 1e4+1;
// a vector of prime numbers under MAXN
vi primes;
bool composite[MAXN];

// prime, len -> sum of ks corresponding?
ll dp[1500][MAXN];

// N is no. of cows
int N, M;

int main()
{
	int i,j,k;
	ifstream fin ("exercise.in");
	ofstream fout ("exercise.out");
	fin >> N >> M;

	//run sieve for a list of primes
	for (i=2;i<=N;i++)
		if (!composite[i])
		{
			for (j=i*2;j<=N;j+=i)
				composite[j] = true;
			primes.pb(i);
		}
	// debug
	// for (auto a: primes) cout << a << endl;
	
	// treat 1 as prime 0
	// period 1 is always possible
	for (i=0;i<=N;i++) dp[0][i] = 1;

	// prime -> length of string -> try all powers of prime
	for (i=1;i<=primes.size();i++)
	{
		for (int n=0;n<=N;n++)
		{
			int pp = primes[i-1];
			dp[i][n] = dp[i-1][n];
			while (pp <= n)
			{
				dp[i][n] += pp * dp[i-1][n-pp];
				dp[i][n] %= M;
				pp *= primes[i-1];
			}
		}
	}
	fout << dp[primes.size()][N]%M << endl;
	return 0;
}

