//2017 US Open P1

#include <iostream>
#include <fstream>
#include <set>
#include <cmath>

using namespace std;

int N, M;
string spotty[500], plain[500];
unsigned long long hashes1[500], hashes2[500], R[500];
int main() 
{
	//read input
	int i,j,k;
	ifstream fin("cownomics.in");
	ofstream fout("cownomics.out");
	fin >> N >> M;
	for (i=0;i<N;i++) fin >> spotty[i];
	for (i=0;i<N;i++) fin >> plain[i];
	for (i=0;i<M;i++) R[i] = rand() % 1000000000;
	i=0,j=0;
	int best=M,dups=N;
	//set up the sliding window
	while (j<M)
	{
		//if valid solution, update
		if (dups==0) best=min(best,j-i);
		if (dups>0)
		{
			set<int> H;
			dups=0;
			for (k=0;k<N;k++) H.insert(hashes1[k]+=R[j]*spotty[k][j]);
			for (k=0;k<N;k++) if (H.count(hashes2[k]+=R[j]*plain[k][j])>0) dups++;
			j++;
		}
		else
		{
			set<int> H;
			dups=0;
			for (k=0;k<N;k++) H.insert(hashes1[k]-=R[i]*spotty[k][i]);
			for (k=0;k<N;k++) if (H.count(hashes2[k]-=R[i]*plain[k][i])>0) dups++;
			i++;
		}
	}
	fout << best << "\n";
	return 0;
}
