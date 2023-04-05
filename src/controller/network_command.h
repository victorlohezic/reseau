#ifndef _NETWORK_COMMAND_H_
#define _NETWORK_COMMAND_H_

#include <sys/types.h>
#include <sys/socket.h>

#include "aquarium.h"

void hello(char* command, int socket);

void log_out(int socket);

#endif