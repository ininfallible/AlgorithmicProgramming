//2018 Dec Gold P2
//implementing sol
//ad hoc problem
#include <bits/stdc++.h>
using namespace std;

#define pb push_back
#define mp make_pair
#define fi first
#define se second
typedef vector<int> vi;
typedef pair<int, int> pii;
typedef long long ll;
const int INF = 1<<20;

//inc_exc -> 0, 1, 2, 3, 4, 5 flavors in common
long long N, inc_exc[] = {-1, 1, -1, 1, -1, 1};
//5 ints
struct S5 
{
	int n;
	int v[5];
};
S5 A[100000]; //max no. of flavors possible
bool operator< (const S5&a, const S5 &b)
{
	for (int j=0;j<5;j++)
	{
		if (a.v[j] < b.v[j]) return true;
		if (a.v[j] > b.v[j]) return false;
	}
	return false;
}
S5 get_subset(S5 &a, int x)
{
	S5 result = {0, {0,0,0,0,0}}; //struct sorcery
	//how tf does this work?
	//basically it inverts the binary rep. of x, then masks a to get result
	for (int j=0;j<5;j++)
		if((1<<j)&x) result.v[result.n++] = a.v[j];
	return result;
}


map<S5, int> subsets;

int main()
{
	int i,j,k;
	ifstream fin("cowpatibility.in");
	ofstream fout("cowpatibility.out");
	fin >> N;
	for (i=0;i<N;i++)
	{
		A[i].n = 5;
		for(j=0;j<5;j++)
			fin >> A[i].v[j];
		sort(A[i].v, A[i].v+5); //sorts the flavors for the c
		// generates 2^5 subsets of flavors for the cow, adds to tally in map of 
		// flavor -> no. of cows
		for(int x=1;x<32;x++) subsets[get_subset(A[i],x)]++; 
	
	}
	long long ans = N*(N-1)/2; // starts with all pairs of cows

	for (auto &p : subsets) //iterate through key-val pairs
		ans -= inc_exc[p.first.n] * p.second*(p.second-1) / 2;
	//^ apply correct coefficient, formula for PAIRS OF COWS

	fout << ans << '\n';
	return 0;
}

