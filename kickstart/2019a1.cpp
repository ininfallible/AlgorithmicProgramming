#include <bits/stdc++.h>

using namespace std;

int main()
{
	int i,j,k;
    int T;
    cin >> T;
    for (int t=1;t<=T;t++)
    {
        int N, P;
        cin >> N >> P;
        vector<int> v;
		for (i=0;i<N;i++)
		{
			cin >> j;
			v.push_back(j);
		}
		sort(v.begin(), v.end());
		int s=0;
		for (i=0;i<P;i++)
		{
			s+=v[P-1]-v[i];
		}
		int ms=s;
		for(i=P;i<v.size();i++)
		{
			s+=P*(v[i]-v[i-1]);
			s-=v[i]-v[i-P];
			if (ms>s) ms=s;
		}
		cout <<"Case #"<<t<<": "<<ms<<'\n';
	}
    return 0;
}
