#include "aquarium.h"

const int* get_aquarium_dimension(const struct aquarium* a) {
    return a->dimension;
}


void show(struct aquarium* a) 
{
    printf("%dx%d\n",get_aquarium_dimension(a)[0], get_aquarium_dimension(a)[1]);

    for(int k=0; k<a->nb_views; k++) {
        printf("%d %dx%d+%d+%d\n",
        get_view_id(&a->views[k]),
        get_view_position(&a->views[k])[0],
        get_view_position(&a->views[k])[1],
        get_view_dimension(&a->views[k])[0],
        get_view_dimension(&a->views[k])[1]);
    }    
}



int load(struct aquarium* a, char* path)
{
    a->nb_views = 0;
    a->nb_fishes = 0;

    FILE* input_file;
    input_file = fopen(path, "r");

    if (input_file == NULL) {
        return -1;
    }

    int num;
    for (int k=0; k<2; k++) {
        fscanf(input_file, "%d", &num);
        a->dimension[k] = num;
        fseek(input_file, 1, SEEK_CUR);
    }

    int id, pos_x, pos_y, dim_x, dim_y;
    struct view v;
    while (fscanf(input_file, "%d", &id) == 1) {
        fseek(input_file, 1, SEEK_CUR);

        fscanf(input_file, "%d", &pos_x);     
        fseek(input_file, 1, SEEK_CUR);

        fscanf(input_file, "%d", &pos_y);
        fseek(input_file, 1, SEEK_CUR);

        fscanf(input_file, "%d", &dim_x);
        fseek(input_file, 1, SEEK_CUR);
        
        fscanf(input_file, "%d", &dim_y);
        fseek(input_file, 1, SEEK_CUR);

        init_view(&v, id, pos_x, pos_y, dim_x, dim_y);
        add_view(a, &v);
    }

    fclose(input_file);
    return 0;
}



int add_view(struct aquarium* a, struct view* v)
{
    if (a->nb_views >= MAX_VIEWS) {
        return -1;
    } 
        
    if (get_view_position(v)[0] < 0 || get_view_position(v)[1] < 0 || 
        get_view_position(v)[0] + get_view_dimension(v)[0] > get_aquarium_dimension(a)[0] ||
        get_view_position(v)[1] + get_view_dimension(v)[1] > get_aquarium_dimension(a)[1]) {

        return -1;
    }

    for(int k=0; k<a->nb_views; k++) {
        if (get_view_id(v) ==  get_view_id(&(a->views[k]))) {
            return -1;
        }
    }
    a->views[a->nb_views] = *v;
    a->nb_views++;
    return 0;
        

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

int find_fish(struct aquarium *a, char* fish_name) {
    for(int k=0; k<a->nb_fishes; k++) {
        if (strcmp(fish_name, get_fish_name(a->fishes+k)) == 0) {
            return 1;
        }
    }
    return 0;
}



int add_fish(struct aquarium* a, struct fish* f)
{
    if (a->nb_fishes >= MAX_FISHES) {
        return -1;
    } 
        
    if (get_fish_position(f)[0] < 0 || get_fish_position(f)[1] < 0 || 
        get_fish_position(f)[0] + get_fish_dimension(f)[0] > get_aquarium_dimension(a)[0] ||
        get_fish_position(f)[1] + get_fish_dimension(f)[1] > get_aquarium_dimension(a)[1]) {

        return -1;
    }

    if (find_fish(a, get_fish_name(f))) {
        return -1;
    }

    a->fishes[a->nb_fishes] = *f;
    a->nb_fishes++;
    return 0;
        

}



int del_fish(struct aquarium* a, char* fish_name) {
    for(int k = 0; k< a->nb_fishes; k++) {
        if (strcmp((a->fishes[k]).name,fish_name) == 0) {

            for(int j = k+1; j < a->nb_fishes; j++) {
                a->fishes[j-1] = a->fishes[j];
            }
            a->nb_fishes--;

            return 0;

        } 

    }
    return -1;

}



int save_aquarium(struct aquarium* a, char* path) {

    FILE* output_file;
    output_file = fopen(path, "w");

    if (output_file == NULL) {
        return -1;
    }

    fprintf(output_file, "%dx%d\n",get_aquarium_dimension(a)[0], get_aquarium_dimension(a)[1]);

    for(int k=0; k<a->nb_views; k++) {
        fprintf(output_file, "%d %dx%d+%d+%d\n",
        get_view_id(&a->views[k]),
        get_view_position(&a->views[k])[0],
        get_view_position(&a->views[k])[1],
        get_view_dimension(&a->views[k])[0],
        get_view_dimension(&a->views[k])[1]);
    }    

    fclose(output_file);
    return 0;
}

int find_view(struct aquarium* a, int id_view) {
    for(int k=0; k<a->nb_views; k++) {
        if ((a->views[k]).id == id_view) {
            return 1;
        }
    }
    return 0;
}

int fishes_in_view(struct aquarium* a, struct fish* tmp, int id_view)
{
    struct view* v = NULL;
    for(int k=0; k<a->nb_views; k++) {
        if ((a->views[k]).id == id_view) {
            v = a->views+k;
        }
    }
    if (v == NULL) {
        return 0;
    }

    int rank = 0;

    for (int i=0; i<a->nb_fishes; i++) {
        int cond1 = (get_fish_position(a->fishes+i)[0] < get_view_position(v)[0] + get_view_dimension(v)[0]);
        int cond2 = (get_fish_position(a->fishes+i)[0] + get_fish_dimension(a->fishes+i)[0] > get_view_position(v)[0]);
        int cond3 = (get_fish_position(a->fishes+i)[1] < get_view_position(v)[1] + get_view_dimension(v)[1]);
        int cond4 = (get_fish_position(a->fishes+i)[1] + get_fish_dimension(a->fishes+i)[1] > get_view_position(v)[1]);

        if (cond1 && cond2 && cond3 && cond4) {
            init_fish(tmp+rank, get_fish_name(a->fishes+i),
                                 get_fish_dimension(a->fishes+i)[0], 
                                 get_fish_dimension(a->fishes+i)[1], 
                                 get_fish_position(a->fishes+i)[0], 
                                 get_fish_position(a->fishes+i)[1], 
                                 a->fishes[i].move.shift);

            rank++;
        }
    }
    return rank;
}

