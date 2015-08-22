#include <iostream>

int main() {
  int sum = 0, value = 50;
  // keep executing the while as long as value is less than or equal to 100
  while (value <= 100) {
    sum += value; // assigns sum + value to sum
    ++value;      // add 1 to val
  }  
  std::cout << "Sum of 50 to 100 inclusive is " << sum << std::endl;
}
