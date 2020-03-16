//2020 Feb Gold P2
//Implementing solution
#include <iostream>
#include <fstream>
#include <vector>

using namespace std;

typedef pair<int, int> pii;
#define f first
#define s second

int N;
const int MOD = 1e9+7;

int main() 
{
	int i,j,k;
	ifstream fin("help.in");
	ofstream fout("help.out");
	//read input
	fin >> N;
	vector <pii> v(N);
	for (auto& a : v) fin >> a.f >> a.s;

	vector<int> over(2*N+1), po2(N);

	//precompute powers of 2
	po2[0]=1; 
	for (i=1;i<N;i++) po2[i] = 2*po2[i-1]%MOD;

	//calc prefix sum
	//t.f represents start of segment, so it's ++
	//t.s represents end of segment, so it's --
	for (auto& t : v) over[t.f]++, over[t.s]--;
	for (i=1;i<=2*N;i++) over[i] += over[i-1];

	int ans=0;
	//2 ^ (N-1-{#of intervals that contain (i-1, i)})
	for (auto& t:v) ans = (ans+po2[N-1-over[t.f-1]])%MOD;
	cout << ans << "\n";

	return 0;
}
