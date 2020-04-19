#include <bits/stdc++.h>

using namespace std;

long arr[101];
int main()
{
	long i,j,k;
	int T;
	long N, D;
	//ifstream cin("2020a1.in");
	cin >> T;
	for (int t=1;t<=T;t++)
	{
		long ans = 0;
		cin >> N >> D;
		for (i=0;i<N;i++)
			cin >> arr[i];
		long r;
		for (r=N-1;r>=0;r--)
			D-=D%arr[r];
		ans = D;
		cout <<"Case #"<<t<<": "<<ans<<'\n';
	}
	return 0;
}
