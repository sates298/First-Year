#include<stdio.h>

int main()
{
  int t[8]={200, 100, 50, 20, 10, 5, 2, 1};
  int z,g,b,c,n;
  b=0;
  c=0;
  printf("write a number of zloty: ");
  scanf("%d", &z);
  printf("write  number of groszy: ");
  scanf("%d", &g);

  if(g>=100)
  {
    z=z+g/100;
    g=g%100;
  }

  for(int i=0; i<8; i++)
  {
    n=z/t[i];
    if(n>0)
    {
      if(b==0 && i<5)
      {
        printf("notes: \n");
        b++;
      }
      else if(c==0 && i>4)
      {
        printf("coins: \n");
        c++;
      }
      printf("%d x %dzl \n", n, t[i]);
    }
    z=z-(n*t[i]);
    if(z==0)
      break;
  }

  for(int i=2; i<8; i++)
  {
    n=g/t[i];
    if(n>0)
    {
      if(c==0)
        printf("coins: \n");
      printf("%d x %dgr \n", n, t[i]);
      c++;
    }
    g=g-(n*t[i]);
    if(g==0)
      break;
  }

  return 0;
}
