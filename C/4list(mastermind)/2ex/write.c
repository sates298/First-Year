#include"mastermind.h"

void write(int* array){

  for(int i = 0; i < FIELDS; i++){
    printf("[%d] ", array[i]);
  }
  printf("\n");

}

