#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#define MAX_LINE_LENGTH 100

struct ConfigItem{ // Represents one parameters of the controller file
    char key[MAX_LINE_LENGTH];
    char value[MAX_LINE_LENGTH];
} ;

struct Config{ // Contain all of the ConfigItems
    struct ConfigItem* items;
    int count;
} ;

/* Take a file and retrurn the list of all parameters*/
struct Config* parse_controller_config(const char* filename);

/* Used to free all the malloc and realloc made to create the Config struct*/
void free_config(struct Config* config);
