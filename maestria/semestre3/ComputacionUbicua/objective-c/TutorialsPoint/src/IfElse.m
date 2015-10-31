#import <Foundation/Foundation.h>

int main() {
  int a = 100;

  if(a < 20) {
    NSLog(@"a is less than 20\n");
  } else {
    NSLog(@"a is not less than 20\n");
  }
  NSLog(@"value of a is: %d", a);

  return 0;
}
