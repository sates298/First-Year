#include <stdio.h>

int main ()
{
  float a,b,c;
  scanf("%f", &a);
  scanf("%f", &b);

  c=a+b;
  printf("amount = %f \n", c);
  c=a-b;
  printf("diff = %f \n", c);
  c=a*b;
  printf("product = %f \n", c);
  c=a/b;
  printf("quotient = %f \n", c);
 
  return 0;
}
