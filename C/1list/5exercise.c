#include<stdio.h>

int main ()
{
  int n;
  scanf("%d", &n);

  for(int i=n; i>0; i--)
  {
    for(int j=0; j<n-i; j++)
    {
      printf(" ");
    }
    int m=2*i-1;
    for(int j=0; j<m; j++)
    {
      printf("*");
    }
    printf("\n");
  }

  return 0;
}
