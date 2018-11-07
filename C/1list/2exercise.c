#include<stdio.h>
#include<math.h>

int main()
{
  float a, b, c, delta, x1, x2;

  printf("declare a coefficient a: \n");
  scanf("%f", &a);
  printf("declare a coefficient b: \n");
  scanf("%f", &b);
  printf("declare a coefficient c: \n");
  scanf("%f", &c);

  delta=b*b-(4*a*c);
  if(delta==0)
  { 
    x1=((-1)*b)/(2*a);
    printf("score is equal %f \n", x1);
  }
  else if(delta<0)
    printf("score is not real number \n"); 
  else
  {
    x1=((-1)*b - sqrtf(delta))/(2*a);
    x2=((-1)*b + sqrtf(delta))/(2*a);
    printf("scores are equal %f and %f \n", x1, x2);
  }


  return 0;
}
