#ifndef _NETWORK_COMMAND_H_
#define _NETWORK_COMMAND_H_

#include <sys/types.h>
#include <sys/socket.h>
#include <string.h>
#include <unistd.h>
#include <stdlib.h>

#include "client.h"
#include "move.h"
#include "logger.h"

int hello(char* command, int socket, struct client_set* clients);

void log_out(int socket, struct client_set* clients);

void ping(char* command, int socket);

void network_add_fish(char* command, int socket, struct client_set* clients);

void network_start_fish(char* command, int socket, struct client_set* clients);

void network_del_fish(char* command, int socket, struct client_set* clients);

void network_get_fishes(int socket, struct client_set* clients);

void get_fishes_continuously(int socket, struct client_set* clients);

#endif