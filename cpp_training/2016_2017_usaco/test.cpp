#include <iostream>
#include <fstream>
#include <map>
#include <vector>

using namespace std;

int main() 
{

	map<int, vector<int>> bleh;
	bleh[0].push_back(0);
	bleh[0].push_back(1);
	cout << bleh[0].size() << endl;
	return 0;
}
