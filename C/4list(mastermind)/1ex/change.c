#include"match.h"

void change(char* string){ //remove first sign in array

  int i = 0;
  while(string[i] != '\0'){
    string[i] = string[i+1];
    i++;
  }

}
