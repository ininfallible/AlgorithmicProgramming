//2015-16 Gold P1

#include <iostream>
#include <fstream>
#include <algorithm>

using namespace std;

const int MAXN=50001;
int e1[MAXN];
int e2[MAXN];
int b[MAXN];
bool tmp[MAXN*2];
int N;
int main()
{
	int i,j,k;
	ifstream fin("cardgame.in");
	ofstream fout("cardgame.out");
	fin >> N;
	for(i=0;i<N/2;i++)
		fin >> e1[i];
	for(i=0;i<N/2;i++)
		fin >> e2[i];
	for (i=0;i<N/2;i++)
	{
		tmp[e1[i]]=true;
		tmp[e2[i]]=true;
	}
	j=0;
	for (i=1;i<=N*2;i++)
	if (!tmp[i]) 
	{
		b[j]=i;
		j++;
	}
	sort(e1,e1+N/2);
	sort(e2,e2+N/2);
	sort(b,b+N);
	int cb=0,ce=0,ans=0;
	while (cb<N/2&&ce<N/2)
	{
		//cout << b[cb+N/2] << " vs. " << e1[ce] << endl;
		if (b[cb+N/2]>e1[ce])
		{
			ce++;
			ans++;
		}
		cb++;
	}
	cb=N/2-1; ce=N/2-1;
	//cout << "second half" << endl;
	while (cb>=0&&ce>=0)
	{
		//cout << b[cb] << " vs. " << e2[ce] << endl;
		if(b[cb]<e2[ce])
		{
			ce--;
			ans++;
		}
		cb--;
	}
	/*
	for (i=0;i<N/2;i++)
		cout << e1[i] << " ";
	cout << endl;
	for (i=0;i<N/2;i++)
		cout << e2[i] << " ";
	cout << endl;
	for (i=0;i<N;i++)
		cout << b[i] << " ";
	cout << endl;
	cout << ans << endl;
	*/
	fout << ans << endl;
	return 0;
}
