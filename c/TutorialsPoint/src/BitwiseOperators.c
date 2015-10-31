#include <stdio.h>
#include <string.h>

#define SZ 32

static char* binrep(unsigned int, char*, int);

int main() {
  unsigned int a = 60; // 60 = 0011 1100
  unsigned int b = 13; // 13 = 0000 1101
  int c = 0;
  char buff[SZ+1];

  printf("a is %d[%s]\n", a, binrep(a, buff, SZ));
  printf("b is %d[%s]\n", b, binrep(b, buff, SZ));

  c = a & b;
  printf("Line 1 - Value of c is %d[%s]\n", c, binrep(c, buff, SZ));

  c = a | b;
  printf("Line 2 - Value of c is %d[%s]\n", c, binrep(c, buff, SZ));

  c = a ^ b;
  printf("Line 3 - Value of c is %d[%s]\n", c, binrep(c, buff, SZ));

  c = ~a;
  printf("Line 4 - Value of c is %d[%s]\n", c, binrep(c, buff, SZ));

  c = a << 2;
  printf("Line 5 - Value of c is %d[%s]\n", c, binrep(c, buff, SZ));

  c = a >> 2;
  printf("Line 6 - Value of c is %d[%s]\n", c, binrep(c, buff, SZ));

  return 0;
}

static char* binrep(unsigned int val, char *buff, int sz) {
  char *pbuff = buff;

  // Must be able to store one character at least.
  if (sz < 1) return NULL;

  // Special case for zero to ensure some output.
  if (val == 0) {
    *pbuff++ = '0';
    *pbuff = '\0';
    return buff;
  }

  // Work from the end of the buffer back.
  pbuff += sz;
  *pbuff-- = '\0';

  while(val != 0) {
    if (sz-- == 0) return NULL;
    *pbuff-- = ((val & 1) == 1) ? '1' : '0';
    
    // Get nex bit
    val >>= 1;
  }
  return pbuff + 1;
}
