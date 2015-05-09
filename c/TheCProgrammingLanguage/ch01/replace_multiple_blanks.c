#include <stdio.h>

main() {
  int c;

  while ((c = getchar()) != EOF) {
    if (c == ' ') {
      printf("\\b");
    } else if (c == '\t') { 
      putchar('\\');
      putchar('t');
    } else if (c == '\\') { 
      putchar('\\');
      putchar('\\');
    }else {
      putchar(c);
    }
  }

}
