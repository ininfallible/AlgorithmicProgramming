#include <iostream>
#include <fstream>

using namespace std;

const int MAXN = 5000;
int N, Q;
int main()
{
	int i,j,k;
	ifstream fin("threesum.in");
	ofstream fout("threesum.out");

	fin >> N >> Q;
	int A[MAXN];
	for (i=0;i<N;i++) fin >> A[i];

	//num[i][k] needs to be number of ans for i,k
	int ans[MAXN][MAXN];
	int map[2000001];
	//find num[i][j]
	for (i=N;i>=0;i--)
	{
		for (k=i+1;k<N;k++)
		{
			int ind = 1000000-A[i]-A[j];
			if (ind>=0&&ind<=2000000) ans[i][k] = map[ind];
			map[A[i]+1000000]++;
		}
		fo (k=i+1;k<N;k++)
		{
			map[A[i]+1000000]--;
		}
	}
	for (i=N-1;i>=0;i--)
	for (k=i+1;k<N;k++)
		ans[i][k] += ans[i+1][k]+ans[i][k-1]-ans[i+1][j-1];
	for (i=0;i<Q;i++)
	{
		fin >> j >> k;
		fout << ans[j-1][k-1] << endl;
	}
	

	return 0;
}
