//2016 US Open Gold P1
#include <bits/stdc++.h>
using namespace std;

#define pb push_back
#define mp make_pair
#define fi first
#define se second
typedef vector<int> vi;
typedef pair<int, int> pii;
typedef long long ll;
const int INF = 1<<30;

int N;
vector<pii> cows;
int main()
{
	ifstream fin("split.in");
	ofstream fout("split.out");
	int i,j,k;
	fin >> N;
	int hix=0, lox=INF, hiy=0, loy=INF;
	for (i=0;i<N;i++)
	{
		fin >> j >> k;
		hix = max(hix, j);
		lox = min(lox, j);
		hiy = max(hiy, k);
		loy = min(loy, k);
		cows.pb(mp(j, k));
	}
	ll ans1 = (ll)(hix-lox) * (ll)(hiy-loy);
	ll ans2 = ans1;
	//sort by x, then y?
	sort(cows.begin(), cows.end());
	//scan algo: 
	multiset<int> fx, fy, bx, by;
	for (int argh=0;argh<2;argh++)
	{
		for (i=0;i<N;i++)
		{
			bx.insert(cows[i].fi);
			by.insert(cows[i].se);
		}
		for (int ind=0;cows[ind].fi<(*cows.rbegin()).fi;)
		{
			//cows[ind] is in the first rect
			//update sets
			int next=ind+1;
			while (cows[ind].fi==cows[next].fi && next<N)
				next++;
			for (i=ind;i<next;i++)
			{
				fx.insert(cows[i].fi); fy.insert(cows[i].se);
				bx.erase(bx.find(cows[i].fi)); by.erase(by.find(cows[i].se));
			}
			//update area
			int fhix=*fx.rbegin(), flox=*fx.begin(), fhiy=*fy.rbegin(), floy=*fy.begin();
			int bhix=*bx.rbegin(), blox=*bx.begin(), bhiy=*by.rbegin(), bloy=*by.begin();

			/*
			//debug
			for (auto a: bx) cout << a << " ";
			cout << endl;
			for (auto a: by) cout << a << " ";
			cout << endl;
			*/

			ll pos_area = abs(((ll)(fhix-flox))*((ll)(fhiy-floy)));
			pos_area  +=  abs(((ll)(bhix-blox))*((ll)(bhiy-bloy)));
			ans2 = min(ans2, pos_area);
			ind=next;
		}
		//transforms for all possibilities
		if (argh==0)
			for (i=0;i<N;i++) 
			{
				int tmp=cows[i].se;
				cows[i].se = cows[i].fi;
				cows[i].fi = tmp;
			}

		fx.clear();fy.clear();bx.clear();by.clear();
	}
	//out << ans1 << endl;
	fout << ans1-ans2 << endl;
	
	return 0;
}
