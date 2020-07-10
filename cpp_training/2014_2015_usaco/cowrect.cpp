//2015 Jan Gold P1
//implementing sol from problem
//concept was right but couldn't implement scanning algo correctly :(
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
const int MAXN = 500;

struct cow {
	int x; int y; bool h;
	bool operator<(cow b)  const
	{
		return x < b.x;
	}
};
bool sort_y (cow a, cow b)
{
	return a.y<b.y;
}
int N;
vector<cow> sxc;
vi sy;

int main()
{
	ifstream fin("cowrect.in");
	ofstream fout("cowrect.out");
	int i,j,k;
	char tmp;
	fin >> N;
	for (i=0;i<N;i++)
	{
		fin >> j >> k >> tmp;
		sxc.pb(cow{j, k, tmp=='H'});
		sy.pb(k);
	}
	sort(sxc.begin(), sxc.end());
	sort(sy.begin(), sy.end());

	int ans_area=INF, ans_cow=0;
	//for (i=0;i<N;i++) cout << scx[i].x << endl; //debug
	for (int lo=0;lo<sy.size();lo++)
	for (int hi=lo+1;hi<sy.size();hi++)
	{
		bool valid = false;
		int px = -1;
		int cx = 0;
		int sum = 0;
		for (int cur = 0; cur<N;)
		{
			int it = cur;
			int h=0, g=0;
			while (it < N && sxc[cur].x==sxc[it].x)
			{
				if (sy[lo]<=sxc[it].y && sxc[it].y<=sy[hi]);
				{
					if (sxc[it].h) h++;
					else g++;
					it++;
				}
				if (g>0) valid = false, sum=0;
				else if (h + g > 0)
				{
					if (!valid)
					{
						valid = true;
						px = sxc[cur].x;
					}
					sum += h;
					int area = (sy[hi]-sy[lo] * (sxc[cur].x - px));
					if (sum > ans_area || (sum==ans_area && area < ans_area))
					{
						ans_area = sum;
						ans_area = area;
					}
					cur = it;
				}
			}
		}
	}

	cout << ans_cow << endl;
	cout << ans_area << endl;
	return 0;
}

