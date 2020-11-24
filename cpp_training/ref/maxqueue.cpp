#include <bits/stdc++.h>

using namespace std;

template <class T>
struct max_queue {
  explicit max_queue(size_t sz) : X(sz), Y(sz), a(0), b(0), va(0), vb(0) {
  }

  void printstate()
  {
	  for (int i=0;i<8;i++) cout << X[i] << " ";
	  cout << endl;
	  for (int i=0;i<8;i++) cout << Y[i] << " ";
	  cout << endl;
	  cout << "a: " << a << endl; 
	  cout << "b: " << b << endl; 
	  cout << "va: " << va << endl; 
	  cout << "vb: " << vb << endl; 
	  cout << endl;
  }

  void push(const T& v) {
    while(va < vb && X[vb - 1] <= v) vb--;
    X[vb] = v;
    Y[vb++] = b++;
	printstate();
  }

  void pop() {
    va += a++ == Y[va];
	printstate();
  }

  T max() {
    return X[va];
  }

  vector<T> X;
  vector<size_t> Y;
  size_t a, b, va, vb;
};

int main()
{
	max_queue<int> test(10);
	cout << "pushing: 4 3 1 2 3" << endl;
	test.push(4);
	test.push(3);
	test.push(1);
	test.push(2);
	test.push(3);
	cout << "max: "<< test.max() << endl;
	test.pop();
	cout << "max: "<< test.max() << endl;
	test.pop();
	cout << "max: "<< test.max() << endl;
	test.pop();
	cout << "max: "<< test.max() << endl;
	test.pop();
	cout << "max: "<< test.max() << endl;
}
