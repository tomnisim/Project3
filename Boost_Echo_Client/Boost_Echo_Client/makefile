CFLAGS:=-c -Wall -Weffc++ -g -std=c++11 -Iinclude
LDFLAGS:=-lboost_system

all: BGRSClient
	g++ -o bin/BGRSClient bin/connectionHandler.o bin/BGRSClient.o $(LDFLAGS)

EchoClient: bin/connectionHandler.o bin/BGRSClient.o
	
bin/connectionHandler.o: src/connectionHandler.cpp
	g++ $(CFLAGS) -o bin/connectionHandler.o src/connectionHandler.cpp

bin/BGRSClient.o: src/BGRSClient.cpp
	g++ $(CFLAGS) -o bin/BGRSClient.o src/BGRSClient.cpp
	
.PHONY: clean
clean:
	rm -f bin/*
