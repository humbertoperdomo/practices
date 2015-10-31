#import <Foundation/Foundation.h>

int main() {
  char grade = 'B';

  switch(grade) {
    case 'A':
      NSLog(@"Excellent!");
      break;
    case 'B':
    case 'C':
      NSLog(@"Well done");
      break;
    case 'D':
      NSLog(@"You passed");
      break;
    case 'F':
      NSLog(@"Better try again");
      break;
    default:
      NSLog(@"Invalid grade");
  }
  NSLog(@"Your grade is %c", grade);

  return 0;
}
