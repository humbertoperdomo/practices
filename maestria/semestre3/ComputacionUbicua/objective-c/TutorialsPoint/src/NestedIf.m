#import <Foundation/Foundation.h>

int main() {
  int a = 100;
  int b = 200;

  if(a == 100) {
    if(b == 200) {
      NSLog(@"Value of a is 100 and b is 200");
    }
  }
  NSLog(@"Exact value of a is: %d", a);
  NSLog(@"Exact value of b is: %d", b);

  return 0;
}
