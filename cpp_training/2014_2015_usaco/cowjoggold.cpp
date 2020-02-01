// 2014 December Gold P3

#include <algorithm>
#include <iostream>
#include <fstream>
#include <vector>

using namespace std;

int N, T;

long long sp[100000], ep[100000];
int main()
{
	long long i, j, k;
	ifstream fin("cowjog.in");
	ofstream fout("cowjog.out");
	fin >> N >> T;
	for (i=0;i<N;i++) {
		fin >> ep[i] >> j;
		ep[i] += sp[i] + j*T;
	}
	//longest decreasing sequence
	vector<long long> ans;
	for (i=0;i<N;i++) 
	{
		if (ans.empty() || ep[i]<=ans.back())
			ans.push_back(ep[i]);
		else
			for (j=0;j<ans.size();j++) 
				if (ans[j]<ep[i])
				{
					ans[j]=ep[i];
					break;
				}
	}
	cout << ans.size() << endl;
	fout << ans.size() << endl;
	return 0;
}
