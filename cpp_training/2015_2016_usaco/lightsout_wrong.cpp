//2015-2016 Jan Gold P1 
//not a solution

#include <iostream>
#include <fstream>
#include <queue>
#include <algorithm>
#include <vector>

using namespace std;

//comp_angle () 

int N;
int B[201][2];
int dl[201], dd[201];
int dir[201], ang[201];

int dist(int ind) 
{
	return abs(B[ind+1][0]-B[ind][0])+abs(B[ind+1][1]-B[ind][1]);
}

int main()
{
	int i,j,k;
	ifstream fin("lightsout.in");
	ofstream fout("lightsout.out");
	fin >> N;
	for (i=0;i<N;i++) 
		fin >> B[i][0] >> B[i][1];
	B[N+1][0]=B[0][0]; B[N+1][1]=B[0][1];

	//calculate distance in light
	for (i=1;i<N;i++)
		dl[i]=dl[i-1]+dist(i-1);
	dl[N]=dist(N);
	for (i=N-1;i>0;i--)
		dl[i]=min(dl[i], dl[i+1]+dist(i));
	//calculate angles
	//dir: direction between i, i+1 NESW
	for (i=0;i<N;i++) 
	{
		if (B[i][1]<B[i+1][1]) 
			dir[i]=0;
		else if (B[i][0]<B[i+1][0]) 
			dir[i]=1;
		else if (B[i][1]>B[i+1][1]) 
			dir[i]=2;
		else if (B[i][0]>B[i+1][0]) 
			dir[i]=3;
	}
	//for each i: calculate angle
	for (i=1;i<N;i++)
	{
		if ((dir[i-1]-dir[i]+4)%4==3)
			ang[i]=90;
		else
			ang[i]=270;
	}
	//for each i: calculate unique 
	bool unique[201];
	unique[0]=true;
	int a, b;
	vector<int> seq[201];
	for (i=0;i<N;i++)
	{
		if (!unique[i]) 
		{
			//case reach end
			if (i+seq[i].size()/2>=N-1) 
			{
				unique[i]=true;
				continue;
			}
			//extend seq[i]
			seq[i].push_back(ang[i+seq[i].size()/2]);
			seq[i].push_back(dist(i+seq[i].size()/2));
		}
		//update unique
		for (a=0;a<N;a++)
		{
			if (unique[a]) continue;
			for (b=0;b<N;b++)
				if (b!=a&&seq[b]==seq[a]) 
					break;
			if (b==N) unique[i]=true;
		}
	}
	//calcuate dd
	for (i=0;i<N;i++)
	{
		int end=i+seq[i].size()/2;
		dd[i]+=dl[end];
		for (j=i;j<end;j++)
			dd[i]+=dist(j);
	}
	int ans = 0;
	for (i=1;i<N;i++)
	{
		ans=max(dd[i]-dl[i], ans);
	}
	fout << ans << endl;
	cout << ans << endl;

	return 0;
}
