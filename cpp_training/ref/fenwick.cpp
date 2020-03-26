#include <iostream>
#include <fstream>

using namespace std;

//indexing from 1
int arr[]={0,1,2,3,4,5,6,7,8,9,10};
int tree[11];
int n = 10;
int sum (int k)
{
	int s=0;
	while (k>=1)
	{
		s += tree[k];
		// k&-k returns smallest power of 2 less than k;
		k-=k&-k;
	}
	return s;
}
void add (int k, int x)
{
	while (k <= n)
	{
		tree[k] += x;
		k += k&-k;
	}
}

int main ()
{
	//construction (nlogn)
	for (int i=1;i<=n;i++) add(i,arr[i]);
	cout << sum(2) << endl;
	cout << sum(4) << endl;
	return 0;
}
