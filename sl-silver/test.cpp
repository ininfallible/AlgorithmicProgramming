#include <iostream>
#include <queue>

using namespace std;
queue<int> q;
queue<int> p;

void bleh() {
	queue<int>().swap(q);
}

int main() {
	p = q;
	q.push(1);
	cout << p.empty() << endl;
	bleh();
	cout << p.empty() << endl;
}



