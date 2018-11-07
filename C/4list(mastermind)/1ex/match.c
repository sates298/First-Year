#include"match.h"

bool match(char* pattern, char* chain){

  if(pattern[0] == '\0' && chain[0] == '\0'){
    return true;
  }
  if(pattern[0] == chain[0] || pattern[0] == '?'){
    change(pattern);
    change(chain);
    return match(pattern, chain);
  }
  else if(pattern[0] == '*' && pattern[1] == '\0'){
    return true;
  }
  else if(pattern[0] == '*' && pattern[1] == '*'){
    change(pattern);
    return match(pattern, chain);
  }
  else if(pattern[0] == '*'){
    if(checkStars(pattern) > 1){
      change(pattern);
      int lenght = 0;

      while(pattern[lenght+1] != '*'){ //checking subsequence's lenght
        lenght++;
      }

      int i = 0;
      while(i <= lenght){  //searching that subsequence in chain
        if(chain[0] == '\0'){
          return false;
        }
        if(pattern[i] == chain[0] || pattern[i] == '?'){
          change(chain);
          i++;
        }
        else if(pattern[i] != chain[0]){
          if(i == 0){
            change(chain);
          }
          i = 0;
        }
      }

      while(pattern[0] != '*'){  //remove that subsequence
        change(pattern);
      }

      return match(pattern, chain);
    }
    else
    {
      reverse(pattern);
      reverse(chain);
      return match(pattern,chain);
    }
  }

  return false;
}

