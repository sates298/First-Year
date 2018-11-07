#include "funs.h"

int phi(long int n)
{
  int sum=0;
  for(long int i=1; i<=n; i++)
  {
    if(coprime(n,i))
    {
      sum++;
    }
  }
  return sum;
}
