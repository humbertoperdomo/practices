#include <iostream>

int main() {
  int i = 1, &ri = i;
  double d = 0, &rd = d;

  std::cout << "i: " << i << ", ri: " << ri << std::endl;
  std::cout << "d: " << d << ", rd: " << rd << std::endl;
  
  rd = 3.14159;
  std::cout << "rd = 3.14159: " << rd << std::endl;

  rd = ri;
  std::cout << "rd = ri: " << rd << std::endl;

  i = rd;
  std::cout << "i = rd: " << i << std::endl;

  ri = d;
  std::cout << "ri = d: " << ri << std::endl;


}
