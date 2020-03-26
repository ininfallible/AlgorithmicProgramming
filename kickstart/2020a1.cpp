#include <bits/stdc++.h>

using namespace std;

int main()
{
	int i,j,k;
	int T;
	int N, B;
	//ifstream cin("2020a1.in");
	cin >> T;
	for (int t=1;t<=T;t++)
	{
		int ans = 0;
		cin >> N >> B;
		vector<int> v;
		for (i=0;i<N;i++)
		{
			cin >> j;
			v.push_back(j);
		}
		sort(v.begin(),v.end());
		for (i=0;i<N;i++)
		{
			B -= v[i];
			if (B >= 0) ans++;
			else break;
		}
		cout <<"Case #"<<t<<": "<<ans<<'\n';
	}
	return 0;
}
