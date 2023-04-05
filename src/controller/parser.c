#include <string.h>

#include "parser.h"
       
/* Main function: Take a file and retrurn the list of all parameters*/
struct Config* parse_controller_config(const char* filename) {
    FILE* file = fopen(filename, "r"); // Open the file

    if (!file) { // If we couldn't open the file
        printf("Error: could not open file '%s'\n", filename);
        return NULL;
    }
    
    struct Config* config = (struct Config*) malloc(sizeof(struct Config)); // Initialization of the strcut config
    config->items = NULL;
    config->count = 0;
    
    char line[MAX_LINE_LENGTH];
    while (fgets(line, MAX_LINE_LENGTH, file)) { // For each line

        if (line[0] == '#' || line[0] == '\n') { // If the line is a comment or empty...
            continue;                            // ... we move on to the next line
        }

        char* equals_pos = strchr(line, '='); // Detects the position of the "=" in the line 
        if (!equals_pos) { // If there is no "=" ...
            printf("Error: invalid line in file '%s'\n", filename); // ... an error is printed
            free(config->items);
            free(config);
            fclose(file);
            return NULL;
        }

        struct ConfigItem item;
        strncpy(item.key, line, equals_pos - line); // Copy into the "key" attribute the one read in the file
        item.key[equals_pos - line] = '\0';
        strncpy(item.value, equals_pos + 1, strlen(line) - (equals_pos - line + 1)); // Same for its value
        item.value[strlen(line) - (equals_pos - line + 1) - 1] = '\0';

        config->items = (struct ConfigItem*) realloc(config->items, sizeof(struct ConfigItem) * (config->count + 1));
        config->items[config->count] = item; // Add this item tp the list
        config->count++;
    }
    
    fclose(file);
    return config;
}

/* Used to free all the malloc and realloc made to create the Config struct*/
void free_config(struct Config* config) {
    free(config->items);
    free(config);
}

int get_setting(struct Config* config, char* setting_name) {
    for (int i = 0; i < config->count; i++) {
        char* trimmed_key = strtok(config->items[i].key, " "); // Remove white space
        if (strcmp(setting_name, trimmed_key) == 0) {
            return atoi(config->items[i].value);
        }
    }
    perror("Setting not found");
    exit(1);
}


// int main() {
//     struct Config* config = parse_controller_config("controller.cfg");
//     if (!config) {
//         printf("Error: could not parse config file\n");
//         return 1;
//     }
//     // for (int i = 0; i < config->count; i++) {
//     //     printf("Key: %s, Value: %s\n", config->items[i].key, config->items[i].value);
//     // }
//     printf("Key: %s\n", config->items[0].key);
//     printf("%i\n", strcmp(config->items[0].key, "controller-port"));

//     printf("config->items[0].key: \"%s\"\n", config->items[0].key);
//     printf("\"controller-port\": \"%s\"\n", "controller-port");
//     printf("%i\n", get_setting(config, "controller-port"));
//     free_config(config);
//     return 0;
// }
