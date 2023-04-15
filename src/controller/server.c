/* A simple server in the internet domain using TCP
   The port number is passed as an argument */
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <strings.h>
#include <sys/time.h>
#include <sys/select.h>

#include "parser.h"
#include "client.h"
#include "network_command.h"

#define SO_REUSEPORT 15 
#define MAX_CLIENTS MAX_VIEWS

void error(char *msg)
{
    perror(msg);
    exit(1);
}

void handle_network_command(char* command, int socket_client, struct client_set* clients){
    if (strstr(command, "hello") == command) { // check if the command start with hello
        hello(command, socket_client, clients);
    } 
    else if (strstr(command, "log out") == command) {
        log_out(socket_client);
    }
    else if (strstr(command, "ping") == command) {
        ping(command, socket_client);
    }
    else if (strstr(command, "addFish") == command) {
        network_add_fish(command, socket_client, clients);
    }
    else if (strstr(command, "delFish") == command) {
        network_del_fish(command, socket_client, clients);
    }
     else if (strstr(command, "getFishesContinuously") == command) {
        write(socket_client, "Command not found\n", 19);
    }
    else if (strstr(command, "getFishes") == command) {
        network_get_fishes(command, socket_client, clients);
    }
    else if (strstr(command, "startFish") == command) {
        network_start_fish(command, socket_client, clients);
    }
    else {
        write(socket_client, "Command not found\n", 19);
    }
}


void init_server(const char* filename, int* controller_port, int* display_time_out_value, int* fish_update_interval) {
    struct Config* config = parse_controller_config(filename);
    *controller_port = get_setting(config, "controller-port");
    *display_time_out_value = get_setting(config, "display-timeout-value");
    *fish_update_interval = get_setting(config, "fish-update-interval");
}

int main(int argc, char *argv[])
{
    int main_socket_fd, controller_port, display_time_out_value, fish_update_interval;
    struct aquarium controller_aquarium;
    struct client_set clients;
    struct client_info current_client;
    load(&controller_aquarium, "save_a1.txt");

    init_client_set(&clients, &controller_aquarium);
    socklen_t clilen;
    int opt = 1;
    char buffer[256];
    struct sockaddr_in serv_addr, cli_addr;
    int socket_fd, client_ready;
    //int n;
    //set of socket descriptors 
    fd_set readfds;
    
    init_server("build/controller.cfg", &controller_port, &display_time_out_value, &fish_update_interval);
    
    main_socket_fd = socket(AF_INET, SOCK_STREAM, 0);

    if (main_socket_fd < 0)
        error("ERROR opening socket");

    // Forcefully attaching socket to the port 
    if (setsockopt(main_socket_fd, SOL_SOCKET, SO_REUSEADDR | SO_REUSEPORT, &opt, sizeof(opt))) {
        error("ERROR setsockopt");
    }
    bzero((char *)&serv_addr, sizeof(serv_addr));
    serv_addr.sin_family = AF_INET;
    serv_addr.sin_addr.s_addr = INADDR_ANY;
    serv_addr.sin_port = htons(controller_port);

    if (bind(main_socket_fd, (struct sockaddr *)&serv_addr, sizeof(serv_addr)) < 0)
        error("ERROR on binding");

    listen(main_socket_fd, MAX_CLIENTS);
    clilen = sizeof(cli_addr);
    FD_ZERO(&readfds);  
    FD_SET(main_socket_fd, &readfds);
    while (1) {
        
        bzero(buffer, 256);
        //wait for an activity on one of the sockets , timeout is NULL , 
        //so wait indefinitely 
        client_ready = select(FD_SETSIZE , &readfds , NULL , NULL , NULL);  
        if ((client_ready < 0)) { 
            error("ERROR on select");  
        } 
        //new connection 
        if (FD_ISSET(main_socket_fd, &readfds)) {  
            socket_fd = accept(main_socket_fd, (struct sockaddr *)&cli_addr, &clilen); 
    
            if (socket_fd < 0) {  
                error("ERROR on accept") ;
            }
            
            //inform user of socket number - used in send and receive commands 
            printf("New connection , socket fd is %d, port : %d \n" , socket_fd , ntohs
                  (cli_addr.sin_port));  

            if (read(socket_fd , buffer, 256) < 0) {
                error("ERROR on reading new socket");
            }
            printf("< %s", buffer);
            if (hello(buffer, socket_fd, &clients) != -1) {
                FD_SET(socket_fd, &readfds);  
            } 
        }


         //else its some IO operation on some other socket
        for (int i = 0; i < MAX_CLIENTS; i++)  
        {  
            current_client = get_client_info(&clients, i);
            socket_fd = get_socket_client(&current_client);
            if (FD_ISSET(socket_fd, &readfds)) {  
                if ((read(socket_fd, buffer, 1024)) < 0) {
                    error("ERROR on reading");
                }  
                printf("< %s", buffer);
                //Echo back the message that came in 
               handle_network_command(buffer, socket_fd, &clients); 
            }  
        }
    }
    return 0;
}
