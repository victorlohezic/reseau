CFLAGS=-Wall -std=c99
CC=gcc
JC=javac
BUILD_DIR=build
SRC=src
TEST_DIR=test
TEST = TestFish

.PHONY: clean 

all : build

build : build_directory server client 

test: test_model




server: build_directory
	${CC} ${CFLAGS} ${SRC}/controller/server.c  -o ${BUILD_DIR}/server

client: build_directory 
	${JC} ${SRC}/view/*.java -d ${BUILD_DIR} -cp ${BUILD_DIR} 

test: ex_java_test test_model

build_directory: 
	mkdir -p build

clean:
	rm -rf  ${BUILD_DIR} *.o



#------------- creation fichiers .o --------------

model.o: ${SRC}/model/model.c
	${CC} ${CFLAGS} ${SRC}/model/model.c -c





#-------------- tests -------------------
test_model: build_directory model.o test_model.o
	${CC} ${CFLAGS} model.o test_model.o -o ${BUILD_DIR}/$@ 


test_model.o: ${TEST_DIR}/test_model.c
	${CC} ${CFLAGS} ${TEST_DIR}/test_model.c -c

java_test: client
	$(JC) -d $(BUILD_DIR) -cp $(BUILD_DIR) $(TEST_DIR)/*.java

ex_java_test:: java_test
	java -ea -cp $(BUILD_DIR) LancerTest $(TEST)