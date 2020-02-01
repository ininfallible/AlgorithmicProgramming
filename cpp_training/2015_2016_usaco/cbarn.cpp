//2015-2016 February Gold P1
// this is the given solution
// generalizable concepts: rotate can be used to rotate an array so a favorable point is at the front

#include <iostream>
#include <fstream>
#include <vector>
#include <algorithm>
#include <set>

using namespace std;

int N;
const int MAXN=100001;

long long sum_n2(long long v) {
	return v*(v+1)*(2*v+1)/6;
}
int main()
{
	int i,j,k;
	ifstream fin("cbarn.in");
	ofstream fout("cbarn.out");
	int N; fin >> N;
	vector<long long> A(N);

	//c keeps count of cows?
	int c=0;
	for (i=0;i<N;i++)
	{
		cin >> A[i];
		c = max(0ll,c+A[i]-1);
	}

	//moves the point at which c=0 to the front
	for (i=0;;i++)
	{
		if (c==0) {
			rotate(A.begin(), A.begin()+i,A.begin()+N);
			break;
		}
		c=max(0ll,c+A[i]-1);
	}

	long long result=0;
	// for each i, sum of squares from c-1 to A[i]+c-1
	// represents all cows walking
	// when it sees new cows are added, it knows the cost will be
	// added to the current number of cows that are waiting
	// to be put into a room
	for (i=0;i<N;i++)
	{
		result += sum_n2(A[i]+c-1)-sum_n2(c-1);
		c=max(0ll,c+A[i]-1);
	}
	fout << result << endl;
	return 0;
}
