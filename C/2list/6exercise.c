#include<stdio.h>

int divisors(int a)
{
  int i=1;
  int sum=0;
  while(i<=a/2)
  {
    if(a%i==0)
      sum+=i;
    i++;
  }
  return sum;
}

int main()
{
  int n=1000;
  int t[n];
  for(int i=0; i<n; i++)
  {
    t[i]=divisors(i+1);
  }

  printf("perfect numbers: \n");
  for(int i=0; i<n; i++)
  {
    if(i+1==t[i])
      printf("%d \n", i+1);
  }

  printf("amicable numbers: \n");
  for(int i=0; i<n; i++)
  {
    for(int j=0; j<i; j++)
    {
      if(t[i]==j+1 && t[j]==i+1)
        printf("%d; %d \n", i+1, j+1);
    }
  }


  return 0;
}
