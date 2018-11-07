#Makefile

all: main

main: main.o match.o change.o reverse.o checkStars.o
	clang -Wall --pedantic -std=c11 -o main main.o match.o change.o reverse.o checkStars.o

match.o: match.c match.h
	clang -c match.c

change.o: change.c match.h
	clang -c change.c

reverse.o: reverse.c match.h
	clang -c reverse.c

checkStars.o: checkStars.c match.h
	clang -c checkStars.c

clean:
	rm -f main *.o *~ 
