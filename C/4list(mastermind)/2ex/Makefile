#Makefile

all: main

main: main.o add.o power.o change.o removeOne.o write.o removeAll.o
	clang -Wall --pedantic -o main -std=c11 main.o add.o power.o change.o removeOne.o write.o removeAll.o

main.o: main.c mastermind.h
	clang -c main.c

add.o: add.c mastermind.h
	clang -c add.c

power.o: power.c mastermind.h
	clang -c power.c

change.o: change.c mastermind.h
	clang -c change.c

write.o: write.c mastermind.h
	clang -c write.c

removeOne.o: removeOne.c mastermind.h
	clang -c removeOne.c

removeAll.o: removeAll.c mastermind.h
	clang -c removeAll.c

clean:
	rm -f main *.o *~
