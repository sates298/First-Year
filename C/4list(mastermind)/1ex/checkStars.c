#include "match.h"

int checkStars(char* string)
{
  int i = 0, sum = 0;
  while(string[i] != '\0'){
    if(string[i] == '*'){
      sum++;
    }
    i++;
  }

  return sum;
}
