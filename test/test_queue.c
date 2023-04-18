#include "test_queue.h"

void test_init_queue() {
    printf("%s", __func__);

    int pos[2] = {10, 20};
    int dt = 5;

    struct queue_position* q = init_queue(pos, dt);
    assert(q->positions[0] = 10);
    assert(q->positions[1] == 20);
    assert(q->positions[1] != 21);

    struct timeval tv;
    gettimeofday(&tv, NULL);

    double sec = (double) tv.tv_sec;
    double usec = (double) tv.tv_usec / 1000000;

    assert(q->time <= (double) dt + sec +usec);
    assert(q->time >=  sec +usec);

    assert(q->next == NULL);

    free(q);

    printf("\t\tOK\n");
}


void test_go_to_next() {
    printf("%s", __func__);

    int pos1[2] = {11,12};
    struct queue_position* q1 = init_queue(pos1,5);

    int pos2[2] = {21,22};
    struct queue_position* q2 = init_queue(pos2,10);

    q1->next = q2;
    assert(q1->next == q2);

    struct queue_position* next = go_to_next(q1);
    assert(next == q2);
    assert(next->positions[0] == 21);

    next = go_to_next(next);
    assert(next == NULL);

    free(q1);
    free(q2);
    printf("\t\tOK\n");
}


void test_add_element() {
    printf("%s", __func__);

    int pos1[2] = {11,12};
    struct queue_position* q1 = init_queue(pos1,5);

    int pos2[2] = {21,22};
    struct queue_position* q = add_element(q1, pos2, 12);
    assert(q == q1);
    assert(go_to_next(q)->positions[0] == 21);

    int pos3[2] = {31,32};
    q = add_element(q, pos3, 2);   
    assert(q != q1);
    assert(q->positions[0] == 31);
    assert(q->next == q1); 

    free(q1->next);
    free(q1);
    free(q);

    printf("\tOK\n");
}


void test_del_element_head() {
    printf("%s", __func__);

    int pos1[2] = {11,12};
    struct queue_position* q1 = init_queue(pos1,5);

    int pos2[2] = {21,22};
    struct queue_position* q = add_element(q1, pos2, 12);

    int pos3[2] = {31,32};
    q = add_element(q, pos3, 2);   

    q = del_element_head(q);
    assert(q->positions[1] == 12);
    assert(q == q1);
    q = del_element_head(q);
    assert(q->positions[0] == 21);
    assert(q->next == NULL);
    free(q);
    

    printf("\tOK\n");
}

void test_free_queue() {
    printf("%s", __func__);

    int pos1[2] = {11,12};
    struct queue_position* q1 = init_queue(pos1,5);

    int pos2[2] = {21,22};
    struct queue_position* q = add_element(q1, pos2, 12);

    int pos3[2] = {31,32};
    q = add_element(q, pos3, 2);   

    free_queue(q);

    printf("\t\tOK\n");
}




int main(void)
{
    printf("\nTEST FILE : %s\n\n", __FILE__);

    test_init_queue();
    test_go_to_next();
    test_add_element();
    test_del_element_head();
    test_free_queue();

    //test_update_queue();
    
    return 0;
}