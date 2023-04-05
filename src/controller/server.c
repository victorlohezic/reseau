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

#include "parser.h"
#include "aquarium.h"
#include "network_command.h"

#define SO_REUSEPORT 15 

void error(char *msg)
{
    perror(msg);
    exit(1);
}



void handle_network_command(char* command, int socket_client){
    if (strstr(command, "hello") == command) { // check if the command start with hello
        hello(command, socket_client);
    } 
    else if (strcmp(command, "log out") == 0) {
        log_out(socket_client);
    }
    else {
        send(socket_client, "Command not found\n", 19, 0);
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
    int sockfd, newsockfd, controller_port, display_time_out_value, fish_update_interval;
    struct aquarium controller_aquarium;
    load(&controller_aquarium, "save_a1.txt");
    socklen_t clilen;
    int opt = 1;
    char buffer[256];
    struct sockaddr_in serv_addr, cli_addr;
    int n;

    init_server("build/controller.cfg", &controller_port, &display_time_out_value, &fish_update_interval);

    sockfd = socket(AF_INET, SOCK_STREAM, 0);

    if (sockfd < 0)
        error("ERROR opening socket");

    // Forcefully attaching socket to the port 
    if (setsockopt(sockfd, SOL_SOCKET, SO_REUSEADDR | SO_REUSEPORT, &opt, sizeof(opt))) {
        error("ERROR setsockopt");
    }
    bzero((char *)&serv_addr, sizeof(serv_addr));
    serv_addr.sin_family = AF_INET;
    serv_addr.sin_addr.s_addr = INADDR_ANY;
    serv_addr.sin_port = htons(controller_port);

    if (bind(sockfd, (struct sockaddr *)&serv_addr, sizeof(serv_addr)) < 0)
        error("ERROR on binding");
    listen(sockfd, 5);
    clilen = sizeof(cli_addr);
    newsockfd = accept(sockfd, (struct sockaddr *)&cli_addr, &clilen);

    if (newsockfd < 0)
        error("ERROR on accept");

    while (1) {
        bzero(buffer, 256);
        n = read(newsockfd, buffer, 255);
        if (n < 0)
            error("ERROR reading from socket");
   
        printf("Here is the message: %s\n", buffer);
        
        handle_network_command(buffer, newsockfd);
        // bzero(buffer, 256);
        // n = 0;
        // // while ((buffer[n++] = getchar()) != '\n');
        // // n = write(newsockfd, buffer, n*sizeof(char));
        
        // if (n < 0)
        //     error("ERROR writing to socket");

        // if (strcmp("exit", buffer) == 0) {
        //     break;
        // }
    }
    close(newsockfd);

    return 0;
}
