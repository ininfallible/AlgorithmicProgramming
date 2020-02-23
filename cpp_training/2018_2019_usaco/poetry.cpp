// 2019 Jan Gold P1

#include <iostream>
#include <fstream>
#include <vector>
#include <map>
#include <cmath>

using namespace std;

typedef pair<int, int> pii;

const int MAXN = 5001;
const int MP = 1000000007;
//const int MAXK = 5001;
//const int MAXM = 100000;

int N, M, K;
int main()
{
	int i,j,k;
	ifstream fin("poetry.in");
	ofstream fout("poetry.out");
	fin >> N >> M >> K;
	vector<pii> words;
	for (i=0;i<N;i++)
	{
		fin >> j >> k;
		words.push_back(make_pair(j,k));
	}
	//run knapsack
	unsigned long dp[MAXN]={0};
	dp[0]=1;
	for (i=0;i<K;i++)
	for (auto a : words)
	{
		if (i-a.first>=0) dp[i] += dp[i-a.first];
		dp[i] %= MP;
	}
	//represent number of possible lines for rhyme group ci
	map<int,unsigned long> ways;
	for (auto a : words)
	{
		ways[a.second] += dp[K-a.first];
		ways[a.second] %= MP;
	}
	int classes[26]={0};
	char c;
	for (i=0;i<M;i++)
	{
		fin >> c;
		classes[c-'A']++;
	}

	//wip
	long long ans=1;
	for (i=0;i<26;i++)
	{
		if (classes[i] != 0)
		{
			long long tmp=0;
			for (auto a : ways)
			{
				long long tmp2 = 1;
				for (j=0;j<classes[i];j++)
				{
					tmp2 *= a.second;
					tmp2 %= MP;
				}
				tmp += tmp2;
				tmp %= MP;
			}
			ans *= tmp;
		}
		ans %= MP;
		//cout << ans << endl;
	}
	//debug
	/*
	cout << "classes: ";
	for (int i=0;i<26;i++) cout << classes[i] << " ";
	cout << endl;

	cout << "dp: ";
	for (i=0;i<K;i++) cout << dp[i] << " ";
	cout << endl;

	cout << "ways: " << endl;
	for (auto a : ways) cout << a.first << " " << a.second << endl;

	cout << "ans: " << ans << endl;
	*/
	fout << ans << endl;
	return 0;
}
