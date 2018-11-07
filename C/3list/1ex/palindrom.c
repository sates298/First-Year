#include<string.h>
#include<stdbool.h>
#include"funs.h"

bool palindrom(char inscription[])
{
  int n=strlen(inscription)-1;
  for(int i=0; i<n-i; i++)
  {
    if(inscription[i]!=inscription[n-i])
      return false;
  }
  return true;
}
