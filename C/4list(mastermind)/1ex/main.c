#include"match.h"

int main()
{
  char chain[256];
  char pattern[256];
  printf("write a pattern: ");
  scanf("%s", pattern);
  printf("write an inscription: ");
  scanf("%s", chain);

  if(match(pattern, chain)){
    printf("inscription is compatible with pattern \n");
  }
  else{
    printf("inscription is not compatible with pattern \n");
  }

  return 0;
}
