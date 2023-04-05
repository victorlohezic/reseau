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



