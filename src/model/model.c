#include "model.h"

// fonctions sur les coordonnees

void init_coordinates(struct coordinates* c, int _x, int _y)
{
    c->x = _x;
    c->y = _y;
}


int getx(struct coordinates* c)
{
    return c->x; 
}


int gety(struct coordinates* c)
{
    return c->y; 
}


void setx(struct coordinates* c, int _x)
{
    c->x = _x;
}


void sety(struct coordinates* c, int _y)
{
    c->y = _y;
}


// fonctions sur les deplacements

void init_movement(struct movement* mov, void (*f) (struct coordinates*))
{
    mov->shift = f;
}

void shifting(struct movement* mov, struct coordinates* c)
{
    (*(mov->shift))(c);
}



// fonctions sur les poissons

void init_fish(struct fish* f, char* _name, int width, int height, int x, int y, void (*shift) (struct coordinates*))
{
    strcpy(f->name, _name);

    init_coordinates(&(f->dimension), width, height);
    init_coordinates(&(f->location), x, y);
    init_movement(&(f->move), shift);
}


void shift_fish(struct fish* f)
{
    shifting(&(f->move),&(f->location));
}

struct coordinates get_fish_location(struct fish* f)
{
    struct coordinates tmp;
    tmp.x = getx(&(f->location));
    tmp.y = gety(&(f->location));
    return tmp;
}

void set_fish_location(struct fish* f, int x, int y)
{
    init_coordinates(&(f->location), x, y);
}


struct coordinates get_fish_dimension(struct fish* f)
{
    struct coordinates tmp;
    tmp.x = getx(&(f->dimension));
    tmp.y = gety(&(f->dimension));
    return tmp;
}

void set_fish_dimension(struct fish* f, int width, int height)
{
    init_coordinates(&(f->dimension), width, height);
}


void set_fish_move(struct fish* f, void (*_shift) (struct coordinates*))
{
    init_movement(&(f->move), _shift);
}

void print_fish_loc(struct fish* f)
{
    struct coordinates c = get_fish_location(f);
    printf("x = %d\n", getx(&c));
    printf("y = %d\n", gety(&c));

}

void print_fish_name(struct fish* f)
{
    printf("Il s'agit d'un %s\n", f->name);
}