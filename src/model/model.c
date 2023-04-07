#include "model.h"

// functions for the movements

void init_movement(struct movement* mov, void (*f) (int*))
{
    mov->shift = f;
}

void shifting(struct movement* mov, int* c)
{
    (*(mov->shift))(c);
}



// functions for the fishes


void init_fish(struct fish* f, char* _name, int width, int height, int x, int y, void (*shift) (int*))
{
    strcpy(f->name, _name);

    f->position[0] = x;
    f->position[1] = y;

    f->dimension[0] = width;
    f->dimension[1] = height;

    init_movement(&(f->move), shift);
}

char* get_fish_name(struct fish* f)
{
    return f->name;
}


void shift_fish(struct fish* f)
{
    shifting(&(f->move),f->position);
}

int* get_fish_position(struct fish* f)
{
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


void set_fish_move(struct fish* f, void (*_shift) (int*))
{
    init_movement(&(f->move), _shift);
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