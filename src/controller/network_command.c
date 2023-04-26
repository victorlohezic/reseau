#include "network_command.h"
#include "client.h"
#include <stdlib.h>

int hello(char* command, int socket, struct client_set* clients) {

    if (strstr(command, "hello") != command) {
        write(socket, "no greeting\n", 13);
        return 0;
    }

    int id_view;
    
    if (((id_view = find_client(clients, socket)) != 0)) {
        write(socket, "no greeting\n", 13);
        return id_view;
    }

    struct client_info new_client;
    char answer[20] = "greeting ";
    char str_id[4] = {'\0'};
    if (sscanf(command, "Hello in as %d\n", &id_view) == 1) {
        if (is_view_available(clients, id_view)) {
            sprintf(str_id, "%d", id_view); // convert the integer to a string using sprintf
            strcat(answer, str_id);
            strcat(answer, "\n");
            write(socket, answer, strlen(answer));
            printf("> %s", answer);
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
    printf("> %s", answer);
    new_client = init_client_info(socket, id_view);
    add_client(clients, &new_client);
    return id_view;
}

void log_out(int socket, struct client_set* clients) {
    char* answer = "bye\n";
    int id_view = find_client(clients, socket);
    del_client(clients, id_view);
    printf("> %s", answer);
    write(socket, answer, strlen(answer));
    
}

void ping(char* command, int socket) {
    char answer[20] = "pong ";
    char port_number[10];
    if (sscanf(command, "ping %s", port_number) != 1) {
        return;
    }
    strcat(answer, port_number);
    strcat(answer, "\n");
    write(socket, answer, strlen(answer));
    printf("> %s", answer);
}

void parse_fish(char* command, char* name, int* width, int* height, int* x, int* y, void (*shift) (int*)) {
    

}

void failed_network_command(int socket) {
    write(socket, "NOK\n", 5);
    printf("> NOK\n");
}

void network_add_fish(char* command, int socket, struct client_set* clients) {
    char fish_name[SIZE_NAMES];
    int width, height, x, y; 
    char movement[SIZE_NAMES];
    if (sscanf(command, "addFish %s at %dx%d,%dx%d, %s\n", fish_name, &x, &y, &width, &height, movement) != 6) {
        failed_network_command(socket);
        return ;
    }
    struct fish new_fish;
    init_fish(&new_fish, fish_name, width, height, x, y, (void (*)(int*)) movement);

    if (add_fish(clients->client_aquarium, &new_fish) == -1) {
        failed_network_command(socket);
        return ;
    }

    write(socket, "OK\n", 4);
    printf("> OK\n");
}

void network_start_fish(char* command, int socket, struct client_set* clients) {
    char fish_name[SIZE_NAMES];

    if (sscanf(command, "startFish %s\n", fish_name) != 1) {
        failed_network_command(socket);
        return ;
    }

    if (start_fish_aquarium(clients->client_aquarium, fish_name) == -1) {
        failed_network_command(socket);
        return ;
    }

    write(socket, "OK\n", 4);
    printf("> OK\n");
}

void network_del_fish(char* command, int socket, struct client_set* clients) {
    char fish_name[SIZE_NAMES];

    if (sscanf(command, "delFish %s\n", fish_name) != 1) {
        failed_network_command(socket);
        return ;
    }

    if (del_fish(clients->client_aquarium, fish_name) == -1) {
        failed_network_command(socket);
        return ;
    }

    write(socket, "OK\n", 4);
    printf("> OK\n");
}

void send_fish(int socket, struct fish *f) {
    char answer[256];
    char* fish_name = get_fish_name(f);
    int* position = get_fish_position(f);
    int* dimension = get_fish_dimension(f);
    sprintf(answer, " [%s at %dx%d,%dx%d,%d]", fish_name, position[0], position[1], dimension[0], dimension[1], 5);
    write(socket, answer, strlen(answer));
    printf("%s", answer);
}

void network_get_fishes(int socket, struct client_set* clients) {
    write(socket, "list", 5);
    printf("> list");
    int id_view = find_client(clients, socket);
    struct fish list_fishes[MAX_FISHES];
    int nb_fishes = fishes_in_view(clients->client_aquarium, list_fishes, id_view);
    for (int i = 0; i < nb_fishes; ++i) {
        send_fish(socket, &list_fishes[i]);
    }
    printf("\n");    
    write(socket, "\n", 2);
}

void get_fishes_continuously(int socket, struct client_set* clients) {
    int id_view = find_client(clients, socket);
    start_get_fishes_continuously(clients, id_view);
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