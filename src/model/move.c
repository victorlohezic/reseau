#include "move.h"


void random_path(int* last_pos, int* size)
{
    struct timeval tv;
    gettimeofday(&tv, NULL);
    last_pos[0] = (last_pos[0]+ tv.tv_usec) % (100-size[0]); 

    gettimeofday(&tv, NULL);
    last_pos[1] = (last_pos[1]+ tv.tv_usec) % (100-size[1]); 
}


void dvd_bouncing(int* last_pos, int* size)
{
    struct timeval tv;

    if ((last_pos[0]%(100-size[0]) != 0) && (last_pos[1]%(100-size[1]) != 0)){
        gettimeofday(&tv, NULL);
        int side_choice = (tv.tv_usec)%2;

        gettimeofday(&tv, NULL);
        last_pos[side_choice] = ((tv.tv_usec+last_pos[0])%2) * (100-size[side_choice]);
        last_pos[1-side_choice] = 1+((tv.tv_usec+last_pos[1])%(99-size[1-side_choice]));

    } else if(last_pos[0] == 0) {
        last_pos[0] = (100-size[0]) - last_pos[1];
        last_pos[1] = (100-size[1]);

    } else if(last_pos[0] == (100-size[0])) {
        last_pos[0] = (100-size[0]) - last_pos[1];
        last_pos[1] = 0;
    } else if(last_pos[1] == 0) {
        last_pos[1] = last_pos[0];
        last_pos[0] = 0;
    } else {
        last_pos[1] = last_pos[0];
        last_pos[0] = (100-size[0]);
    }

}

void round_trip(int* last_pos, int* size)
{
    struct timeval tv;
    gettimeofday(&tv, NULL);

    if (last_pos[0]%(100-size[0]) != 0){
        last_pos[0] = ((tv.tv_usec)%2) * (100-size[0]);
        last_pos[1] = tv.tv_usec%(100-size[1]);
    } else {
        last_pos[0] = (100-size[0]) - last_pos[0];
        last_pos[1] = (tv.tv_usec+last_pos[1])%(100-size[1]);
    }
}





void (*get_move_function(char* move_name)) (int*, int*) 
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

    void (*move)(int*, int*);
    move = get_move_function("DvdBouncing");
    int last_pos[2] = {21, 68};
    int size[2] = {4, 6};
    for (int k = 0; k<10; k++) {
        (*move)(last_pos, size);
        printf("%d, %d\n", last_pos[0], last_pos[1]);
    }
    return 0;
}
*/