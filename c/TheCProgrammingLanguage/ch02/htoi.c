#include <stdio.h>
#define LENGHTNUMBER 256

int getLine(char[], int);
int htoi(char[]);
int upper(int);
int main() {
  int i;
  char num[LENGHTNUMBER];
  printf("Enter a number: ");

  getLine(num, LENGHTNUMBER);
  
  i = htoi(num);
  printf("The value entered is %d. Its double is %d.\n", i, i*2);

  return 0;
}

int getLine(char s[], int lim) {
  int c, i;

  for (i = 0; i < lim - 1 && (c = getchar()) != EOF && c != '\n'; ++i)
    s[i] = c;

  s[i] = '\0';
  
  return i;
}

int htoi(char s[]) {
  int i, n, x, f;
  
  n = 0;
  for (i = 0; (s[i] >= '0' && s[i] <= '9') || 
        (upper(s[i]) >= 'A' && upper(s[i])<= 'F')

        ; ++i){
    x = upper(s[i]) - '0';
    if (x > 9)
      x = x - 7;
    n = 16 * n + x; 
  }

  return n;
}

int upper(int c) {
  if (c >= 'a' && c <= 'f'){
    return c - ('a' - 'A');}
  else
    return c;
}
