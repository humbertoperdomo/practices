#include <iostream>

int main() {
  int i = 1024, *p = &i, &r = i;
  std::cout << i << " " << *p << " " << &r << std::endl;
  double dval;
  double *pd = &dval;
  double *pd2 = pd;
  // int *pi = pd; // error: types of pi and pd differ
  // pi = &dval; // error: assigning the address of a double to a pointer to int
  int ival = 42;
  int *p2 = &ival;
  std::cout << *p2 << std::endl;

  p2 = &i;
  std::cout << p2 << " " << *p2 << std::endl;

  *p2 = 101;
  std::cout << &i << " " << i << std::endl;
  std::cout << p2 << " " << *p << std::endl;
  //*p2 = nullptr;
  double obj = 3.14, *pd3 = &obj;
  // ok: void* can hold the address value of any data pointer type
  void *pv = &obj;  // obj can be an object of any type
  pv = pd3;          // pv van hold a pointer to any type
  std::cout << obj << " " << *pd3 << " " << pv << std::endl;

  return 0;
}
