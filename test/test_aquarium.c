#include "test_aquarium.h"


void test_add_view()
{
    printf("%s", __func__);

    struct view v[5];

    struct aquarium a;

    for(int k=0; k<5; k++) {
        init_view(v+k, k, 10, 10, 10, 10);
        a.views[k] = v+k;
    }
    
    a.nb_views = 5;

    a.nb_fishes = 0;

    struct view new_v;
    init_view(&new_v, 5, 10, 10, 10, 10);
    assert(add_view(&a, &new_v) == 0);


    printf("\t\tOK\n");
}


void test_del_view();





int main() 
{
    test_add_view();
    //test_del_view();


    return 0;
}