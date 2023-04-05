CFLAGS=-Wall -std=c99
CC=gcc
JC=javac
BUILD_DIR=build
SRC=src
TEST_DIR=test
TEST = TestFish TestView

.PHONY: clean 

all : build

build : build_directory server client 

test: ex_test_model ex_test_view ex_test_aquarium ex_java_test

server: $(SRC)/controller/parser.o build_directory copy_controller
	${CC} ${CFLAGS} ${SRC}/controller/server.c $< -o ${BUILD_DIR}/server

client: build_directory copy_view
	${JC} ${SRC}/view/*.java -d ${BUILD_DIR} -cp ${BUILD_DIR} 

copy_view: 
	cp ${SRC}/view/view.cfg	${BUILD_DIR}/

copy_controller:
	cp ${SRC}/controller/controller.cfg	${BUILD_DIR}/

build_directory: 
	mkdir -p build

clean:
	rm -rf  ${BUILD_DIR} *.o

#------------- creation fichiers .o --------------

%.o : %.c
	${CC} ${CFLAGS} -c $< -o $@

model.o: ${SRC}/model/model.c
	${CC} ${CFLAGS} ${SRC}/model/model.c -c

view.o: ${SRC}/model/view.c
	${CC} ${CFLAGS} ${SRC}/model/view.c -c


aquarium.o: ${SRC}/model/aquarium.c
	${CC} ${CFLAGS} ${SRC}/model/aquarium.c -c

#-------------- tests -------------------
test_model: build_directory model.o test_model.o
	${CC} ${CFLAGS} model.o test_model.o -o ${BUILD_DIR}/$@ 

test_view: $(TEST_DIR)/test_view.c view.o build_directory
	$(CC) $(CFLAGS) view.o $(TEST_DIR)/test_view.c -o $(BUILD_DIR)/$@ 

test_aquarium: build_directory model.o view.o aquarium.o test_aquarium.o
	${CC} ${CFLAGS} model.o view.o aquarium.o test_aquarium.o -o ${BUILD_DIR}/$@ 


test_model.o: ${TEST_DIR}/test_model.c
	${CC} ${CFLAGS} ${TEST_DIR}/test_model.c -c

test_aquarium.o: ${TEST_DIR}/test_aquarium.c
	${CC} ${CFLAGS} ${TEST_DIR}/test_aquarium.c -c



java_test: client
	$(JC) -d $(BUILD_DIR) -cp $(BUILD_DIR) $(TEST_DIR)/*.java


ex_java_test:: java_test
	java -ea -cp $(BUILD_DIR) LancerTest $(TEST)

ex_test_model: test_model
	./${BUILD_DIR}/test_model

ex_test_view: test_view
	./$(BUILD_DIR)/test_view
	
ex_test_aquarium: test_aquarium
	./$(BUILD_DIR)/test_aquarium
