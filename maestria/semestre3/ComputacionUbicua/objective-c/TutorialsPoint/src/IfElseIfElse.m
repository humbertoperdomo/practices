#import <Foundation/Foundation.h>

int main() {
  int a = 100;

  if(a == 10) {
    NSLog(@"Value of a is 10");
  } else if(a == 20) {
    NSLog(@"Value of a is 20");
  } else if(a == 30) {
    NSLog(@"Value of a is 30");
  } else {
    NSLog(@"None of the values is matching");
  }
  NSLog(@"Exact value of a is: %d", a);

  return 0;
}
