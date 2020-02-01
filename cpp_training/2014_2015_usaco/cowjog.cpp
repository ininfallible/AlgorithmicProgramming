//2014-2015 December Silver P1

#include <iostream>
#include <fstream>
#include <algorithm>

using namespace std;
int N, T;
struct Cow
{
	long long d, s;
	Cow (long long a, long long b) : d(a), s(b) {};
	Cow () : d(0), s(0) {};
};
bool c_cow (Cow a, Cow b) 
{
	return (a.d-b.d)<=0;
}
Cow cows[100000];
int main() 
{
	int i, j, k;
	ifstream fin("cowjog.in");
	ofstream fout("cowjog.out");
	fin >> N >> T;
	for (i=0;i<N;i++) 
		fin >> cows[i].d >> cows[i].s;
	sort(cows,cows+N,c_cow);
	for (i=0;i<N;i++) 
		cows[i].d += cows[i].s*T;
	int out=1;
	long long cur=cows[N-1].d;
	for (i=N-1;i>=0;i--) 
		if (cows[i].d < cur) 
		{
			out++;
			cur=cows[i].d;
		}
	//debug
	//for (i=0;i<N;i++) 
	//	cout << cows[i].d << " " << cows[i].s << endl;
	//cout << out << endl;
	fout << out << endl;

	return 0;
}
