#include"mastermind.h"

void change(list* pointer, int* array){

  int val = pointer -> code;
  for(int i = 0; i < FIELDS; i++){
    array[i] = (val / power(FIELDS - (i+1))) + 1;
    val = val - ((array[i] - 1) * power(FIELDS - (i+1)));
  }

}
