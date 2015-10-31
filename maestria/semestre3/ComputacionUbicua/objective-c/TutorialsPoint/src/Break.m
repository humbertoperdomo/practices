#import <Foundation/Foundation.h>

int main() {
  int a = 10;

  while(a < 20) {
    NSLog(@"value of a: %d", a);
    a++;
    if(a > 15) {
      break;
    }
  }

  return 0;
}
