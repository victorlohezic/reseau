#include "network_command.h"
#include "client.h"
#include <stdlib.h>

int hello(char* command, int socket, struct client_set* clients) {

    if (strstr(command, "hello") != command) {
        write(socket, "say hello first\n", 17);
        return 0;
    }

    struct client_info new_client;
    char answer[20] = "greeting ";
    char str_id[4] = {'\0'};
    char copy[30];
    int id_view;
    strcpy(copy, command);
    char* token = strtok(copy, " "); 
    token = strtok(NULL, " ");
    token = strtok(NULL, " ");
    if (token != NULL) {
        strcpy(str_id, ++token);
        id_view = atoi(str_id);
        printf("loop : %s %s\n", token, command);
        if (is_view_available(clients, id_view)) {
            printf("loop : %s\n", answer);
            strcat(answer, str_id);
            strcat(answer, "\n");
            write(socket, answer, strlen(answer));
            printf("%s\n", answer);
            
            new_client = init_client_info(socket, id_view);
            add_client(clients, &new_client);
            return id_view;
        }
    }
    id_view = find_view_available(clients);
    if (id_view == 0) {
        write(socket, "no greeting\n", 13);
        return 0;
    }
    sprintf(str_id, "%d", id_view); // convert the integer to a string using sprintf
    strcat(answer, str_id);
    strcat(answer, "\n");
    write(socket, answer, strlen(answer));
    printf("Answer : %s\n", answer);
    new_client = init_client_info(socket, id_view);
    add_client(clients, &new_client);
    return id_view;

}

void log_out(int socket) {
    char* answer = "bye\n";
    printf("Answer : %s\n", answer);
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