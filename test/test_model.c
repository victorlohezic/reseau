#include "test_model.h"



// testing structure movement

void test_init_movement()
{
    printf("%s", __func__);

    void f (int* c)
    {
        c[0] +=1;
        c[1] +=2;
    }
    
    assert(1);

    struct movement m;
    init_movement(&m, &f);

    int c[2];
    c[0] = 1; c[1] = 1;

    (*(m.shift))(c);

    assert(c[0] == 2);
    assert(c[1] == 3);

    printf("\tOK\n");
}


void test_shifting()
{
    printf("%s", __func__);

    void f (int* c)
    {
        c[0] += 10;
        c[1] += 20;
    }

    assert(1);

    struct movement m;
    init_movement(&m, &f);

    int c[2];
    c[0] = 3; c[1] = 3;

    shifting(&m, c);

    assert(c[0] == 13);
    assert(c[1] == 23);
    assert(c[0] != 3);
    assert(c[1] != 3);



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

    void shift (int* c)
    {
        c[0] +=1;
        c[1] +=2;
    }

    assert(width == 10);

    struct fish f;

    init_fish(&f, name, width, height, x, y, &shift);

    assert(f.dimension[0] == 10);
    assert(f.dimension[1] == 20);
    assert(f.position[0] == 4);
    assert(f.position[1] == 5);
    assert(strcmp(f.name, "poisson rouge") == 0);
    assert(strcmp(f.name, "poissie rouge") != 0);

    shifting(&(f.move), f.position);
    assert(f.position[0] == 5);
    assert(f.position[1] == 7);


    printf("\t\tOK\n");
}


void test_get_fish_name()
{
    printf("%s", __func__);

    char name[] = "Poisson Chouchou";
    int width = 10;
    int height = 20;
    int x = 4;
    int y = 5;

    void shift (int* c)
    {
        c[0] +=2;
        c[1] +=3;
    }

    assert(width == 10);

    struct fish f;

    init_fish(&f, name, width, height, x, y, &shift);

    assert(strcmp(get_fish_name(&f),"Poisson Chouchou") == 0);
    assert(strcmp(get_fish_name(&f),"poisson Chouchou") != 0);
    assert(strcmp(get_fish_name(&f),"poisson Chichon") != 0);


    printf("\tOK\n");
}

void test_get_fish_pos()
{
    printf("%s", __func__);

    char name[] = "poisson chat";
    int width = 10;
    int height = 20;
    int x = 4;
    int y = 5;

    void shift (int* c)
    {
        c[0] +=2;
        c[1] +=3;
    }

    assert(width == 10);

    struct fish f;

    init_fish(&f, name, width, height, x, y, &shift);

    assert(get_fish_position(&f)[0] == 4);
    assert(get_fish_position(&f)[1] == 5);


    shifting(&(f.move), f.position);
 
    assert(get_fish_position(&f)[0]  != 4);
    assert(get_fish_position(&f)[1]  != 5);
    assert(get_fish_position(&f)[0]  == 6);
    assert(get_fish_position(&f)[1]  == 8);



    printf("\tOK\n");
}


