//2014 March Silver P3
#include <iostream>
#include <fstream>
#include <cmath>
#define INF 1000000

using namespace std;
int M[100001];
int n[101];
int nP[101];
int b[21];
int N, B;
int ans=0;

void F(int vol) {
	int i;
	for (i=0;i<B;i++) 
	{
		if (b[i]==0) continue;
		if (vol-b[i]>=0&&M[vol-b[i]]!=INF) 
		{
			M[vol] = min(M[vol-b[i]]+1, M[vol]);
		}
	}
}


int main() 
{
	int i, j, k, l;
	ifstream cin("mooomoo.in");
	cin >> N >> B;
	for (j=0;j<B;j++)
	{
		cin >> b[j];
	}
	for (i=0;i<N;i++)
	{
		cin >> n[i];
	}

	fill(M, M+100001, INF);
	M[0]=0;
	for (k=0;k<100001;k++)
	{
		F(k);
	}
	
	for (l=0;l<N;l++)
	{
		if (l==0)
			nP[l] = n[l];
		else 
			nP[l] = n[l]-max(n[l-1]-1, 0);
		ans += M[nP[l]];
	}


#ifndef LOCAL
	//ifstream cin("mooomoo.in");
	ofstream cout("mooomoo.out");
#endif
	/*
	for (i=0;i<20;i++)
		cout << M[i] << " ";
	cout << endl;
	for (i=0;i<N;i++)
		cout << nP[i] << " ";
	cout << endl;
	*/
	cout << ans << endl;
	return 0;
}

