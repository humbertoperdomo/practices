#include <stdio.h>

main() {
  int c, nl = 0;
  while((c = getchar()) != EOF)
    if(c == '\n')
      ++nl;
  printf("Lineas encontradas: %d\n", nl);
}
