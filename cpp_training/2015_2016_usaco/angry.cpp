// 2015-2016 January Contest P1
// ad-hoc and dp 
// observation - scan from left/right, min when they cross is answer
// implementing given solution for understanding

#include <iostream>
#include <fstream>
#include <algorithm>

using namespace std;

int N;
int c[50001];
int dp[500001];
int main()
{
	int i, j, k;
	ifstream fin("angry.in");
	ofstream fout("angry.out");

	//read input
	fin >> N;
	for (i=0;i<N;i++)
		fin >> c[i];
	sort(c, c+N);

	//scan from both ends
	int beg=0,rr=0,rl=0,end=N-1;
	double best = 1<<20;
	while (true)
	{
		//val is max between ___A-----B____ , r, r1
		//where the second term is distance between beg(A), and end(B)
		//beg and end are bounds of search
		double val=max((double)(max(rl,rr)+1),(double)(c[end]-c[beg])/2);
		if (val<best) best=val;
		//distance between node and next node for both directions
		int potl = max(rl,c[beg+1]-c[beg]);
		int potr = max(rr+1,c[end]-c[end-1]);
		if (potl<potr)
		{
			// advance search from left 
			// :
			rl=potl;
			int ind = beg;
			//c[beg+potl=c[ind+1]]
			while (ind<N-1&&c[beg]+potl>=c[ind+1]) ind++;
			beg=ind;
		}
		else
		{
			rr=potr;
			int ind = end;
			while (ind>0&&c[end]-potr<=c[ind-1]) ind--;
			end=ind;
		}
		if (end<=beg) 
		{
			best = min(best,(double)max(rl,rr));
			break;
		}
	}
	cout << best << endl;
	printf("%2.1f\n", best);
	return 0;
}
