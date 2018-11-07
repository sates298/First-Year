#include<stdio.h>
#include<stdlib.h>

int NWD(int a, int b)
{
  int c;
  while(a%b!=0)
  {
    c=a;
    a=b;
    b=c%b;
  }

  return b;
}

double function(int n, double pairs)
{
  return pairs/(n*n);
}

int main()
{
  unsigned int n=1000;
  //scanf("%d", &n);
  double  pairs=0;
  FILE *fp;

  fp=fopen("5ex.txt","w");

  for(int i=1; i<=n; i++)
  {
    pairs=0;
    for(int j=1; j<=i; j++)
    {
      for(int k=1; k<=i; k++)
      {
        if(NWD(j,k)==1)
        {
          pairs++;
        }
      }
    }
//    printf("pairs %lf \n", pairs);
    printf("%d; %lf \n",i ,function(i,pairs));
    fprintf(fp, "%d; %lf \n", i, function(i,pairs));
  }
  fclose(fp);

  return 0;
}

