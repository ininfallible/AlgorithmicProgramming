//2016 Feb Plat P2
//implementing sol from website
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

const int MAXN = 25001;
int A, B, N, M;
int vg[MAXN], hg[MAXN];
int vf[MAXN], hf[MAXN];
int vuse = 0, huse = 0;
int vign = 0, hign = 0;
int main()
{
	int i,j,k;
	ifstream fin("fencedin.in");
	ofstream fout("fencedin.out");
	fin >> A >> B >> N >> M;

	for (i=1;i<=N;i++) fin >> hf[i];
	hf[N+1] = A;
	sort(hf, hf+N+2);
	for (i=1;i<=M;i++) fin >> vf[i];
	vf[M+1] = B;
	sort(vf, vf+M+2);

	//debug
	for (i=0;i<=N+1;i++) cout << hf[i] << " ";
	cout << endl;
	for (i=0;i<=M+1;i++) cout << vf[i] << " ";
	cout << endl;
	
	for (i=0;i<=N;i++) vg[i] = hf[i+1] - hf[i];
	for (i=0;i<=M;i++) hg[i] = vf[i+1] - vf[i];

	//debug
	sort(vg, vg+N+1);
	sort(hg, hg+M+1);
	for (i=0;i<=N;i++) cout << vg[i] << " ";
	cout << endl;
	for (i=0;i<=M;i++) cout << hg[i] << " ";
	cout << endl;

	ll ans = 1ll*vg[0]*(M-1) + 1ll*hg[0]*(N-1);
	for (vuse = 1, huse = 1; vuse < N && huse < M;)
	{
		if (vg[vuse]<hg[huse])
			ans += vg[vuse++] * (M - huse);
		else
			ans += hg[huse++] * (N - vuse);
	}
	cout << ans << endl;
	return 0;
}

