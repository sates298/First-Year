/*
@title: zadanie w C++
@author: Stanisław Woźniak
@version: 0.23541
*/

#include<iostream>
using namespace std;

class LiczbyPierwsze{  //klasa
  public:
    int *linia;
    int dlugosc;
    LiczbyPierwsze(int n);
    ~LiczbyPierwsze();
    int pierwsza(int x);
    int liczba(int m);
};

class MojWyjatek : public exception{  //wyjątek

  public:
    const char* wypisz() const throw(){
      return " -za mała liczba\n";

    }
    const char* wypisz2() const throw(){
      return " -liczba spoza zakresu\n";

    }
};

class ZlyFormat: public exception{   //inny wyjątek

  public:
    const char* drukuj() const throw(){
      return " - zły format\n";
    }
};

LiczbyPierwsze::LiczbyPierwsze(int n){  //konstruktor
  int temp[n];

  dlugosc=0;
  for(int i=2; i<=n; i++){
    if(pierwsza(i)){
      temp[dlugosc] = i;
      dlugosc++;
    }
  }

  this -> linia = new int[dlugosc];

  for(int i=0; i<dlugosc; i++){
    this -> linia[i] = temp[i];
  }
//  this -> linia[dlugosc] = '\0';
}

LiczbyPierwsze::~LiczbyPierwsze(){  //dekonstruktor
  delete[] linia;
}

int LiczbyPierwsze::liczba(int m){  //zwraca m-ty element linii
    return linia[m];
}

int LiczbyPierwsze::pierwsza(int x){  //zwraca "pierwszość" liczby

  for(int i = 2; i*i <= x; i++){
    if(x%i == 0){
      return false;
    }
  }
  return true;
}

int main(int argc, char* argv[]){

  if(argc == 1){
    cout << "napisz coś!!" << endl;
    return 0;
  }
  char *p;
  int x;
  try{

    strtol(argv[1], &p, 10);   //sprawdzanie czy to liczba
    if(*p != 0){
      ZlyFormat z;
      throw z;
    }

    x = atoi(argv[1]);
    if(x < 2){
      MojWyjatek a;
      throw a;
    }

  LiczbyPierwsze liczbyPierwsze = LiczbyPierwsze(x);

  for(int i=2; i<argc; i++){

    try{
      strtol(argv[i], &p, 10);   //sprawdzanie czy to liczba
      if(*p != 0){
        ZlyFormat zl;
        throw zl;
      }
      if(atoi(argv[i]) < 0 || atoi(argv[i]) >= liczbyPierwsze.dlugosc){  //sprawdzanie zakresu
        MojWyjatek b;
        throw b;
      }
      cout << argv[i] << " - " << liczbyPierwsze.liczba(atoi(argv[i])) << '\n';
    }catch(MojWyjatek ex){
      cout << argv[i] << ex.wypisz2();
    }catch(ZlyFormat e){
      cout << argv[i] << e.drukuj();
    }catch(...){
      cout << argv[i] << " - zła dana\n";
    }
  }

  }catch(MojWyjatek e){
    cout << x << e.wypisz();
  }catch(ZlyFormat ex){
    cout << argv[1] << ex.drukuj();
  }catch(...){
    cout << " zła dana" << endl;
  }

  return 0;
}
