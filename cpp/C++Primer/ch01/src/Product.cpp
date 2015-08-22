#include <iostream>

/**
 * This program get the product of two number entered
 * If get letters instead numbers the program shows 0
 */

int main() {
  std::cout << "Enter two numbers: " << std::flush;
  int v1 = 1, v2 = 1;
  std::cin >> v1 >> v2;
  std::cout << "The product of " << v1 << " and " << v2
    << " is " << v1 * v2 << std::endl;
  std::cout << "/*" << std::endl;
  std::cout << "*/" << std::endl;
  //std::cout << /*"*/" */;
  std::cout << /* "*/" /* "/*" */ << std::endl;
  return 0;
}
