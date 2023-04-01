#include "aquarium.h"

void show(struct aquarium* a) {
    printf("List of fishes: \n");
    for(int k=0; k<a->nb_fishes; k++) {
        struct coordinates dim = get_fish_dimension(&a->fishes[k]);
        struct coordinates loc = get_fish_location(&a->fishes[k]);
        printf("name: %s, dimension: %dx%d, location: %dx%d \n", 
            a->fishes[k].name, 
            getx(&dim),
            gety(&dim),
            getx(&loc),
            gety(&loc));
    }

    printf("List of views: \n");

    for(int k=0; k<a->nb_views; k++) {
        printf("id: %d, dimension: %dx%d, location: %dx%d \n",
        get_view_id(&a->views[k]),
        get_view_dimension(&a->views[k])[0],
        get_view_dimension(&a->views[k])[1],
        get_view_position(&a->views[k])[0],
        get_view_position(&a->views[k])[1]);
    }

}



void load(struct aquarium*);



int add_view(struct aquarium* a, struct view* v)
{
    if (a->nb_views >= MAX_VIEWS) {
        return -1;
    } else {

        for(int k=0; k<a->nb_views; k++) {
            if (get_view_id(v) ==  get_view_id(&(a->views[k]))) {
                return -1;
            }
        }
        a->views[a->nb_views] = *v;
        a->nb_views++;

        return 0;
    }
}



int del_view(struct aquarium* a, int id_view) {
    for(int k = 0; k< a->nb_views; k++) {
        if ((a->views[k]).id == id_view) {

            for(int j = k+1; j < a->nb_views; j++) {
                a->views[j-1] = a->views[j];
            }
            a->nb_views--;

            return 0;

        } 

    }
    return -1;

}



void save_aquarium(struct aquarium*);




