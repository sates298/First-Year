/*
@title: head of mainClass
@author: Stanisław Woźniak
@version: 1.0
*/
#include<string>

using namespace std;

class RomArab {
  private:
     static string numbers[7];
  public:
    static int romToArab(string rome);
    static string arabToRom(int arab);
};
