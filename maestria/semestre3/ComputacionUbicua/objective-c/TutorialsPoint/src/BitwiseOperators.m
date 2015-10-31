#import <Foundation/Foundation.h>

#define SZ 32

@interface SampleClass:NSObject
+ (char*) binrep:(unsigned int)val withBuffer:(char*)buff ofSize:(int)sz;
@end

@implementation SampleClass
+ (char*) binrep:(unsigned int)val withBuffer:(char*)buff ofSize:(int)sz {
  char *pbuff = buff;

  // Must be able to store one character at least.
  if (sz < 1) return nil;

  // Special case for zero to ensure some output.
  if (val == 0) {
    *pbuff++ = '0';
    *pbuff = '\0';
    return pbuff;
  }
  
  // Work from the end of the buffer back.
  pbuff += sz;
  *pbuff-- = '\0';

  while (val != 0) {
    if (sz-- == 0) return nil;
    *pbuff-- = ((val & 1) == 1) ? '1' : '0';

    // Get next bit.
    val >>= 1;
  }

  return pbuff + 1;
}
@end

int main() {
  unsigned int a = 60; // 60 = 0011 1100
  unsigned int b = 13; // 13 = 0000 1101
  int c = 0;
  char buff[SZ+1];

  NSLog(@"a is %d[%s]", a, [SampleClass binrep:a withBuffer:buff ofSize:SZ]);
  NSLog(@"b is %d[%s]", b, [SampleClass binrep:b withBuffer:buff ofSize:SZ]);

  c = a & b;
  NSLog(@"Line 1 - Value of c is %d[%s]", c, [SampleClass binrep:c withBuffer:buff ofSize:SZ]);

  c = a | b;
  NSLog(@"Line 2 - Value of c is %d[%s]", c, [SampleClass binrep:c withBuffer:buff ofSize:SZ]);

  c = a ^ b;
  NSLog(@"Line 3 - Value of c is %d[%s]", c, [SampleClass binrep:c withBuffer:buff ofSize:SZ]);

  c = ~a;
  NSLog(@"Line 4 - Value of c is %d[%s]", c, [SampleClass binrep:c withBuffer:buff ofSize:SZ]);

  c = a << 2;
  NSLog(@"Line 5 - Value of c is %d[%s]", c, [SampleClass binrep:c withBuffer:buff ofSize:SZ]);

  c = a >> 2;
  NSLog(@"Line 6 - Value of c is %d[%s]", c, [SampleClass binrep:c withBuffer:buff ofSize:SZ]);
  
  return 0;
}
