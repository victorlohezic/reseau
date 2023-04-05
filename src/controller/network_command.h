#ifndef _NETWORK_COMMAND_H_
#define _NETWORK_COMMAND_H_

#include <sys/types.h>
#include <sys/socket.h>
#include <string.h>
#include <unistd.h>

#include "aquarium.h"

int hello(char* command, int socket, struct aquarium* a, int* clients);

void log_out(int socket);

int id_is_available(int id, struct aquarium* a, int* client);

#endif