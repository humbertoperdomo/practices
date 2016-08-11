#include <stdio.h>
#define MAXLINE 1000

int getLine(char line[], int maxline);
void copy(char to[], char from[]);

int main() {
  int len;
  char line[MAXLINE];

  while ((len = getLine(line, MAXLINE)) > 0) {
    if (line[0] != '\n')
      printf("%s", line);
  }
  return 0;
}

int getLine(char s[], int lim) {
  int c, i;

  for (i = 0; i < lim - 1 && (c = getchar()) != EOF && c != '\n'; ++i)
    if (c != '\t' && c != ' ')
      s[i] = c;
    else
      --i;
  if (c == '\n') {
    s[i] = c;
    ++i;
  }
  
  s[i] = '\0';
  return i;
}
