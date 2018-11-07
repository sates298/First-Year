
/* Program 3
@author Stanisław Woźniak
@version 1.0
*/
import java.util.*;

public class Task3{

  public static int div(int n){
   /* int max = 1;
    for(int i = 1; i < n/2; i++){
      if(n%i == 0 && i > max){
        max = i;
      }
    }
    return max;
*/
    int i = 2;
    while(i*i <= n){
      if(n%i == 0){
        return n/i;
      }
      i++;
    }
/*
    if(n%i == 0){
      return n/i;
    }*/
    return 1;
  }

  public static void main(String[] args){
    int n;
    for(int i = 0; i < args.length; i++){
      try{
        n=Integer.parseInt(args[i]);
        System.out.println(n + " " + div(n));
      }catch(NumberFormatException ex){
        System.out.println(args[i] + " is not integer number");
      }
    }
  }
}
