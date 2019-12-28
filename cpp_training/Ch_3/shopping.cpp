/*
ID: derek.l3
PROB: shopping
LANG: C++14
*/

#include <iostream>
#include <fstream>
#include <vector>
#include <map>

using namespace std;

const int INF=1<<20;
int S, B;
map<int, int> offers[100];
int prices[100];
int t[5], n[5], bp[5];
//each int in sol represents min cost of that configuration
int sol[6][6][6][6][6];

int main()
{
	int i,j,k;
	int x, y;
	ifstream fin("shopping.in");
	ofstream fout("shopping.out");

	fin >> S;
	for (i=0;i<S;i++)
	{
		//construct array of options
		fin >> j;
		for (k=0;k<j;k++)
		{
			fin >> x >> y;
			offers[i][x]=y;
		}
		fin >> prices[i];
	}
	fin >> B;
	for (i=0;i<B;i++)
	{
		fin >> x >> y;
		t[i]=x;
		n[i]=y;
		fin >> bp[i];
	}
	
	/*
	//debug
	cout << "debugging offer 0:" << endl;
	for (auto it : offers[0])
	{
		cout << it.first << " " << it.second << endl;;
	}
	cout << "S offers: " << S << endl;
	cout << "B item types: " << B << endl;
	cout << "t|n" << endl;
	for (int a=0;a<5;a++)
		cout << t[a] << "|" << n[a] << endl;
	cout << "offer prices: " << endl;
	for (int a=0;a<S;a++)
		cout << prices[a] << endl;
	cout << "base prices: " << endl;
	for (int a=0;a<S;a++)
		cout << prices[a] << " ";
	cout << endl;
	*/

	for (int a=0;a<=5;a++)
	for (int b=0;b<=5;b++)
	for (int c=0;c<=5;c++)
	for (int d=0;d<=5;d++)
	for (int e=0;e<=5;e++)
	{
		sol[a][b][c][d][e]=a*bp[0]+b*bp[1]+c*bp[2]+d*bp[3]+e*bp[4];
	}

	//dp solution
	for (int a=0;a<=5;a++)
	for (int b=0;b<=5;b++)
	for (int c=0;c<=5;c++)
	for (int d=0;d<=5;d++)
	for (int e=0;e<=5;e++)
	{
		for (int f=0;f<S;f++)
		{
			map<int, int>cur=offers[f];
			if (a-cur[t[0]]<0 ||
				b-cur[t[1]]<0 ||
				c-cur[t[2]]<0 ||
				d-cur[t[3]]<0 ||
				e-cur[t[4]]<0)
			{
				continue;
			}
			sol[a][b][c][d][e]=min(prices[f]+sol
					[a-cur[t[0]]]
					[b-cur[t[1]]]
					[c-cur[t[2]]]
					[d-cur[t[3]]]
					[e-cur[t[4]]]
				,sol[a][b][c][d][e]);
		}
	}
	cout << sol[n[0]][n[1]][n[2]][n[3]][n[4]] << endl;
	fout << sol[n[0]][n[1]][n[2]][n[3]][n[4]] << endl;
	return 0;
}
