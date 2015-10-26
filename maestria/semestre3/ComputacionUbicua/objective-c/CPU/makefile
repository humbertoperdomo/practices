CC = gcc
RM:=rm -rf
SOURCES=src/*.m
HEADERS:=-L/usr/GNUstep/System/Library/Libraries
LIBRARIES:=-lgnustep-base -lobjc
CFLAGS=-Wall -Werror -g -v $(HEADERS) $(LIBRARIES) $(SOURCES)
LDFLAGS=`gnustep-config --objc-flags`
OUT=-o Debug/main

all:
	$(CC) $(LDFLAGS) $(CFLAGS) $(OUT)