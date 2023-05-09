#include "model.h"

// functions for the movements

void init_movement(struct movement* mov, void (*f) (int*, int*))
{
    mov->shift = f;
    mov->future_positions = NULL;
}

void shifting(struct movement* mov, int* last_pos, int* size)
{
    (*(mov->shift))(last_pos, size);

}


void add_latest_position(struct movement* mov, int* current_pos, int* size)
{
    if (mov->future_positions == NULL) {
        int pos[2]; 
        pos[0] = current_pos[0];
        pos[1] = current_pos[1];
        fprintf(stderr, "current pos = %dx%d\n", current_pos[0], current_pos[1]);
        shifting(mov, pos, size);
        mov->future_positions = init_queue(pos, 2);
        return;
    }
    mov->future_positions = update_queue(mov->future_positions, current_pos);
    struct timeval tv;
    gettimeofday(&tv, NULL);

    double sec = (double) tv.tv_sec;
    double usec = (double) tv.tv_usec / 1000000;

    double current_time = sec +usec;

    struct queue_position* latest_pos = go_to_end(mov->future_positions);
    int new_pos[2]; 
    new_pos[0] = latest_pos->positions[0];
    new_pos[1] = latest_pos->positions[1];

    shifting(mov,new_pos, size);
    
    mov->future_positions = add_element(mov->future_positions, new_pos, (int) (latest_pos->time - current_time)+3);

}



// functions for the fishes


void init_fish(struct fish* f, char* _name, int width, int height, int x, int y, void (*shift) (int*, int*))
{
    strcpy(f->name, _name);

    f->position[0] = x;
    f->position[1] = y;

    f->dimension[0] = width;
    f->dimension[1] = height;

    f->is_started = 0;

    init_movement(&(f->move), shift);
}

void start_fish(struct fish* f) {
    f->is_started = 1;
}

char* get_fish_name(struct fish* f)
{
    return f->name;
}


void shift_fish(struct fish* f)
{
    (f->move).future_positions = update_queue((f->move).future_positions, f->position);
}

int* get_fish_position(struct fish* f)
{
    shift_fish(f);
    return f->position;
}

void set_fish_position(struct fish* f, int x, int y)
{
    f->position[0] = x;
    f->position[1] = y;
}   


int* get_fish_dimension(struct fish* f)
{
    return f->dimension;
}

void set_fish_dimension(struct fish* f, int width, int height)
{
    f->dimension[0] = width;
    f->dimension[1] = height;
}


void set_fish_move(struct fish* f, void (*_shift) (int*, int*))
{
    init_movement(&(f->move), _shift);
}


void add_future_position(struct fish* f, int* pos, int delay) 
{
    if ((f->move).future_positions == NULL) {
        (f->move).future_positions = init_queue(pos, delay);
    } else {
        (f->move).future_positions = add_element((f->move).future_positions, pos, delay);
    }
}

int next_future_position(struct fish* f, int* pos) 
{
    shift_fish(f);
    if ((f->move).future_positions == NULL) {
        generate_future_position(f);
        fprintf(stderr, "vide!!!!!!!!!!!!\n");
    }
    shift_fish(f);
    if ((f->move).future_positions == NULL) {
        return -1;
    }
    struct timeval tv;
    gettimeofday(&tv, NULL);

    double sec = (double) tv.tv_sec;
    double usec = (double) tv.tv_usec / 1000000;

    double current_time = sec +usec;

    double last_time = (f->move).future_positions->time;
    int dt = (int) (last_time - current_time);
    
    pos[0] = (f->move).future_positions->positions[0];
    pos[1] = (f->move).future_positions->positions[1];
    pos[2] = dt;
    return 0;
}

void generate_future_position(struct fish* f) {
    add_latest_position(&(f->move), f->position, f->dimension);
    print_queue(f->move.future_positions);
}

void free_fish(struct fish* f)
{
   free_queue(f->move.future_positions); 
}


void print_fish_pos(struct fish* f)
{
    printf("x = %d\n", f->position[0]);
    printf("y = %d\n", f->position[1]);

}

void print_fish_name(struct fish* f)
{
    printf("Il s'agit d'un %s\n", f->name);
}
