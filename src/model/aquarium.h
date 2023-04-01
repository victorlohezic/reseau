#ifndef _AQUARIUM_H_
#define _AQUARIUM_H_
#include <stddef.h>
#include "view.h"
#include "model.h"

#define MAX_VIEWS 10
#define MAX_FISHES 30


// struct of aquarium that contains views and fishes
struct aquarium {
    int nb_views;
    struct view* views[MAX_VIEWS];

    int nb_fishes;
    struct fish* fishes[MAX_FISHES];
};


void show(struct aquarium*);

void load(struct aquarium*);

// returns 0 if success and -1 otherwize
int add_view(struct aquarium*, struct view*);

// returns 0 if success and -1 otherwize
int del_view(struct aquarium*, int id_view);

void save_aquarium(struct aquarium*);








#endif //_AQUARIUM_H_