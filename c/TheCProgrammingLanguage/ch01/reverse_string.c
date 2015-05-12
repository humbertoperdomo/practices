#include <stdio.h>

#define MAXLINE 1000
int getLine(char[], int);
void copy(char[], char[]);
void reverse(char[], char[], int);

main() {
  int len;
  char line[MAXLINE];
  char reversed[MAXLINE];

  while ((len = getLine(line, MAXLINE)) > 0) {
    reverse(reversed, line, len);
    printf("%s\n", reversed);
  }
  return 0;
}

int getLine(char s[], int lim) {
  int c, i;

  for (i = 0; i < lim - 1 && (c = getchar()) != EOF && c != '\n'; ++i)
    s[i] = c;
  if (c == '\n') {
    s[i] = c;
    ++i;
  }

  s[i] = '\0';
  return i;
}

void reverse(char reversed[], char original[], int size) {
  int i;
  i = 0;
  while (size >= 0) 
    if ((reversed[i++] = original[--size]) == '\n')
      i--;
}
