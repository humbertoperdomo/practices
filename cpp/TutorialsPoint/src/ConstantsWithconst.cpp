#include <iostream>
using namespace std;

int main() {
  const int LENGTH = 10;
  const int WIDTH = 5;
  const char NEWLINE = '\n';
  int area;
  
  area = LENGTH * WIDTH;
  cout << "value of area: " << area << flush;
  cout << NEWLINE << flush;
  
  return 0;
}
