#ifndef _MODEL_H_
#define _MODEL_H_

#include <stdio.h>
#include <string.h>

#define SIZE_NAMES 100

// structure of coordinates for the scale/location of a fish
struct coordinates {
    int x;
    int y;
};

// initializes a coordinates
void init_coordinates(struct coordinates* c, int _x, int _y);

// returns the x value 
int getx(struct coordinates* c);

// returns the y value 
int gety(struct coordinates* c);

// modifies the x value
void setx(struct coordinates* c, int _x);

// modifies the y value
void sety(struct coordinates* c, int _y);





// structure describing the movements of a fish
struct movement {
    void (*shift) (struct coordinates*);
};

// initializes a movement
void init_movement(struct movement* mov, void (*f) (struct coordinates*));


// modifies a coordinate c with the movement mov
void shifting(struct movement* mov, struct coordinates* c);





// structure of a fish
struct fish { 
    char name[SIZE_NAMES];
    struct coordinates dimension;
    struct coordinates location;
    struct movement move; 
};

// initalizes a fish
void init_fish(struct fish* f, char* _name, int width, int height, int x, int y, void (*shift) (struct coordinates*));


// returns the location of a fish 
struct coordinates get_fish_location(struct fish* f);

// modifies the location of a fish
void set_fish_location(struct fish* f, int x, int y);

// returns the dimensions of a fish 
struct coordinates get_fish_dimension(struct fish* f);

// modifies the location of a fish
void set_fish_dimension(struct fish* f, int width, int height);


// modifies the shifting function of a fish
void set_fish_move(struct fish* f, void (*_shift) (struct coordinates*));


// applies the shifting function to move a fish
void shift_fish(struct fish* f);


// diplays the location of a fish
void print_fish_loc(struct fish* f);

// diplays the name of a fish
void print_fish_name(struct fish* f);







#endif //_MODEL_H_