//2015 Feb. Gold

#include <iostream>
#include <fstream>
#include <unordered_map>
#include <vector>

using namespace std;
#define HM 1000000007
#define HA 100000007
#define HB 101

//extends hash of S
int hext (int h, int ch)
{
	return (1ll * h * HA + ch + HB) % HM;
	//why do we add 101?
	//h * HA shifts whole old string "right" by 1
}

int main()
{
	int i,j;
	ifstream fin("censor.in");
	ofstream fout("censor.out");
	int N;
	string S;
	cin >> S >> N;

	//T: length -> hashcode -> search strings
	unordered_map<int, unordered_map<int, vector<string>>> T;
	for (int i=0;i<N;i++)
	{
		string ti;
		cin >> ti;

		//hash ti
		int hash = 0;
		for (j=0;j<ti.size();j++)
			hash = hext(hash, ti[j]-'a');
		T[ti.size()][hash].push_back(ti);
	}
	string RET;
	//running hash of S (input string)
	vector<int> H(1, 0); //1 int with value 0
	//running hash of empty (starting from 1)
	vector<int> HAPW(1,1);
	//construct RET
	for (i=0;i<S.size();i++)
	{
		//maps chars from 0-25
		int ch = S[i]-'a';

		//extend H
		H.push_back(hext(H.back(), ch));
		//extend HAPW
		HAPW.push_back((1ll * HAPW.back() * HA) % HM);
		RET+=S[i];
		
		for (const auto& iter : T)
		{
			// iter becomes pair <length, pair<hashcode, strings w/ code>>
			// length being tested
			int len = iter.first;
			// if current censorstring is longer than out-string cont.
			if (len > RET.size())
				continue;

			// compute hash of suffix of RET with length len
			int hsub = (1ll*H[RET.size()-len] * HAPW[len] % HM);
			int hsh = (HM + H.back()-hsub) % HM;

			bool found = false;
			// jt becomes a vector of strings which have the hashcode
			auto jt = iter.second.find(hsh);
			// if there are strings with the hashcode, do a normal comparison
			if (jt != iter.second.end()) {
				for (const string& t : jt -> second) 
				{
					if (t == RET.substr(RET.size() - len)) 
					{
						RET.resize(RET.size()-len);
						H.resize(H.size()-len);
						HAPW.resize(HAPW.size()-len);
						found=true;
						break;
					}
				}
			}
			if (found) break;
		}
	}
	cout << RET << endl;
	return 0;
}
