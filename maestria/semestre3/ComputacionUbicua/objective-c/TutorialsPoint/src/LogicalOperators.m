#import <Foundation/Foundation.h>

int main() {
  int a = 5;
  int b = 20;
  int c;

  if (a && b) {
    NSLog(@"Line 1 - Condition is true");
  }
  if (a || b) {
    NSLog(@"Line 2 - Condition is true");
  }
  
  // Lets change the value of a and b
  a = 0;
  b = 10;
  if (a && b) {
    NSLog(@"Line 3 - Condition is true");
  } else {
    NSLog(@"Line 3 - Condition is not true");
  }
  if (!(a && b)) {
    NSLog(@"Line 4 - Condition is true");
  }

  return 0;
}
