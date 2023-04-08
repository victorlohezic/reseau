#ifndef _MODEL_H_
#define _MODEL_H_

#include <stdio.h>
#include <string.h>

#define SIZE_NAMES 100

// structure describing the movements of a movement
struct movement {
    void (*shift) (int*);
};

// initializes a movement
void init_movement(struct movement* mov, void (*f) (int*));

// modifies a coordinate c with the movement mov
void shifting(struct movement* mov, int* c);

// structure of a fish
struct fish { 
    char name[SIZE_NAMES];
    int position[2];
    int dimension[2];
    struct movement move; 
};

// initalizes a fish
void init_fish(struct fish* f, char* _name, int width, int height, int x, int y, void (*shift) (int*));

// diplays the name of a fish
char* get_fish_name(struct fish* f);

// returns the position of a fish 
int* get_fish_position(struct fish* f);

// modifies the position of a fish
void set_fish_position(struct fish* f, int x, int y);

// returns the dimensions of a fish 
int* get_fish_dimension(struct fish* f);

// modifies the dimensions of a fish
void set_fish_dimension(struct fish* f, int width, int height);

// modifies the shifting function of a fish
void set_fish_move(struct fish* f, void (*_shift) (int*));

// applies the shifting function to move a fish
void shift_fish(struct fish* f);

// diplays the position of a fish
void print_fish_pos(struct fish* f);

// diplays the name of a fish
void print_fish_name(struct fish* f);

#endif //_MODEL_H_