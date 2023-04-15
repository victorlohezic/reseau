CFLAGS=-Wall -std=c99
CC=gcc
JC=javac
BUILD_DIR=build
SRC=src
TEST_DIR=test
TEST = TestFish TestView

.PHONY: clean 

all : build

build : build_directory server server_mock server_mock_GUI client copy_controller

test: ex_test_queue ex_test_model ex_test_view ex_test_aquarium ex_java_test

server: ${SRC}/controller/server.c parser.o network_command.o aquarium.o view.o model.o client.o
	${CC} ${CFLAGS} $^ -I $(SRC)/model -o ${BUILD_DIR}/server

server_mock: ${SRC}/controller/server_mock.c parser.o
	${CC} ${CFLAGS} $^ -I $(SRC)/model -o ${BUILD_DIR}/server_mock

server_mock_GUI: ${SRC}/controller/server_mock_GUI.c parser.o
	${CC} ${CFLAGS} $^ -I $(SRC)/model -o ${BUILD_DIR}/server_mock_GUI

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

%.o : $(SRC)/controller/%.c
	${CC} ${CFLAGS} -c $< -o $@

%.o : $(SRC)/model/%.c
	${CC} ${CFLAGS} -c $< -o $@

model.o: ${SRC}/model/model.c
	${CC} ${CFLAGS} ${SRC}/model/model.c -c

view.o: ${SRC}/model/view.c
	${CC} ${CFLAGS} ${SRC}/model/view.c -c

aquarium.o: ${SRC}/model/aquarium.c
	${CC} ${CFLAGS} ${SRC}/model/aquarium.c -c

network_command.o: $(SRC)/controller/network_command.c
	${CC} ${CFLAGS} -I $(SRC)/model $< -c

#-------------- tests -------------------
test_queue: build_directory queue.o test_queue.o
	${CC} ${CFLAGS} queue.o test_queue.o -o ${BUILD_DIR}/$@ 

test_model: build_directory model.o test_model.o
	${CC} ${CFLAGS} model.o test_model.o -o ${BUILD_DIR}/$@ 

test_view: $(TEST_DIR)/test_view.c view.o build_directory
	$(CC) $(CFLAGS) view.o $(TEST_DIR)/test_view.c -o $(BUILD_DIR)/$@ 

test_aquarium: build_directory model.o view.o aquarium.o test_aquarium.o
	${CC} ${CFLAGS} model.o view.o aquarium.o test_aquarium.o -o ${BUILD_DIR}/$@ 

test_queue.o: ${TEST_DIR}/test_queue.c
	${CC} ${CFLAGS} ${TEST_DIR}/test_queue.c -c

test_model.o: ${TEST_DIR}/test_model.c
	${CC} ${CFLAGS} ${TEST_DIR}/test_model.c -c

test_aquarium.o: ${TEST_DIR}/test_aquarium.c
	${CC} ${CFLAGS} ${TEST_DIR}/test_aquarium.c -c



java_test: client
	$(JC) -d $(BUILD_DIR) -cp $(BUILD_DIR) $(TEST_DIR)/*.java


ex_java_test:: java_test
	java -ea -cp $(BUILD_DIR) LancerTest $(TEST)

ex_test_queue: test_queue
	./${BUILD_DIR}/test_queue

ex_test_model: test_model
	./${BUILD_DIR}/test_model

ex_test_view: test_view
	./$(BUILD_DIR)/test_view
	
ex_test_aquarium: test_aquarium
	./$(BUILD_DIR)/test_aquarium
