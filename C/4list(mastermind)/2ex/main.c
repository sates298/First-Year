#include"mastermind.h"

int main()
{
  int white = 0, black = 0;
  list* first;
  int array[FIELDS];
  first = malloc(sizeof(list));
  list* temp = first;

  add(temp);  //fill the list

  while(1){

    white = 0;
    black = 0;

    if(temp == NULL){
      printf("You are cheating!! \n");
      break;
    }

    change(temp, array);
    write(array);

    printf("white pins: ");
    scanf("%d", &white);
    printf("black pins: ");
    scanf("%d", &black);

/*
    if(black + white > FIELDS){
      printf("You are cheating!! \n");
      break;
    }
*/
    if(black == FIELDS && white == 0){
      printf("YEAH I won!! \n");
      break;
    }

    temp = removeAll(white, black, temp);

  }

  free(first);

  return 0;
}
