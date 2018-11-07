#include<stdbool.h>
#include "funs.h"

bool coprime(long int a, long int b)
{
  if(nwd(a,b)==1)
  {
      return true;
  }
  return false;
}
