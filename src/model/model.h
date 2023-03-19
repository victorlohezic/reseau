#ifndef _MODEL_H_
#define _MODEL_H_

#include <stdio.h>
#include <string.h>

#define SIZE_NAMES 100

// structure de coordonnees pour position/taille d un poisson
struct coordinates {
    int x;
    int y;
};

void init_coordinates(struct coordinates* c, int _x, int _y);

int getx(struct coordinates* c);

int gety(struct coordinates* c);

void setx(struct coordinates* c, int _x);

void sety(struct coordinates* c, int _y);





// stucture de mouvement decrivant le deplacement des poissons
struct movement {
    void (*shift) (struct coordinates*);
};

void init_movement(struct movement* mov, void (*f) (struct coordinates*));

void shifting(struct movement* mov, struct coordinates* c);





// structure decrivant le poisson
struct fish { 
    char name[SIZE_NAMES];
    struct coordinates dimension;
    struct coordinates location;
    struct movement move; 
};

// initalise les valeurs d'un poisson
void init_fish(struct fish* f, char* _name, int width, int height, int x, int y, void (*shift) (struct coordinates*));


// renvoie les coordonnees d'un poisson
struct coordinates get_fish_location(struct fish* f);

// modifie les coordonnees d'un poisson
void set_fish_location(struct fish* f, int x, int y);


// renvoie les dimensions d'un poisson
struct coordinates get_fish_dimension(struct fish* f);

// modifie les dimensions d'un poisson
void set_fish_dimension(struct fish* f, int width, int height);


// modifie la fonction de déplacement d'un poisson
void set_fish_move(struct fish* f, void (*_shift) (struct coordinates*));


// effectue le deplacement d'un poisson
void shift_fish(struct fish* f);


// affiche les coordonnees d'un poisson
void print_fish_loc(struct fish* f);

void print_fish_name(struct fish* f);







#endif //_MODEL_H_