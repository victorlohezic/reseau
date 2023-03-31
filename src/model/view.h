#ifndef _VIEW_H_
#define _VIEW_H_

struct view {
    int id;
    int position[2];
    int dimension[2];
};

void init_view(struct view* v, int id, int x, int y, int width, int height);

int get_view_id(const struct view* v);

const int* get_view_position(struct view* v);

const int* get_view_dimension(struct view* v);

void set_view_id(struct view* v, int id);

void set_view_dimension(struct view* v, int width, int height);

void set_view_position(struct view* v, int x, int y);

#endif