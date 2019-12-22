/*
ID: derek.l3
PROB: fact4
LANG: C++11
*/
#include <iostream>
#include <fstream>

using namespace std;

int main() 
{
	int i, j, k;
	int n, ans=1;
	ifstream fin("fact4.in");
	ofstream fout("fact4.out");
	fin >> n;
	for (i=1;i<=n;i++)
	{
		ans *= i;
		while (ans%10==0)
			ans/=10;
		ans%=10000;
	}
	fout << ans%10 << endl;
	cout << ans%10 << endl;
	return 0;
}
