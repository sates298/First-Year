#include"mastermind.h"

void removeOne(struct list** pointer){

  list* temp = *pointer;
  *pointer = (*pointer) -> next;
  free(temp);

}
