#include <stdio.h>

#define MAXLINE 1000
#define TABSTOP 8 

int getLine(char[], int);

int main() {
  int len;
  char line[MAXLINE];

  while ((len = getLine(line, MAXLINE)) > 0) {
    printf("%s", line);
  }
  
  return 0;
}

int getLine(char s[], int lim) {
  int c, i, j;

  for (i = 0; i < lim - 1 && (c = getchar()) != EOF && c != '\n'; ++i) {
    if (c == '\t') {
      for (j = (TABSTOP - (i%TABSTOP));j > 0; --j){
        s[i] = ' ';
        ++i;
      }
      --i;
    } else
      s[i] = c;
  }

  if (c == '\n') {
    s[i] = c;
    ++i;
  }
  s[i] = '\0';
  return i;
}
