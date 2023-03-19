#include "model.h"


void f1(struct coordinates* c)
{
    setx(c, getx(c)+1);
    sety(c, gety(c)+1);
}

void f2(struct coordinates* c)
{    
    setx(c, getx(c)+2);
    sety(c, gety(c)-1);
}


int main()
{
    struct coordinates c;
    init_coordinates(&c, 1,2);

    struct movement m;
    init_movement(&m, &f1);

    struct fish f;
    init_fish(&f, "Blob fish", 2, 2, 1, 2, &f1);

    print_fish_name(&f);

    for (int k=0; k<10; k++) {
        print_fish_loc(&f);
        shift_fish(&f);
    }

    set_fish_move(&f, &f2);

    for (int k=0; k<10; k++) {
        print_fish_loc(&f);
        shift_fish(&f);
    }

    return 0;
}