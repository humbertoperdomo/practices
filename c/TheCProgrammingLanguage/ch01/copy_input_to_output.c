#include <stdio.h>

main() {
  int c, comp;

  while(comp = ((c = getchar()) != EOF)){
    putchar(c);
  }
  putchar(EOF); 
  printf("\n%d\n", comp);
}
