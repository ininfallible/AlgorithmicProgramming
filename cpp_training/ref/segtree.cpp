//Notes for segment tree implementation 

#include <iostream>
#include <algorithm>

using namespace std;
int segtree[1<<20];
int N;

int qsum (int a, int b)
{
	int s = 0;
	a += N-1, b += N-1;
	//cout<<a<<" "<<b<<" "<<N-1<<" "<<endl;
	while (a<=b) 
	{
		if (a%2==1) s += segtree[a++];
		if (b%2==0) s += segtree[b--];
		a/=2, b/=2;
	}
	return s;
}
void add(int k, int n)
{
	k += N;
	segtree[k] += n;
	for (k/=2;k>=1;k/=2)
		segtree[k] = segtree[2*k]+segtree[2*k+1];
}
int main ()
{
	int arr[]{5, 8, 6, 3, 2, 7, 2, 6};
	//construct a tree
	N=1;
	int arrsize = sizeof(arr)/sizeof(int);

	cout << arrsize << endl;

	while (N<arrsize) N*=2;
	//init segtree
	fill(segtree, segtree+N*2, 0);
	for (int i=N*2-1;i>0;i--)
	{
		if (i>=N&&i-N<arrsize) 
			segtree[i] = arr[i-N];
		else if (i<N)
			segtree[i] = segtree[i*2] + segtree[i*2+1];
	}

	for (int i=0;i<N*2;i++)
		cout << segtree[i] << " ";
	cout << endl;
	cout << "====TESTS====" << endl;
	cout << qsum(3, 6) << endl;
	add(5, 10);
	cout << qsum(3, 6) << endl;
	return 0;
}
