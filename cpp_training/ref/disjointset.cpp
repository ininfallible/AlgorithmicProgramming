#include <iostream>
#include <fstream>

using namespace std;

int link[10];
int size[10];

int find (int x) 
{
	//goes up the tree, where link[root]=root
	//logn
	while (x != link[x]) x = link[x];
	return x;
}

bool same (int a, int b)
{
	return find(a) == find(b);
}

void join(int a, int b)
{
	//find root/representative of both numbers
	a = find(a);
	b = find(b);
	//join smaller tree to larger tree = path compression
	if (size[a]<size[b]) swap(a, b);
	size[a] += size[b];
	link[b] = a;
}

int main ()
{
	for (int i=0;i<10;i++) link[i]=i;
	for (int i=0;i<10;i++) size[i]=1;
	return 0;
}
