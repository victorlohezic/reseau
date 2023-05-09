#ifndef _QUEUE_H_
#define _QUEUE_H_

#include <stdio.h>
#include <sys/time.h>
#include <stdlib.h>

//struct of queue ordered by time
struct queue_position {

    int positions[2];
    double time;
    struct queue_position* next;
};

//initializes a queue with a first position and delta_time
struct queue_position* init_queue(int* pos, int delta_time);

//returns the final position in the queue
struct queue_position* go_to_end(struct queue_position* q);

//adds a position in the ordered queue
struct queue_position* add_element(struct queue_position* q, int* pos, int delta_time);

//deletes the first element of the queue
struct queue_position* del_element_head(struct queue_position* q);

//deletes the passed positions in the queue
struct queue_position* update_queue(struct queue_position* q, int* current_pos);

//frees all the positions in the queue
int free_queue(struct queue_position* q);

//prints the entire queue
void print_queue(struct queue_position* q);


#endif // _QUEUE_H_

