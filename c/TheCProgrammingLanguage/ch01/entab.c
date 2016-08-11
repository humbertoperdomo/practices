#include <stdio.h>

#define MAXLINE 1000
#define TABSTOP 8 

int getLine(char[], int);

int main() {
  int len;
  char line[MAXLINE];
  printf("TABSTOP = %d\n", TABSTOP);
  while ((len = getLine(line, MAXLINE)) > 0) {
    printf("%s", line);
  }
  
  return 0;
}

int getLine(char s[], int lim) {
  int c, i, j, k, e;
  
  j = e = 0;
  for (i = 0; i < lim - 1 && (c = getchar()) != EOF && c != '\n'; ++i) {
    if ((i%TABSTOP) == 0) {
      if (j > 0) {
        s[e++] = '\t';
        j = 0;
      } 
      if (c == ' ')
        ++j;
      else
        s[e++] = c;
    } else if (c == ' ') {
      ++j;
    } else {
      for(k = (i - j); k < i; ++k)
        s[e++] = ' ';
      s[e++] = c;
      j = 0;
    }
  }
  
  if (c == '\n') {
    s[e++] = c;
  }
  s[e] = '\0';
  return i;
}
