#import <Foundation/Foundation.h>

int main() {
  int a = 10;

  do {
    NSLog(@"value of a: %d", a);
    a++;
  } while(a < 20);


  return 0;
}
