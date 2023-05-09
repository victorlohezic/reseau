#ifndef _AQUARIUM_H_
#define _AQUARIUM_H_
#include <stddef.h>
#include <stdio.h>
#include <stdlib.h>


#include "view.h"
#include "model.h"

#define MAX_VIEWS 10
#define MAX_FISHES 30


// struct of aquarium that contains views and fishes
struct aquarium {
    int dimension[2];

    int nb_views;
    struct view views[MAX_VIEWS];

    int nb_fishes;
    struct fish fishes[MAX_FISHES];
};

// returns the dimensions of the aquarium
const int* get_aquarium_dimension(const struct aquarium* a);

// displays the content of the aquarium
void show(struct aquarium*);

// load an aquarium from a file, returns 0 if success, -1 otherwise
int load(struct aquarium*, char* path);

// returns 0 if success and -1 otherwise
int add_view(struct aquarium*, struct view*);

// returns 0 if success and -1 otherwise
int del_view(struct aquarium*, int id_view);

// returns 0 if success and -1 otherwise
int add_fish(struct aquarium*, struct fish*);

// returns 0 if success and -1 otherwise
int del_fish(struct aquarium*, char* fish_name);

// saves the content of the aquarium in a file, returns 0 if success, -1 otherwise
int save_aquarium(struct aquarium* a, char* path);

// return 1 if the view is in the aquarium 0 otherwise 
int find_view(struct aquarium* a, int id_view);

// return if the fish is in the aquarium 0 otherwise
int find_fish(struct aquarium *a, char* fish_name);

// return the fishes belonging to a view
int fishes_in_view(struct aquarium* a, struct fish** tmp, int id_view);

// update the positions of the fishes
void update_fishes(struct aquarium* a);

// starts the fish matching with the name
int start_fish_aquarium(struct aquarium* a, char* fish_name);

#endif //_AQUARIUM_H_