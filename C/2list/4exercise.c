#include<stdio.h>
#include<math.h>
#include<stdbool.h>
#include<stdlib.h>

bool prime(long int a)
{
  long int i=2;
  while(i*i<=a)
  {
    if(a%i==0)
      return false;
    i++;
  }
  return true;
}

long double function(long int n,long int howmany)
{
  long double a;
  a=(howmany*log(n))/n;

  return a;
}


int main()
{
  long int howmany=0;
  long int n=100000;
  long int i=2;
  printf("write a number n: \n");
  //scanf("%ld", &n);

  FILE *fp;
  fp=fopen("4ex.txt", "w");
  while(i<=n)
  {
    if(prime(i))
      howmany++;
    printf("%ld ; %Lf\n", i, function(i,howmany));
    if(i<=1000)
      fprintf(fp, "%ld ; %Lf\n", i, function(i,howmany));
    i++;
  }
  fclose(fp);

  return 0;
}

