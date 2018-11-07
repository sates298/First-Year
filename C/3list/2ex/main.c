#include<stdio.h>
#include<math.h>
#include "funs.h"

int main()
{
  double eps,a,b;
  printf("declare a: ");
  scanf("%lf", &a);
  printf("declare b: ");
  scanf("%lf", &b);
  printf("declare epsilon>0: ");
  scanf("%lf", &eps);

  double x= solution(a,b,eps);

  printf("solution: x=%lf \n", x);

  return 0;
}
