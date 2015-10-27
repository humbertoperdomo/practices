#include <iostream>
using namespace std;

int main() {
  enum color {red, green=5, blue} c;
  c = red;
  cout << "Value of red in the enum c is: " << red << endl;
  return 0;
}
