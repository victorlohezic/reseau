#include "test_aquarium.h"


void test_add_view()
{
    printf("%s", __func__);

    struct aquarium a;
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

    //show(&a);

    printf("\t\tOK\n");
}


void test_del_view()
{
    printf("%s", __func__);

    struct aquarium a;
    a.nb_fishes = 0;

    for(int k=0; k<5; k++) {
        init_view(a.views+k, k, 10, 10, 10, 10);
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

    //show(&a);

    printf("\t\tOK\n");
}





int main() 
{
    test_add_view();
    test_del_view();


    return 0;
}