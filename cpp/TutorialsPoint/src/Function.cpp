#include <iostream>
using namespace std;

int max(int num1, int num2);

int main() {
  int a = 100;
  int b = 200;
  int ret;

  ret = max(a, b);

  cout << "Max value is: " << ret << endl;

  return 0;
}

int max(int a, int b) {
  int result;

  if(a > b)
    result = a;
  else
    result = b;

  return result;
}
