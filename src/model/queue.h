#ifndef _QUEUE_H_
#define _QUEUE_H_

#include <stdio.h>
#include <sys/time.h>
#include <stdlib.h>

struct queue_position {

    int positions[2];
    double time;
    struct queue_position* next;
};


struct queue_position* init_queue(int* pos, int delta_time);

struct queue_position* go_to_next(struct queue_position* q);

struct queue_position* add_element(struct queue_position* q, int* pos, int delta_time);

struct queue_position* del_element_head(struct queue_position* q);

int free_queue(struct queue_position* q);



#endif // _QUEUE_H_

