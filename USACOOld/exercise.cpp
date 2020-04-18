//placeholder
#include <bits/stdc++.h>
using namespace std;

#define fi first
#define se second
typedef vector<int> vi;
typedef pair<int, int> pii;
typedef long long ll;
const int INF = 1<<20;

const int MAXN=10001;
int N, M;
vector<bool> siv(MAXN);
vector<int> p;
int dp[MAXN][MAXN];
int main()
{
	int i,j,k;
	ifstream fin("exercise.in");
	ofstream fout("exercise.out");
	fin >> N >> M;
	//run sieve
	fill(siv.begin(),siv.end(),true);
	siv[0]=siv[1]=false;
	for (i=2;i<=N;i++)
	{
		if (siv[i])
		{
			p.push_back(i);
			for(j=i*i;j<=N;j+=i)
				siv[j]=false;
		}
	}
	p.insert(p.begin(), 1);
	for (int d: p) cout << d << " " << endl;
	cout << endl;

	//dp
	//memset(dp, 0xc0, sizeof(dp));
	for (i=0;i<p.size();i++) dp[p[i]][i]=p[i];
	for (i=1;i<=N;i++) dp[i][0]=1;
	//i= cur size, j = max prime used so far, s = next prime
	ll ans=0;
	for (i=1;i<=N;i++)
	{
		for (j=0;j<p.size();j++)
		for (int s=0;s+j<p.size()&&i+p[j+s]<=N;s++)
		{
			//if (dp[i][j]<0) continue;
			if (s!=0)
				dp[i+p[j+s]][j+s]+=dp[i][j]*(p[j+s]);
			else if(j!=0)
				dp[i+p[j]][j]+=dp[i][j]+(dp[i][j]/p[j])*((dp[i][j]/p[j]));
			dp[i+p[j+s]][j+s]%=M;
		}

		for (int a=0;a<=N;a++)
		{
			for (int b=0;b<p.size();b++) cout << ((dp[a][b]<0)?-1:dp[a][b]) << " ";
			cout << '\n';
		}
		cout << '\n';
	}	
	cout << ans << endl;
	fout << ans << endl;
	return 0;
}

