#include <stdio.h>

extern int count;
void write_extern(void) {
  count = 5;
  printf("count is %d\n", count);
}
