//2014 December Gold P2

#include <iostream>
#include <fstream>
#include <vector>

using namespace std;

int N, Q;

//distance between two nodes i, i+1;
int dt[100001];
//distance saved by skipping i
int sd[100001];
//coords
int loc[100001][2];

int d(int a, int b)
{
	return abs(loc[a][0]-loc[b][0])+abs(loc[a][1]-loc[b][1]);
}

int q(int a, int b)
{
	int i, ret=0, sav=0;
	for (i=a;i<b;i++)
		ret+=dt[i];
	for (i=a+1;i<b;i++)
		sav = max(sav, sd[i]);
	ret -= sav;
	return ret;
}
void u(int ind, int x, int y) 
{
	loc[ind][0]=x;loc[ind][1]=y;
	if (ind>1)
		dt[ind-1]=d(ind-1, ind);
	dt[ind]=d(ind-1, ind);
	sd[ind+1]=dt[ind]+dt[ind+1]-d(ind, ind+2);
	sd[ind-1]=dt[ind-2]+dt[ind-1]-d(ind-2, ind);
	sd[ind]  =dt[ind-1]+dt[ind]-d(ind-1, ind+1);
}

int main()
{
	int i, j, k;
	ifstream fin("marathon.in");
	ofstream fout("marathon.out");
	fin >> N >> Q;
	for (i=1;i<=N;i++)
		fin >> loc[i][0] >> loc[i][1];
	for (i=1;i<N;i++)
		dt[i] = d(i,i+1);
	for (i=2;i<N;i++)
		sd[i]=dt[i-1]+dt[i]-d(i-1,i+1);
	
	char c;
	for (i=0;i<Q;i++)
	{
		fin >> c;
		if (c=='Q') {
			int a, b;
			fin >> a >> b;
			fout << q(a, b) << endl;
		}
		if (c=='U') {
			int ind, x, y;
			fin >> ind >> x >> y;
			u(ind, x, y);
		}
	}
	

	return 0;
}
