#include "network_command.h"

int hello(char* command, int socket, struct client_set* clients) {

    if (strstr(command, "hello") != command) {
        write(socket, "no greeting\n", 12);
        return 0;
    }

    int id_view;
    
    if (((id_view = find_client(clients, socket)) != 0)) {
        write(socket, "no greeting\n", 12);
        return id_view;
    }

    struct client_info new_client;
    char answer[20] = "greeting N";
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
        write(socket, "no greeting\n", 12);
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

void failed_network_command(int socket) {
    write(socket, "NOK\n", 4);
    printf("> NOK\n");
}

void network_add_fish(char* command, int socket, struct client_set* clients) {
    char fish_name[SIZE_NAMES];
    int width, height, x, y; 
    
    char movement_name[SIZE_NAMES];
    if (sscanf(command, "addFish %s at %dx%d,%dx%d, %s\n", fish_name, &x, &y, &width, &height, movement_name) != 6) {
        failed_network_command(socket);
        return ;
    }
    int id_view = find_client(clients, socket);
    int k = find_view(clients->client_aquarium, id_view);
    if (k < 0) {
        failed_network_command(socket);
        return;
    }
    struct view v_client = clients->client_aquarium->views[k];
    int* view_position = get_view_position(&v_client);
    int* view_dimension = get_view_dimension(&v_client);
    x = view_position[0] + x * view_dimension[0] / 100;
    y = view_position[1] + y * view_dimension[1] / 100;
    struct fish new_fish;
    void (*movement)(int*, int*);
    movement = get_move_function(movement_name);
    init_fish(&new_fish, fish_name, width, height, x, y, movement);

    if (add_fish(clients->client_aquarium, &new_fish) == -1) {
        failed_network_command(socket);
        return ;
    }

    write(socket, "OK\n", 3);
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

    write(socket, "OK\n", 3);
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

    write(socket, "OK\n", 3);
    printf("> OK\n");
}

void send_fish(int socket, struct fish *f, const int* view_position, const int* view_dimension) {
    char answer[256];
    int x, y;
    int time = 0;
    char* fish_name = get_fish_name(f);
    int future_position[3];
    if (next_future_position(f, future_position) == -1) {
        x = get_fish_position(f)[0];
        y = get_fish_position(f)[1];
    } else {
        x = future_position[0];
        y = future_position[1];
        time = future_position[2];

    }
    int* dimension = get_fish_dimension(f);

    x = 100 * (x - view_position[0])/view_dimension[0];
    y = 100 * (y - view_position[1])/view_dimension[1];
    sprintf(answer, " [%s at %dx%d,%dx%d,%d]", fish_name, x, y, dimension[0], dimension[1], time);
    write(socket, answer, strlen(answer));
    printf("%s", answer);
}

void network_get_fishes(int socket, struct client_set* clients) {
    write(socket, "list", 4);
    printf("> list");
    int id_view = find_client(clients, socket);
   
 
    int k = find_view(clients->client_aquarium, id_view);
    if (k < 0) {
        failed_network_command(socket);
        return;
    }
    struct view v_client = clients->client_aquarium->views[k];
    const int* view_position = get_view_position(&v_client);
    const int* view_dimension = get_view_dimension(&v_client);
   
    struct fish* list_fishes[MAX_FISHES];
    int nb_fishes = fishes_in_view(clients->client_aquarium, list_fishes, id_view);
    for (int i = 0; i < nb_fishes; ++i) {
        send_fish(socket, list_fishes[i], view_position, view_dimension);
    }
    printf("\n");    
    write(socket, "\n", 1);
}

void get_fishes_continuously(int socket, struct client_set* clients) {
    int id_view = find_client(clients, socket);
    start_get_fishes_continuously(clients, id_view);
}
