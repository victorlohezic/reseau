CFLAGS=-Wall -std=c99
CC=gcc
JC=javac
BUILD_DIR=build
SRC=src
TEST_DIR=tst

.PHONY: clean 

all : build

build : build_directory server client

server: build_directory
	${CC} ${CFLAGS} ${SRC}/controller/server.c  -o ${BUILD_DIR}/server

client: build_directory
	${JC} ${SRC}/view/Client.java -d ${BUILD_DIR} -cp ${BUILD_DIR} 

build_directory: 
	mkdir -p build

clean:
	rm -rf  ${BUILD_DIR}
