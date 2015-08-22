#include <iostream>

int main() {
  int val1 = 0, val2 = 0;

  std::cout << "Enter two numbers: " << std::flush;
  std::cin >> val1 >> val2;
  
  if (val1 <= val2) {
    while (val1 <= val2) {
      std::cout << val1 << std::endl;
      ++val1;
    }
  } else {
    std::cout << "First value must be lower than second value." << std::endl;
  }
}