void test_set_fish_pos()
{
    printf("%s", __func__);

    char name[] = "poisson chien";
    int width = 10;
    int height = 20;
    int x = 4;
    int y = 5;

    void shift (int* c)
    {
        c[0] +=2;
        c[1] +=3;
    }

    assert(width == 10);

    struct fish f;

    init_fish(&f, name, width, height, x, y, &shift);

    assert(get_fish_position(&f)[0] == 4);
    assert(get_fish_position(&f)[1] == 5);


    set_fish_position(&f, 6, 9);
 
    assert(get_fish_position(&f)[0] != 4);
    assert(get_fish_position(&f)[1] != 5);
    assert(get_fish_position(&f)[0] == 6);
    assert(get_fish_position(&f)[1] == 9);

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

    void shift (int* c)
    {
        c[0] +=2;
        c[1] +=3;
    }

    assert(width == 10);

    struct fish f;

    init_fish(&f, name, width, height, x, y, &shift);


    assert(get_fish_dimension(&f)[0] != 4);
    assert(get_fish_dimension(&f)[1] != 5);
    assert(get_fish_dimension(&f)[0] == 10);
    assert(get_fish_dimension(&f)[1] == 20);

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

    void shift (int* c)
    {
        c[0] +=2;
        c[1] +=3;
    }

    assert(width == 10);

    struct fish f;

    init_fish(&f, name, width, height, x, y, &shift);

    assert(get_fish_dimension(&f)[0] == 10);
    assert(get_fish_dimension(&f)[1] == 20);


    set_fish_dimension(&f, 7, 10);
 
    assert(get_fish_dimension(&f)[0] != 10);
    assert(get_fish_dimension(&f)[1] != 20);
    assert(get_fish_dimension(&f)[0] == 7);
    assert(get_fish_dimension(&f)[1] == 10);

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

    void shift (int* c)
    {
        c[0] +=2;
        c[1] +=3;
    }

    assert(width == 10);

    struct fish f;

    init_fish(&f, name, width, height, x, y, &shift);

    shifting(&(f.move), f.position);
    assert(f.position[0] == 6);
    assert(f.position[1] == 8);

    void shift2 (int* c)
    {
        c[0] +=10;
        c[1] -=20;
    }

    set_fish_move(&f, &shift2);
    shifting(&(f.move), f.position);
    assert(f.position[0] != 6);
    assert(f.position[1] != 8);
    assert(f.position[0] == 16);
    assert(f.position[1] == -12);

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

    void shift (int* c)
    {
        c[0] +=1;
        c[1] +=2;
    }
    assert(width == 10);
    struct fish f;
    init_fish(&f, name, width, height, x, y, &shift);

    for(int k = 0; k<10; k++){
        assert(get_fish_position(&f)[0] == k);
        assert(get_fish_position(&f)[1] == 2*k);
        shift_fish(&f);
    }

    printf("\t\tOK\n");
}


void test_add_future_pos()
{
    printf("%s", __func__);

    char name[] = "poisson dagetruqué";
    int width = 10;
    int height = 20;
    int x = 0   ;
    int y = 0;

    void shift (int* c)
    {
        c[0] +=1;
        c[1] +=2;
    }
    assert(width == 10);
    struct fish f;
    init_fish(&f, name, width, height, x, y, &shift);

    assert(f.move.future_positions == NULL);

    int pos1[2] = {11,22};
    add_future_position(&f, pos1, 5);
    assert(f.move.future_positions != NULL);
    assert(f.move.future_positions->positions[1] == 22);
    assert(f.move.future_positions->next == NULL);

    int pos2[2] = {13,18};
    add_future_position(&f, pos2, 10);
    assert(f.move.future_positions != NULL);
    assert(f.move.future_positions->positions[1] == 22);
    assert(f.move.future_positions->next->positions[0] == 13);   

    int pos3[2] = {88,77};
    add_future_position(&f, pos3, 1);
    assert(f.move.future_positions != NULL);
    assert(f.move.future_positions->positions[1] == 77);
    assert(f.move.future_positions->next->positions[0] == 11); 

    //print_queue(f.move.future_positions);
    free_fish(&f);
    printf("\tOK\n");
}


void test_next_future_pos()
{
    printf("%s", __func__);

    char name[] = "nossiop le myope";
    int width = 10;
    int height = 20;
    int x = 0   ;
    int y = 0;

    void shift (int* c)
    {
        c[0] +=1;
        c[1] +=2;
    }
    assert(width == 10);
    struct fish f;
    init_fish(&f, name, width, height, x, y, &shift);

    assert(f.move.future_positions == NULL);
    int future_pos[3];
    assert(next_future_position(&f, future_pos) == -1);


    int pos1[2] = {11,22};
    add_future_position(&f, pos1, 5);
    assert(next_future_position(&f, future_pos) == 0);
    assert(future_pos[0] == 11 && future_pos[1] == 22);
    assert(future_pos[2] <= 5);

    int pos2[2] = {13,18};
    add_future_position(&f, pos2, 10);
    assert(next_future_position(&f, future_pos) == 0);
    assert(future_pos[0] == 11 && future_pos[1] == 22);
    assert(future_pos[2] <= 5);

    int pos3[2] = {88,77};
    add_future_position(&f, pos3, 1);
    assert(next_future_position(&f, future_pos) == 0);
    assert(future_pos[0] == 88 && future_pos[1] == 77);
    assert(future_pos[2] <= 1);
    free_fish(&f);
    
    printf("\tOK\n");


}



int main(void)
{
    printf("\nTEST FILE : %s\n\n", __FILE__);

    test_init_movement();
    test_shifting();

    test_init_fish();
    test_get_fish_name();
    test_get_fish_pos();
    test_set_fish_pos();
    test_get_fish_dim();
    test_set_fish_dim();
    test_set_fish_mov();
    test_shift_fish();
    test_add_future_pos();
    test_next_future_pos();
    
    return 0;
}