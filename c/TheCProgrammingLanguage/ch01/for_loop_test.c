#include <stdio.h>

int main(){
    int i, j;
    for(i = 0; i <  1; i++){
        printf("i = %d\n", i);
        ++i;
        printf("i = %d\n", i);
    }

    for(j = 0; j < 10; ++j)
        printf("j = %d\n", j);
}
