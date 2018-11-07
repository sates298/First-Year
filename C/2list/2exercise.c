#include<stdio.h>

int main()
{
  unsigned int n;
  printf("declare a natural n: ");
  scanf("%d", &n);
  double x,a;
  double s=0;

  for(int i=0; i<n; i++)
  {
    printf("write a real number: ");
    scanf("%lf", &x);
    s=s+x;
  }

  a=s/n;
  printf("average is equal %lf \n", a);


  return 0;
}
