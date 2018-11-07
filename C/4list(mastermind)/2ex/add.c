#include"mastermind.h"

void add(list* first){

  list* ptr;
  list* temp;
  temp = first;
  for(int i = 0; i < power(FIELDS); i++){
    ptr = malloc(sizeof(list));
    ptr -> code = i;
    ptr -> next = NULL;
    temp -> next = ptr;
    temp = ptr;
  }

}

