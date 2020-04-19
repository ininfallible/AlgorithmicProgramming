#include <bits/stdc++.h>

using namespace std;

int arr[101];
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
		cin >> N;
		for (i=0;i<N;i++) cin >> arr[i];
		for (i=1;i<N-1;i++) 
			if (arr[i-1]<arr[i]&&arr[i+1]<arr[i])
				ans++;
		cout <<"Case #"<<t<<": "<<ans<<'\n';
	}
	return 0;
}
