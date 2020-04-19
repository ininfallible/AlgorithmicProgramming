#include <bits/stdc++.h>

using namespace std;
typedef pair<long, long> pii;
#define f first
#define s second

pii par(string in)
{
	pii out = make_pair(0 , 0);
	int i=0;
	for (int j=0;j<in.length();j++)
	{
		if (i==0)
		{
			//cout <<"processing: " << in[j] << endl;
			if (in[j]=='N') out.s--;
			else if (in[j]=='S') out.s++;
			else if (in[j]=='E') out.f++;
			else if (in[j]=='W') out.f--;
		} 
		if (2<=in[j]-'0'&&in[j]-'0'<=9)
		{
			int r = in[j]-'0';
			j++;
			int ss=j+1, se;
			int i=1;
			while(i!=0&&j<in.length()-1)
			{
				j++;
				if (in[j]=='(') i++;
				if (in[j]==')') i--;
			}
			se = j;
			//cout << "par: ";
			//cout << in.substr(ss, se-ss) << endl;
			pii tmp = par(in.substr(ss, se-ss));
			out.f += tmp.f*r; out.s += tmp.s*r;
			//cout << "j="  << j << endl;
			//j++;
		}
	}
	//cout << "ret: " << out.f << " "<< out.s <<endl;
	while (out.f > 1e9) out.f -= 1e9;
	while (out.s > 1e9) out.s -= 1e9;
	while (out.f < -1e9) out.f += 1e9;
	while (out.s < -1e9) out.s += 1e9;
	return out;
}
int main()
{
	int i,k;
	int T;
	string S;
	//ifstream cin("2020a1.in");
	cin >> T;
	for (int t=1;t<=T;t++)
	{
		cin >> S;
		pii ans = par(S);
		ans.f++;
		ans.s++;
		while (ans.f > 1e9) ans.f -= 1e9;
		while (ans.s > 1e9) ans.s -= 1e9;
		while (ans.f <= 0) ans.f += 1e9;
		while (ans.s <= 0) ans.s += 1e9;
		cout <<"Case #"<<t<<": "<<ans.f<<" "<<ans.s<<'\n';
	}
	return 0;
}
