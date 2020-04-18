#include <bits/stdc++.h>

using namespace std;

int main()
{
	//reading some ints
	int arr[10];
	for (int i=0;i<10;i++)
		scanf("%i", &arr[i]);
	for (auto a : arr) cout << a << endl;
	int h, w;
	scanf("%i%i", &h, &w);
	cout << h << " " << w << endl;
	return 0;
}
