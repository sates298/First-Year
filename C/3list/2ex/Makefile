#Makefile

all: main

main: main.o function.o solution.o
	clang -Wall --pedantic -std=c11 -lm -o main main.o function.o solution.o

main.o: main.c
	clang -c main.c

function.o: function.c
	clang -c function.c

solution.o: solution.c
	clang -c solution.c

clean:
	rm -f main *.o *~
