#include<stdio.h>

int main()
{
  long double s=0;
  long double n=0;

  while(s<=10)
  {
    n++;
    s=s+(1/n);
  }

  printf("the lowest n is equal %Lf \n", n);

  return 0;
}

