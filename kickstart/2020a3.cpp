//implementing after contest
#include <bits/stdc++.h>

using namespace std;

int d[100000];
int main()
{
	int i,j,k;
	int T;
	int N, K;
	ifstream cin("2020a3.in");
	cin >> T;
	for (int t=1;t<=T;t++)
	{
		int ans = 0;

		cin >> N >> K;

		cin >> j;
		for (i=0;i<N-1;i++)
		{
			k = j;
			cin >> j;
			d[i]=j-k;
		}

		int l = 1, r=1e9+1, m=0;

		for (i=0;i<N-1;i++) cout << d[i] << " ";
		cout << endl;

		while (l<r)
		{
			m = (l+r)/2;
			int sumd = 0;
			for (int i=0;i<N-1;i++)
				sumd += (d[i]/m)-1+(d[i]%m?1:0);

			cout << l << " " << m << " " << r << endl;

			if (sumd>K)
				l=m+1;
			else
				r=m;
		}
		ans=l;
		cout <<"Case #"<<t<<": "<<ans<<'\n';
	}

	return 0;
}
