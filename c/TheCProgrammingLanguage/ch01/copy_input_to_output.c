#include <stdio.h>

main() {
  int c, comp;

  while(comp = ((c = getchar()) != EOF)){
    putchar(c);
  }
  putchar(c); 
  printf("\n%d\n", comp);
}
