//placeholder
#include <bits/stdc++.h>
using namespace std;

#define fi first
#define se second
typedef vector<int> vi;
typedef pair<int, int> pii;
typedef long long ll;
const int INF = 1<<20;

int N;
vi stacks[100001];
int bases[100001];
int main()
{
	int i,j,k;
	ifstream fin("dishes.in");
	ofstream fout("dishes.out");
	fin >> N;
	int placed = 0, ans=N;
	int cur;
	for (i=0;i<N;i++)
	{
		cin >> cur;
		if (cur<placed)
		{
			ans=i;
			break;
		}
		//if making a new stack, resolve plates that will go in this stack
		for (j=cur;j>0&&!bases[j];j--)
			bases[j]=cur;
		//remove smaller labels on the stack
		//manual: earlier plates are removed, but doesn't need to be represented in code
		while (!stacks[bases[cur]].empty() && stacks[bases[cur]].back() < cur)
		{
			placed = stacks[bases[cur]].back();
			stacks[bases[cur]].pop_back();
		}
		//add to the stack
		stacks[bases[cur]].push_back(cur);
	}
	fout << ans << endl;
	return 0;
}


