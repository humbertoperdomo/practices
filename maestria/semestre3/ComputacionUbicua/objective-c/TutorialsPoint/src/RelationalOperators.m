#import <Foundation/Foundation.h>

int main() {
  int a = 21;
  int b = 10;
  int c;

  if (a == b) {
    NSLog(@"Line 1 - a is equal to b");
  } else {
    NSLog(@"Line 1 - a is not equal to b");
  }

  if (a < b) {
    NSLog(@"Line 2 - a is less than b");
  } else {
    NSLog(@"Line 2 - a is not less than b");
  }

  if (a > b) {
    NSLog(@"Line 3 - a is greater than b");
  } else {
    NSLog(@"Line 3 - a is not greater than b");
  }

  // Lets change value of a and b
  a = 5;
  b = 20;

  if (a <= b) {
    NSLog(@"Line 4 - a is either less than or equal to b");
  }

  if (a >= b) {
    NSLog(@"Line 5 - a is either greater than or equal to b");
  }

  return 0;
}
