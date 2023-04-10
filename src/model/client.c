#include "client.h"

struct client_info init_client_info(int socket, int id_view) {
    struct client_info client = {socket, id_view};
    return client;
}

int get_socket_client(struct client_info *client) {
    return client->socket_fd;
}

void init_client_set(struct client_set *clients, struct aquarium* client_aquarium) {
    clients->client_aquarium = client_aquarium;
    for (int i = 0; i < MAX_CLIENTS; ++i) {
        clients->clients[i] = init_client_info(0, 0);
    }
}

int find_client(struct client_set *clients, int socket_client) {
    for (int i = 0; i < MAX_CLIENTS; ++i) {
        if (clients->clients[i].socket_fd == socket_client) {
            return clients->clients[i].id_view; 
        }
    }
    return 0;
}
void add_client(struct client_set *clients, struct client_info* client) {
    int id = client->id_view - 1;
    clients->clients[id] = *client;
}

void del_client(struct client_set *clients, struct client_info* client) {
    int id = client->id_view - 1;
    clients->clients[id-1] = init_client_info(0, 0);
}

struct client_info get_client_info(struct client_set *clients, int id_view) {
    return clients->clients[id_view-1];
}

int is_view_available(struct client_set *clients, int id_view) {
    if (id_view <= 0 || 
    id_view > MAX_VIEWS || 
    clients->clients[id_view-1].socket_fd != 0 || 
    !find_view(clients->client_aquarium, id_view)) 
    {   
        return 0;
    } 
    return 1;
}

int find_view_available(struct client_set *clients) {
    for (int i = 0; i < MAX_CLIENTS; ++i) {
        if (clients->clients[i].socket_fd == 0 || find_view(clients->client_aquarium, i+1)) {
            return i+1;
        }
    }
    return 0;
}

