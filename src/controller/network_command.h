#ifndef _NETWORK_COMMAND_H_
#define _NETWORK_COMMAND_H_

#include <sys/types.h>
#include <sys/socket.h>
#include <string.h>
#include <unistd.h>

#include "client.h"

int hello(char* command, int socket, struct client_set* clients);

void log_out(int socket);

void ping(char* command, int socket);

#endif