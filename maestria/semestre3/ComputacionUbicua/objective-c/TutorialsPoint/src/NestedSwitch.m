#import <Foundation/Foundation.h>

int main() {
  int a = 100;
  int b = 200;
  
  switch(a) {
    case 100:
      NSLog(@"This is part of outer switch",a);
      switch(b) {
        case 200:
	  NSLog(@"This is part of inner switch",a);
	  break;
      }
      break;
  }
  NSLog(@"Exact value of a is: %d", a);
  NSLog(@"Exact value of b is: %d", b);

  return 0;
}
