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

struct queue_position* go_to_end(struct queue_position* q) {
    if (q == NULL) {
        return NULL;
    }
    
    struct queue_position* ite = q;
    while (ite->next != NULL) {
        ite = ite->next;
    }
    return ite;
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
 

struct queue_position* update_queue(struct queue_position* q, int* current_pos)
{

    struct timeval tv;
    gettimeofday(&tv, NULL);

    double sec = (double) tv.tv_sec;
    double usec = (double) tv.tv_usec / 1000000;

    double current_time = sec +usec;

    struct queue_position* head = q;
    while (head != NULL && head->time < current_time) {
        if (head->next == NULL || head->next->time > current_time) {
            current_pos[0] = head->positions[0];
            current_pos[1] = head->positions[1];
        }
        head = del_element_head(head);
    }
    return head;
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


void print_queue(struct queue_position* q)
{
printf("\n##########\n");

struct queue_position* ite = q;
while(ite != NULL) {
    printf("x: %d, y: %d, time: %f\n", ite->positions[0], ite->positions[1], ite->time);
    ite = ite->next;
}
printf("##########\n");

}
