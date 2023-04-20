#ifndef _PROMPT_COMMAND_H_
#define _PROMPT_COMMAND_H_

#include <string.h>
#include <unistd.h>

#include "aquarium.h"

void prompt_add_view(char* command, struct aquarium* controller_aquarium);

void prompt_del_view(char* command, struct aquarium* controller_aquarium);

#endif