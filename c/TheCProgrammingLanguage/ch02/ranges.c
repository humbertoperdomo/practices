#include <stdio.h>
#include <limits.h>
#include <float.h>

main() {
  printf("Type\tMin\t\t\tMax\t\t\tUnSign\n");
  printf("char\t%d\t\t\t%d\t\t\t%d\n", CHAR_MIN, CHAR_MAX, UCHAR_MAX);
  printf("sohrt\t%d\t\t\t%d\t\t\t%d\n", SHRT_MIN, SHRT_MAX, USHRT_MAX);
  printf("int\t%d\t\t%d\t\t%ld\n", INT_MIN, INT_MAX, UINT_MAX);
  printf("long\t%ld\t%ld\t%lu\n", LONG_MIN, LONG_MAX, ULONG_MAX);
  printf("float\t%f\t%f\n", FLT_MIN, FLT_MAX);
  printf("double\t%lf\t%lf\n", DBL_MIN, DBL_MAX);
  printf("\a");
}
