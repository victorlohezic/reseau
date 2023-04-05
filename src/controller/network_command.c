#include "network_command.h"

void hello(char* command, int socket) {
    char* answer = "greeting\n";
    send(socket, answer, strlen(answer), 0);
}

void log_out(int socket) {
    char* answer = "bye\n";
    send(socket, answer, strlen(answer), 0);
}