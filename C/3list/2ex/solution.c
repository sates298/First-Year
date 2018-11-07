#include"funs.h"
#include<math.h>

double solution(double a, double b, double eps)
{
  while(b-a>eps)
  {
    if(f(a)*f(a+(b-a)/2)<0)
    {
      b=a+(b-a)/2;
    }
    else
    {
      a=a+(b-a)/2;
    }
  }

  return a;
}
