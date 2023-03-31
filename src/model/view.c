#include "view.h"

void init_view(struct view* v, int id, int x, int y, int width, int height) {
    v->id = id;
    v->dimension[0] = width;
    v->dimension[1] = height;
    v->position[0] = x;
    v->position[1] = y;
}

int get_view_id(const struct view* v) {
    return v->id;
}

const int* get_view_position(const struct view* v) {
    return v->position;
}

const int* get_view_dimension(const struct view* v) {
    return v->dimension;
}

void set_view_id(struct view* v, int id) {
  v->id = id;
}

void set_view_dimension(struct view* v, int width, int height) {
  v->dimension[0] = width;
  v->dimension[1] = height;
}

void set_view_position(struct view* v, int x, int y) {
  v->position[0] = x;
  v->position[1] = y;
}



