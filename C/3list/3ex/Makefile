#Makefile

all: main

main: main.o nwd.o coprime.o phi.o
	clang -Wall --pedantic -std=c11 -o main main.o nwd.o coprime.o phi.o

main.o: main.c
	clang -c main.c

nwd.o: nwd.c
	clang -c nwd.c

coprime.o: coprime.c
	clang -c coprime.c

phi.o: phi.c
	clang -c phi.c

clean:
	rm -f main *.o *~

