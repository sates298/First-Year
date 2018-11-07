#include "funs.h"

long int nwd(long int a, long int b)
{
  long int c;
  while(a%b>0)
  {
    c=a;
    a=b;
    b=c%b;
  }
  return b;
}
