#include "move.h"


void random_path(int* c)
{
    struct timeval tv;
    gettimeofday(&tv, NULL);
    c[0] = (c[0]+ tv.tv_usec) % 100; 

    gettimeofday(&tv, NULL);
    c[1] = (c[1]+ tv.tv_usec) % 100; 
}


void dvd_bouncing(int* c)
{
    struct timeval tv;

    if ((c[0]%100 != 0) && (c[1]%100 != 0)){
        gettimeofday(&tv, NULL);
        int side_choice = (tv.tv_usec)%2;

        gettimeofday(&tv, NULL);
        c[side_choice] = ((tv.tv_usec+c[0])%2) * 100;
        c[1-side_choice] = 1+((tv.tv_usec+c[1])%99);

    } else if(c[0] == 0) {
        c[0] = 100 - c[1];
        c[1] = 100;

    } else if(c[0] == 100) {
        c[0] = 100 - c[1];
        c[1] = 0;
    } else if(c[1] == 0) {
        c[1] = c[0];
        c[0] = 0;
    } else {
        c[1] = c[0];
        c[0] = 100;
    }

}

void round_trip(int* c)
{
    struct timeval tv;
    gettimeofday(&tv, NULL);

    if (c[0]%100 != 0){
        c[0] = ((tv.tv_usec)%2) * 100;
        c[1] = tv.tv_usec%100;
    } else {
        c[0] = 100 - c[0];
        c[1] = (tv.tv_usec+c[1])%100;
    }
}





void (*get_move_function(char* move_name)) (int*) 
{
    if (strcmp(move_name, "RandomWayPoint") == 0) {
        return &random_path;
    } else if(strcmp(move_name, "DvdBouncing") == 0){
        return &dvd_bouncing;
    } else if(strcmp(move_name, "RoundTrip") == 0){
        return &round_trip;
    } else {
        assert(0);
    }
}



/*

int main() {

    void (*move)(int*);
    move = get_move_function("RoundTrip");
    int c[2] = {21, 68};
    for (int k = 0; k<10; k++) {
        (*move)(c);
        printf("%d, %d\n", c[0], c[1]);
    }
    return 0;
}*/
