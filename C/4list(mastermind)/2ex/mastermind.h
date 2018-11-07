#include<stdio.h>
#include<stdlib.h>
#define FIELDS 4
#define COLOURS 6

typedef struct list{

  int code;
  struct list* next;

} list;
void add(list* first);
void removeOne(list** pointer);
int power(int index);
void change(list* pointer, int* array);
void write(int* array);
struct list* removeAll(int white, int black, list* pointer);

