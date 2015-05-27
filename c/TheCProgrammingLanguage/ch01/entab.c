#include <stdio.h>

#define MAXLINE 1000
#define TABSTOP 7 

int getLine(char[], int);

main() {
  int len;
  char line[MAXLINE];

  while ((len = getLine(line, MAXLINE)) > 0) {
    printf("%s", line);
  }
  
  return 0;
}

int getLine(char s[], int lim) {
  int c, i, j, k;
  
  j = 0;
  for (i = 0; i < lim - 1 && (c = getchar()) != EOF && c != '\n'; ++i) {
    if (c == ' ' && (i%TABSTOP) == 0) {
      if (j > 1) {
        i = i - j;
        s[i] = '\t';
      } else
        s[i] = c;
      j = 0;
    } else if (c == ' ') {
      ++j;
    } else {
      for(k = (i - j); k < i; ++k)
        s[k] = ' ';
      s[i] = c;
      j = 0;
    }
  }
  
  if (c == '\n') {
    s[i] = c;
    ++i;
  }
  s[i] = '\0';
  return i;
}
