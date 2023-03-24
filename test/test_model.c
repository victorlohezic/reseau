#include "test_model.h"


// testing structure coordinates

void test_init_coord()
{
    printf("%s", __func__);

    struct coordinates c;
    init_coordinates(&c, 1, 2);
    assert(c.x == 1);
    assert(c.y == 2);

    printf("\t\tOK\n");
}


void test_getx()
{
    printf("%s", __func__);

    struct coordinates c;
    init_coordinates(&c, 3, 2);

    assert(getx(&c) == 3);
    assert(getx(&c) != 2);

    printf("\t\tOK\n");
}


void test_gety()
{
    printf("%s", __func__);

    struct coordinates c;
    init_coordinates(&c, 1, 4);

    assert(gety(&c) == 4);
    assert(gety(&c) != 1);

    printf("\t\tOK\n");
}


void test_setx()
{
    printf("%s", __func__);

    struct coordinates c;
    init_coordinates(&c, 1, 2);
    assert(getx(&c) == 1);

    setx(&c, 4);
    assert(getx(&c) == 4);
    assert(getx(&c) != 1);


    printf("\t\tOK\n");
}


void test_sety()
{
    printf("%s", __func__);

    struct coordinates c;
    init_coordinates(&c, 1, 2);
    assert(gety(&c) == 2);

    sety(&c, 6);
    assert(gety(&c) == 6);
    assert(gety(&c) != 2);

    printf("\t\tOK\n");
}


// testing structure movement

void test_init_movement()
{
    printf("%s", __func__);

    void f (struct coordinates* c)
    {
        setx(c, getx(c)+1);
        sety(c, gety(c)+2);
    }

    struct movement m;
    init_movement(&m, &f);

    struct  coordinates c;
    init_coordinates(&c, 1, 1);

    (*(m.shift))(&c);

    assert(getx(&c) == 2);
    assert(gety(&c) == 3);

    printf("\tOK\n");
}


void test_shifting()
{
    printf("%s", __func__);

    void f (struct coordinates* c)
    {
        setx(c, getx(c)+10);
        sety(c, gety(c)+20);
    }

    struct movement m;
    init_movement(&m, &f);

    struct  coordinates c;
    init_coordinates(&c, 3, 3);

    shifting(&m, &c);

    assert(getx(&c) == 13);
    assert(gety(&c) == 23);
    assert(getx(&c) != 3);
    assert(gety(&c) != 3);



    printf("\t\tOK\n");
}


// testing structure fish


void test_init_fish()
{
    printf("%s", __func__);

    char name[] = "poisson rouge";
    int width = 10;
    int height = 20;
    int x = 4;
    int y = 5;

    void shift (struct coordinates* c)
    {
        setx(c, getx(c)+1);
        sety(c, gety(c)+2);
    }

    struct fish f;

    init_fish(&f, name, width, height, x, y, &shift);

    assert(getx(&f.dimension) == 10);
    assert(gety(&f.dimension) == 20);
    assert(getx(&f.location) == 4);
    assert(gety(&f.location) == 5);
    assert(strcmp(f.name, "poisson rouge") == 0);
    assert(strcmp(f.name, "poissie rouge") != 0);

    shifting(&(f.move), &(f.location));
    assert(getx(&f.location) == 5);
    assert(gety(&f.location) == 7);


    printf("\t\tOK\n");
}


void test_get_fish_loc()
{
    printf("%s", __func__);

    char name[] = "poisson chat";
    int width = 10;
    int height = 20;
    int x = 4;
    int y = 5;

    void shift (struct coordinates* c)
    {
        setx(c, getx(c)+2);
        sety(c, gety(c)+3);
    }

    struct fish f;

    init_fish(&f, name, width, height, x, y, &shift);

    struct coordinates loc = get_fish_location(&f);
    assert(getx(&loc) == 4);
    assert(gety(&loc) == 5);


    shifting(&(f.move), &(f.location));
 
    loc = get_fish_location(&f);
    assert(getx(&loc) != 4);
    assert(gety(&loc) != 5);
    assert(getx(&loc) == 6);
    assert(gety(&loc) == 8);



    printf("\tOK\n");
}


