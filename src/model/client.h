#ifndef _CLIENT_H_
#define _CLIENT_H_

#include "aquarium.h"

#define MAX_CLIENTS MAX_VIEWS
#define NULL_CLIENT init_client_info(0, 0);

struct client_info {
    int socket_fd;
    int id_view;
};

struct client_set {
  struct client_info clients[MAX_CLIENTS];
  struct aquarium* client_aquarium;
};

struct client_info init_client_info(int socket, int id_view);

int get_socket_client(struct client_info *client);

void init_client_set(struct client_set *clients, struct aquarium *client_aquarium);

int find_client(struct client_set *clients, int socket_client);

void add_client(struct client_set *clients, struct client_info* client);

void del_client(struct client_set *clients, struct client_info* client);

struct client_info get_client_info(struct client_set *clients, int id_view);

int is_view_available(struct client_set *clients, int id_view);

int find_view_available(struct client_set *clients);

#endif