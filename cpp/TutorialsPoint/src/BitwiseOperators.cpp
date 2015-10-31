#include <iostream>
using namespace std;

#define SZ 32

static char* binrep(unsigned int, char*, int);

int main() {
  unsigned int a = 60; // 60 = 0011 1100
  unsigned int b = 13; // 13 = 0000 1101
  int c = 0;
  char buff[SZ+1];

  cout << "a is " << a << "[" << binrep(a, buff, SZ) << "]" << endl;
  cout << "b is " << b << "[" << binrep(b, buff, SZ) << "]" << endl;

  c = a & b;
  cout << "Line 1 - Value of c is: " << c << "[" << binrep(c, buff, SZ) << "]" << endl;

  c = a | b;
  cout << "Line 2 - Value of c is: " << c << "[" << binrep(c, buff, SZ) << "]" << endl;

  c = a ^ b;
  cout << "Line 3 - Value of c is: " << c << "[" << binrep(c, buff, SZ) << "]" << endl;

  c = ~a;
  cout << "Line 4 - Value of c is: " << c << "[" << binrep(c, buff, SZ) << "]" << endl;

  c = a << 2;
  cout << "Line 5 - Value of c is: " << c << "[" << binrep(c, buff, SZ) << "]" << endl;

  c = a >> 2;
  cout << "Line 6 - Value of c is: " << c << "[" << binrep(c, buff, SZ) << "]" << endl;

  return 0;
}

static char* binrep(unsigned int val, char* buff, int sz) {
  char *pbuff = buff;

  // Must be able to store one character at least.
  if (sz < 1) return NULL;

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
    if (sz-- == 0) return NULL;
    *pbuff-- = ((val & 1) == 1) ? '1' : '0';

    // Get next bit.
    val >>= 1;
  }

  return pbuff + 1;
}
