#include"funs.h"
#include<stdio.h>
#include<stdbool.h>
#include<stdlib.h>
#include<string.h>

int main()
{
  char inscription[100];
  scanf("%s", inscription);

  if(palindrom(inscription))
    printf("%s - is a palindrom \n", inscription);
  else
    printf("%s - is not a palindrom \n", inscription);

  return 0;
}
