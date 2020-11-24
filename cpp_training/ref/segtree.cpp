//Notes for segment tree implementation 
//segment tree indexes from 1

#include <iostream>
#include <algorithm>

using namespace std;
int segtree[1<<20];
int N;

//query for a sum between a and b, inclusive
int qsum (int a, int b)
{
	int s = 0;
	//start at the base level (2N-1 nodes so base level is after N-1)
	a += N-1, b += N-1;
	//cout<<a<<" "<<b<<" "<<N-1<<" "<<endl;
	while (a<=b) 
	{
		cout << "a, b: " << a << " " << b << endl;
		// odd means a is a right child (parent is a++/2)
		// if a is right, child parent segment will contain more than query to the left
		if (a%2==1) s += segtree[a++];
		// even means b is a left child :. parent is b--/2 and
		// if b is left,  child parent segment will contain more than query to the right
		if (b%2==0) s += segtree[b--];
		// go to par of a and b
		a/=2, b/=2;
	}
	return s;
}

// update the tree
void add(int k, int n)
{
	k += N;
	segtree[k] += n;
	// move to par
	k /= 2;
	for (;k>=1;k/=2)
		// cur = sum children
		segtree[k] = segtree[2*k]+segtree[2*k+1];
}
int main ()
{
	int arr[]{1, 2, 3, 4};
	// construct a tree
	N=1;
	int arrsize = sizeof(arr)/sizeof(int);

	cout << arrsize << endl;

	while (N<arrsize) N*=2;
	// init segtree
	fill(segtree, segtree+N*2, 0);
	for (int i=N*2-1;i>0;i--)
	{
		// initialize base layer
		if (i>=N&&i-N<arrsize) 
			segtree[i] = arr[i-N];
		// if not a leaf sum children
		else if (i<N)
			segtree[i] = segtree[i*2] + segtree[i*2+1];
	}

	cout << "tree: ";
	for (int i=0;i<N*2;i++)
		cout << segtree[i] << " ";
	cout << endl;
	cout << "====TESTS====" << endl;
	cout << qsum(2, 3) << endl;
	return 0;
}
