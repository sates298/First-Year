#include "match.h"

void reverse(char* string){

  char temp[256];
  int i = 0, j = 0, k = 0, lenght;

  while(string[i] != '\0'){ //counting array's lenght
    i++;
  }

  lenght = i - 1;
  while(lenght - j >= 0){
    temp[j] = string[lenght-j]; //reverse
    j++;
  }
  temp[j] = '\0';

  while(temp[k] != '\0'){
    string[k] = temp[k];  //replece old array with reversing array
    k++;
  }

}

