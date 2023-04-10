/* A simple server in the internet domain using TCP
   The port number is passed as an argument */
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <time.h>

void error(char *msg)
{
    perror(msg);
    exit(1);
}

int main(int argc, char *argv[])
{
    srand(time(NULL));

    int sockfd, newsockfd, portno, clilen;
    char buffer[256];
    struct sockaddr_in serv_addr, cli_addr;
    int n;

    if (argc < 2)
    {
        fprintf(stderr, "ERROR, no port provided\n");
        exit(1);
    }

    sockfd = socket(AF_INET, SOCK_STREAM, 0);

    if (sockfd < 0)
        error("ERROR opening socket");

    bzero((char *)&serv_addr, sizeof(serv_addr));
    // port number
    portno = atoi(argv[1]);
    serv_addr.sin_family = AF_INET;
    serv_addr.sin_addr.s_addr = INADDR_ANY;
    serv_addr.sin_port = htons(portno);

    if (bind(sockfd, (struct sockaddr *)&serv_addr, sizeof(serv_addr)) < 0)
        error("ERROR on binding");

    listen(sockfd, 5);
    clilen = sizeof(cli_addr);

    newsockfd = accept(sockfd, (struct sockaddr *)&cli_addr, &clilen);

    if (newsockfd < 0)
        error("ERROR on accept");

    int i = 0;
    int random_x = 0;
    int random_y = 0;
    int random_x2 = 0;
    int random_y2 = 0;
    int random_time = 0;
    int random_time2 = 0;
    while (1)
    {
        if (i == 0)
        {
            bzero(buffer, 256);

            n = read(newsockfd, buffer, 255);
            if (n < 0)
                error("ERROR reading from socket");

            printf("Here is the message: %s\n", buffer);
            bzero(buffer, 256);

            n = 0;
            fprintf(stderr, "%d\n", i);
            sleep(3);
            strcpy(buffer, "greeting\n");
            n = write(newsockfd, buffer, strlen(buffer) * sizeof(char));
        }
        else if (i < 3)
        {
            bzero(buffer, 256);

            n = read(newsockfd, buffer, 255);
            if (n < 0)
                error("ERROR reading from socket");

            printf("Here is the message: %s\n", buffer);
            bzero(buffer, 256);

            sleep(2);
            n = 0;
            fprintf(stderr, "%d\n", i);
            sleep(1);
            strcpy(buffer, "OK\n");
            n = write(newsockfd, buffer, strlen(buffer) * sizeof(char));
        }
        else
        {
            sleep(5);
            random_x = rand() % 101;
            random_y = rand() % 101;
            random_x2 = rand() % 101;
            random_y2 = rand() % 101;
            random_time = rand() % 5;
            random_time2 = rand() % 6;
            sprintf(buffer, "list [Chouchou at %dx%d,3x4,%d] [SmileyFleur at %dx%d,3x4,%d]\n", random_x, random_y, random_time, random_x2, random_y2, random_time2);
            fprintf(stderr, buffer);
            n = write(newsockfd, buffer, strlen(buffer) * sizeof(char));
        }

        if (n < 0)
            error("ERROR writing to socket");

        if (strcmp("exit", buffer) == 0)
        {
            break;
        }
        ++i;
    }

    return 0;
}