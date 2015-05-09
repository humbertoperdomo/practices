#include <stdio.h>

main() {
  int c, nb = 0, nt = 0, nl = 0;

  while((c = getchar()) != EOF)
    if (c == ' ')
      ++nb;
    else if (c == '\t')
      ++nt;
    else if (c == '\n')
      ++nl;
  printf("Blacks: %d\n", nb);
  printf("Tabs: %d\n", nt);
  printf("Lines: %d\n", nl);
}
