#include "move.h"
#include "model.h"
#include <time.h>



void random_path(struct fish* f)
{
    struct timeval tv;
    gettimeofday(&tv, NULL);
    double sec = (double) tv.tv_sec;
    double usec = (double) tv.tv_usec / 1000000;

    double current_time = sec +usec;
    struct queue_position* latest_pos = go_to_end(f->move.future_positions);
    int latest_x, latest_y, latest_time;
    if (latest_pos != NULL) {
        latest_x = latest_pos->positions[0];
        latest_y = latest_pos->positions[1];
        latest_time = latest_pos->time;
    } else {
        latest_x = f->position[0];
        latest_y = f->position[1];
        latest_time = current_time;
    }
    srand(time(NULL));
    int future_x, future_y;
    do {
        future_x = latest_x + rand() % 201 - 100;
    } while (future_x + f->dimension[0] >= aquarium_x);
    do {
        future_y = latest_y + rand() % 201 - 100;
    } while (future_y + f->dimension[1] >= aquarium_y);
    
    int future_pos[2] = {future_x, future_y};
    add_future_position(f, future_pos, (current_time - latest_time) + 2);
}


// void dvd_bouncing(struct fish* f)
// {
//     struct timeval tv;

//     if ((last_pos[0]%(100-size[0]) != 0) && (last_pos[1]%(100-size[1]) != 0)){
//         gettimeofday(&tv, NULL);
//         int side_choice = (tv.tv_usec)%2;

//         gettimeofday(&tv, NULL);
//         last_pos[side_choice] = ((tv.tv_usec+last_pos[0])%2) * (100-size[side_choice]);
//         last_pos[1-side_choice] = 1+((tv.tv_usec+last_pos[1])%(99-size[1-side_choice]));

//     } else if(last_pos[0] == 0) {
//         last_pos[0] = (100-size[0]) - last_pos[1];
//         last_pos[1] = (100-size[1]);

//     } else if(last_pos[0] == (100-size[0])) {
//         last_pos[0] = (100-size[0]) - last_pos[1];
//         last_pos[1] = 0;
//     } else if(last_pos[1] == 0) {
//         last_pos[1] = last_pos[0];
//         last_pos[0] = 0;
//     } else {
//         last_pos[1] = last_pos[0];
//         last_pos[0] = (100-size[0]);
//     }

// }

void horizontal_path(struct fish* f)
{
    struct timeval tv;
    gettimeofday(&tv, NULL);
    double sec = (double) tv.tv_sec;
    double usec = (double) tv.tv_usec / 1000000;

    double current_time = sec +usec;
    struct queue_position* latest_pos = go_to_end(f->move.future_positions);
    int latest_x, latest_y, latest_time;
    if (latest_pos != NULL) {
        latest_x = latest_pos->positions[0];
        latest_y = latest_pos->positions[1];
        latest_time = latest_pos->time;
    } else {
        latest_x = f->position[0];
        latest_y = f->position[1];
        latest_time = current_time;
    }
    int future_x, future_y;
    future_y = latest_y;
    future_x = latest_x;
    int time = 2;
    int future_pos[2] = {future_x, future_y};
        if (future_x > aquarium_x/2) {
            while (future_x - aquarium_x/6 > 0) {
                future_x -= aquarium_x/6;
                future_pos[0] = future_x;
                add_future_position(f, future_pos, (current_time - latest_time) + time);
                
                time+=2;
            }
        } else {
            while (future_x + aquarium_x/6 < aquarium_x - f->dimension[1] - 20) {
                future_x += aquarium_x/6;
                future_pos[0] = future_x;
                add_future_position(f, future_pos, (current_time - latest_time) + time);
                time+=2;
            }
    }
}

void (*get_move_function(char* move_name)) (struct fish*)
{
    if (strcmp(move_name, "RandomWayPoint") == 0) {
        return &random_path;
    // } else if(strcmp(move_name, "DvdBouncing") == 0){
    //     return &dvd_bouncing;
    } else if(strcmp(move_name, "HorizontalPathWay") == 0){
       return &horizontal_path;
    } else {
        return &random_path;
    }
}




/*
int main() {

    void (*move)(int*, int*);
    move = get_move_function("RandomWayPoint");
    int last_pos[2] = {21, 21};
    int size[2] = {4, 6};
    for (int k = 0; k<10; k++) {
        (*move)(last_pos, size);
        printf("%d, %d\n", last_pos[0], last_pos[1]);
    }
    return 0;
}
*/