void test_set_fish_loc()
{
    printf("%s", __func__);

    char name[] = "poisson chien";
    int width = 10;
    int height = 20;
    int x = 4;
    int y = 5;

    void shift (struct coordinates* c)
    {
        setx(c, getx(c)+2);
        sety(c, gety(c)+3);
    }

    struct fish f;

    init_fish(&f, name, width, height, x, y, &shift);

    struct coordinates loc = get_fish_location(&f);
    assert(getx(&loc) == 4);
    assert(gety(&loc) == 5);


    set_fish_location(&f, 6, 9);
 
    loc = get_fish_location(&f);
    assert(getx(&loc) != 4);
    assert(gety(&loc) != 5);
    assert(getx(&loc) == 6);
    assert(gety(&loc) == 9);



    printf("\tOK\n");
}


void test_get_fish_dim()
{
    printf("%s", __func__);

    char name[] = "poisson lune";
    int width = 10;
    int height = 20;
    int x = 1;
    int y = 4;

    void shift (struct coordinates* c)
    {
        setx(c, getx(c)+2);
        sety(c, gety(c)+3);
    }

    struct fish f;

    init_fish(&f, name, width, height, x, y, &shift);

    struct coordinates dim = get_fish_dimension(&f);

    assert(getx(&dim) != 4);
    assert(gety(&dim) != 5);
    assert(getx(&dim) == 10);
    assert(gety(&dim) == 20);

    printf("\tOK\n");
}


void test_set_fish_dim()
{
    printf("%s", __func__);

    char name[] = "poisson tigre";
    int width = 10;
    int height = 20;
    int x = 4;
    int y = 5;

    void shift (struct coordinates* c)
    {
        setx(c, getx(c)+2);
        sety(c, gety(c)+3);
    }

    struct fish f;

    init_fish(&f, name, width, height, x, y, &shift);

    struct coordinates dim = get_fish_dimension(&f);
    assert(getx(&dim) == 10);
    assert(gety(&dim) == 20);


    set_fish_dimension(&f, 7, 10);
 
    dim = get_fish_dimension(&f);
    assert(getx(&dim) != 10);
    assert(gety(&dim) != 20);
    assert(getx(&dim) == 7);
    assert(gety(&dim) == 10);

    printf("\tOK\n");
}


void test_set_fish_mov()
{
    printf("%s", __func__);

    char name[] = "poisson d'avril";
    int width = 10;
    int height = 20;
    int x = 4;
    int y = 5;

    void shift (struct coordinates* c)
    {
        setx(c, getx(c)+2);
        sety(c, gety(c)+3);
    }

    struct fish f;

    init_fish(&f, name, width, height, x, y, &shift);

    shifting(&(f.move), &(f.location));
    assert(getx(&f.location) == 6);
    assert(gety(&f.location) == 8);

    void shift2 (struct coordinates* c)
    {
        setx(c, getx(c)+10);
        sety(c, gety(c)-20);
    }

    set_fish_move(&f, &shift2);
    shifting(&(f.move), &(f.location));
    assert(getx(&f.location) != 6);
    assert(gety(&f.location) != 8);
    assert(getx(&f.location) == 16);
    assert(gety(&f.location) == -12);

    printf("\tOK\n");
}


void test_shift_fish()
{
    printf("%s", __func__);

    char name[] = "poisson danal";
    int width = 10;
    int height = 20;
    int x = 0   ;
    int y = 0;

    void shift (struct coordinates* c)
    {
        setx(c, getx(c)+1);
        sety(c, gety(c)+2);
    }

    struct fish f;
    init_fish(&f, name, width, height, x, y, &shift);

    struct coordinates loc;
    for(int k = 0; k<10; k++){
        loc = get_fish_location(&f);
        assert(getx(&loc) == k);
        assert(gety(&loc) == 2*k);
        shift_fish(&f);
    }

    printf("\t\tOK\n");
}






int main(void)
{
    test_init_coord();
    test_getx();
    test_gety();
    test_setx();
    test_sety();
    printf("\n");

    test_init_movement();
    test_shifting();
    printf("\n");

    test_init_fish();
    test_get_fish_loc();
    test_set_fish_loc();
    test_get_fish_dim();
    test_set_fish_dim();
    test_set_fish_mov();
    test_shift_fish();
    
    return 0;
}