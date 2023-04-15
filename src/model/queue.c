#include "queue.h"


struct queue_position* init_queue(int* pos, int delta_time)
{
    struct queue_position* q = malloc(sizeof(struct queue_position));
    struct timeval tv;
    gettimeofday(&tv, NULL);

    double sec = (double) tv.tv_sec;
    double usec = (double) tv.tv_usec / 1000000;


    q->time = (double) delta_time + sec +usec;

    q->positions[0] = pos[0];
    q->positions[1] = pos[1];

    q->next = NULL;

    return q;
}

struct queue_position* go_to_next(struct queue_position* q) {
    if (q == NULL) {
        return NULL;
    }
    return q->next;
}


struct queue_position* add_element(struct queue_position* q, int* pos, int delta_time)
{
    if (q == NULL) {
        return NULL;
    }

    struct queue_position* ite = q;
    struct queue_position* new = init_queue(pos, delta_time);

    if (ite->time > new->time) {
        new->next = ite;
        return new;
    }

    while (ite->next != NULL && ite->time < new->time) {
        ite = ite->next;
    }

    ite->next = new;

    return q;
}

struct queue_position* del_element_head(struct queue_position* q)
{
    if (q == NULL) {
        return NULL;
    }
    struct queue_position* next = q->next;
    free(q);
    return next;
}
 

int free_queue(struct queue_position* q) 
{
    if (q == NULL) {
        return -1;
    }
    struct queue_position* ite = q;
    struct queue_position* next;
    while (ite != NULL) {
        next = ite->next;
        free(ite);
        ite = next;
    }
    return 0;
}

