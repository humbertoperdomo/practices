#import <Foundation/Foundation.h>

int main() {
  int a = 4;
  short b;
  double c;
  int* ptr;
  
  // Example of sizeof operator
  NSLog(@"Storage size for int: %d \n", sizeof(int));
  NSLog(@"Storage size for float: %d \n", sizeof(float));

  NSLog(@"Line 1 - Size of variable a = %d", sizeof(a));
  NSLog(@"Line 2 - Size of variable b = %d", sizeof(b));
  NSLog(@"Line 3 - Size of variable c = %d", sizeof(c));

  // Example of & and * operators
  ptr = &a; // ptr now contains the address of a
  NSLog(@"value of a is %d", a);
  NSLog(@"*ptr is %d.", *ptr);
  // Example of ternary operator
  a = 10;
  b = (a == 1) ? 20 : 30;
  NSLog(@"Value of b is %d", b);

  b = (a == 10) ? 20 : 30;
  NSLog(@"Value of b is %d", b);

  return 0;
}
