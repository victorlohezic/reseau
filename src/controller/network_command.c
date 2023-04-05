#include "network_command.h"

int id_is_available(int id, struct aquarium* a, int* client) {
    if (id <= 0 || id >= MAX_VIEWS || client[id] == 1 || !find_view(a, id)){
        return 0;
    }
    return 1;
}



int hello(char* command, int socket, struct aquarium* a, int* clients) {
    char answer[20] = "greeting ";
    char id[3] = {'\0'};
    char copy[30];
    strcpy(copy, command);
    char* token = strtok(copy, " "); // split the command with whitespace delimiter
    while (token != 0) {
        if (strcmp(token, "in") == 0) {     
            token = strtok(NULL, " ");
            strcpy(id, ++token);
            break;
        }
        token = strtok(NULL, " ");    
    }
    
    if (id_is_available(atoi(id), a, clients)) {
        strcat(answer, id);
        write(socket, answer, strlen(answer));
        return atoi(id);
    }
    write(socket, answer, strlen(answer));
    return -1;
}

void log_out(int socket) {
    char* answer = "bye\n";
    write(socket, answer, strlen(answer));
}

// int main(){
//     int client[10] = {0};
//     client[3] = 1;
//     client[8] = 1;
//     struct aquarium a;
//     a.dimension[0] = 128;
//     a.dimension[1] = 337;

//     a.nb_fishes = 0;

//     for(int k=0; k<5; k++) {
//         init_view(a.views+k, k, 10, 10, 10, 10);
//     }
//     a.nb_views = 5;

//     // printf("%i\n", hello("hello as in N50", 0, &a, client));
//     // printf("%i\n", hello("hello as in N3", 0, &a, client));
//     // printf("%i\n", hello("hello as in N8", 0, &a, client));
//     printf("%i\n", hello("hello as in N4", 0, &a, client));
//     printf("%i\n", hello("hello", 0, &a, client));


// }