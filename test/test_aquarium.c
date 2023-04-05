#include "test_aquarium.h"


void test_add_view()
{
    printf("%s", __func__);

    struct aquarium a;
    a.dimension[0] = 128;
    a.dimension[1] = 337;

    a.nb_fishes = 0;

    for(int k=0; k<5; k++) {
        init_view(a.views+k, k, 10, 10, 10, 10);
    }
    
    a.nb_views = 5;

    struct view new_v;
    init_view(&new_v, 5, 10, 10, 10, 10);
    assert(add_view(&a, &new_v) == 0);

    struct view new_v2;
    init_view(&new_v2, 3, 10, 10, 10, 10);

    assert(add_view(&a, &new_v2) == -1);
    assert(a.nb_views == 6);
    assert(a.nb_views != 5);

    struct view new_v3;
    for (int k=6; k<MAX_VIEWS; k++) {
        init_view(&new_v3, k, 10, 10, 10, 10);
        assert(add_view(&a, &new_v3) == 0);
    }

    init_view(&new_v3, 10, 10, 10, 10, 10);
    assert(add_view(&a, &new_v3) == -1);


    struct view new_v4;
    init_view(&new_v4, -5, 10, 10, 10, 10);
    assert(add_view(&a, &new_v4) == -1);

    struct view new_v5;
    init_view(&new_v5, 5, -10, 10, 10, 10);
    assert(add_view(&a, &new_v5) == -1);

    struct view new_v6;
    init_view(&new_v6, 5, 200, 10, 10, 10);
    assert(add_view(&a, &new_v6) == -1);


    struct view new_v7;
    init_view(&new_v7, 5, 10, 10, 10, 400);
    assert(add_view(&a, &new_v7) == -1);


    //show(&a);

    printf("\t\tOK\n");
}


void test_del_view()
{
    printf("%s", __func__);

    struct aquarium a;
    a.nb_fishes = 0;
    a.dimension[0] = 128;
    a.dimension[1] = 337;

    for(int k=0; k<5; k++) {
        init_view(a.views+k, k, k+10, k+20, k+30, k+40);
    }
    
    a.nb_views = 5;

    assert(del_view(&a, 2) == 0);
    assert(a.nb_views == 4);

    //show(&a);
    assert(del_view(&a, 2) == -1);
    assert(del_view(&a, 6) == -1);

    struct view v;
    init_view(&v, 6, 11, 11, 11, 11);
    add_view(&a, &v);

    //show(&a);

    assert(del_view(&a, 0) == 0);
    assert(del_view(&a, 6) == 0);

    printf("\t\tOK\n");
}


void test_save_load()
{
    printf("%s", __func__);

    struct aquarium a1;

    a1.nb_fishes = 0;
    a1.dimension[0] = 20000;
    a1.dimension[1] = 30000;
    char* path = "save_a1.txt";

    for(int k=0; k<7; k++) {
        init_view(a1.views+k, k, k+10, k+100, k+1000, k+10000);
    }
    a1.nb_views = 7;

    int rs = save_aquarium(&a1, path);
    assert(rs == 0);

    struct aquarium a2;

    int rl = load(&a2, path);
    assert(rl == 0);

    assert(get_aquarium_dimension(&a2)[0] == 20000);
    assert(get_aquarium_dimension(&a2)[1] == 30000);
    assert(a2.nb_fishes == 0);
    assert(a2.nb_views == 7);

    for(int j=0; j<a2.nb_views; j++) {
        assert(get_view_id(a2.views+j) == j);
        assert(get_view_position(a2.views+j)[0] == j+10);
        assert(get_view_dimension(a2.views+j)[1] == j+10000);

    }

    remove(path);

    //show(&a2);
    printf("\t\tOK\n");
}





int main() 
{
    printf("\nTEST FILE : %s\n\n", __FILE__);

    test_add_view();
    test_del_view();
    test_save_load();

    return 0;
}