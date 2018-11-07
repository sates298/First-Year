
#export JAVA_HOME=/usr/lib/jvm/java1.8.0_151
#zabijanie procesu 1099
fuser -k 1099/tcp

#kompilowanie klas
find -name "*.java" > nazwyKlasDoKompilowania.txt
javac @nazwyKlasDoKompilowania.txt
#/eof kompilowanie klas

#posrednik
cd src
rmic server.Server
cd -
#eof posrednik

#odpalanie rejestru
cd src
rmiregistry &
cd -
#eof rejestr


#odpalenie serwera
cd src
#xterm -e 
java server.Server &
#eof odpalenie serwera

#odpalenie klienta


#xterm -e
 java client.Main 
cd ..
#eof odpalenie klienta

