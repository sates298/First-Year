#include "mastermind.h"

struct list* removeAll(int white, int black, list* pointer){

  list* ptr;
  list* temp;
  list* first = pointer;
  int array[FIELDS];
  int temparray[FIELDS];
  int replacedarray[FIELDS];
  int sumblack, missingcolours;
  int match = 0, empty = FIELDS - (black + white);

  change(pointer, array);

  if(pointer -> next == NULL){
    ptr = pointer;
    pointer = pointer -> next;
    free(ptr);
    return pointer;
  }

  temp = pointer;
  while(temp -> next != NULL){       //checking compatible codes
    sumblack = 0;
    missingcolours = 0;
    change(temp -> next, temparray);

    for(int i = 0; i < FIELDS; i++){
      replacedarray[i] = array[i];
    }

    for(int i = 0; i < FIELDS; i++){   //counting the same colours on the same fields
      if(array[i] == temparray[i]){
        sumblack++;
      }
    }

    for(int i = 0; i < FIELDS; i++){    //conting wrong colours
      for(int j = 0; j < FIELDS; j++){
        if(temparray[i] == replacedarray[j]){
          replacedarray[j] = replacedarray[j] + COLOURS;
          break;
        }
        if(j == FIELDS - 1){
          missingcolours++;
        }
      }
    }

    if(black == sumblack  && missingcolours == empty){
      temp = temp -> next;
      match++;
      if(match == 1){
        pointer = temp;
      }
    }
    else{
      removeOne(&(temp -> next));
    }
  }

  if(first == pointer){  //checking the change head of the list
    return NULL;
  }

  return pointer;

}
