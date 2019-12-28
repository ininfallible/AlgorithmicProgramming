//a temporary file to generate input files

#include <iostream>
#include <fstream>

using namespace std;

int main()
{
	ofstream fout("range.in");
	fout << 250 << endl;
	for (int i=0;i<250;i++)
	{
		for (int j=0;j<250;j++)
		{
			fout << 1;
		}
		fout << endl;
	}
	return 0;
}
