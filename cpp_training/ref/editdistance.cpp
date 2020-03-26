#include <iostream>
#include <fstream>

using namespace std;

int main ()
{
	string x = "love";
	string y = "movie";
	/*
	 * operations: insert, remove, modify
	 * distance (a, b) = min(distance (a, b-1) + 1,
	 * 						 distance (a-1, b) + 1,
	 * 						 distance (a-1, b-1) + cost(a,b);
	 * a, b represent prefixes of x and y
	 * cost(a,b)=0 if (x[a]=y[b]), otherwise cost=1
	 * distance (a,b-1) = insert a char at end of x
	 * distance (a-1,b) = remove a char at end of x
	 * distance (a-1,b-1) = match last char of x
	*/

	int dp[x.size()+1][y.size()+1];
	for (int i=0;i<=y.size();i++) dp[0][i]=i;
	for (int i=0;i<=x.size();i++) dp[i][0]=i;

	for (int i=1;i<=x.size();i++)
	for (int j=1;j<=y.size();j++)
	{
		dp[i][j]=min(min(dp[i][j-1]+1, dp[i-1][j]+1),
			dp[i-1][j-1] + (x[i-1]!=y[j-1]));
	}
	for (int i=0;i<=x.size();i++)
	{
		for (int j=0;j<=y.size();j++)
			cout << dp[i][j] << " ";
		cout << endl;
	}
	
	cout << dp[x.size()][y.size()] << endl;
	
	return 0;
}
