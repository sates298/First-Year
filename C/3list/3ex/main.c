#include "funs.h"
#include<stdio.h>

int main()
{
  long int n;
  printf("declare n:");
  scanf("%ld" ,&n);
  int x=phi(n);
  printf("for n=%ld Euler function is equal :%d \n", n, x);

  return 0;
}

