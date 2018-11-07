#include"mastermind.h"

int power(int index){

  int score = 1;
  int i = 0;

  while(i < index){
    score = score*COLOURS;
    i++;
  }

  return score;
}

