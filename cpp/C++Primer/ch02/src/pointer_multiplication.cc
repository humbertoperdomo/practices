#include <iostream>

int main() {
  int i = 42;
  int *p1 = &i;
  *p1 = *p1 * *p1;
  
  std::cout << "Result: " << *p1 << std::endl;

  int j = 0;
  // double* dp = &j;  // cannot convert ‘int*’ to ‘double*’ in initialization
  // int *ip = i;  // invalid conversion from ‘int’ to ‘int*’
  int *p = &j;

  return 0;
}
