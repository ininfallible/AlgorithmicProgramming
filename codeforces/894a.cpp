//basically find QAQ in a string, doesn't need to be consecutive
#include <bits/stdc++.h>

using namespace std;

int ps[101];
int main()
{
	string s;
	cin >> s;
	for (int i=0;i<s.length();i++)
	{
		ps[i]=(s[i]=='Q');
		if (i>0)ps[i]+=ps[i-1];
	}
	int ans=0;
	for (int i=0;i<s.length();i++)
	if(s[i]=='A') ans += ps[i-1]*(ps[s.length()-1]-ps[i]);
	cout << ans << endl;
	return 0;
}
