#include <assert.h>
#include <stdlib.h>
#include <stdio.h>

#include "../src/model/view.h"

void test_init_view() {
    printf("%s", __func__);

    struct view v;
    init_view(&v, 1, 30, 20, 100, 50);
    assert(v.id == 1);
    assert(v.position[0] == 30);
    assert(v.position[1] = 20);
    assert(v.dimension[0] == 100);
    assert(v.dimension[1] == 50);

    printf("\t\t\tOK\n");
}

void test_get_view_id() {
    printf("%s", __func__);

    struct view v;
    init_view(&v, 1, 30, 20, 100, 50);
    assert(get_view_id(&v) == 1);

    printf("\t\tOK\n");
}

void test_get_view_dimension() {
    printf("%s", __func__);

    struct view v;
    init_view(&v, 1, 30, 20, 100, 50);
    const int* dimension = get_view_dimension(&v);
    
    assert(v.dimension[0] == 100);
    assert(v.dimension[1] == 50);
    assert(dimension[0] == 100);
    assert(dimension[1] == 50);

    printf("\t\tOK\n");
}

void test_get_view_position() {
    printf("%s", __func__);

    struct view v;
    init_view(&v, 1, 30, 20, 100, 50);

    const int* position = get_view_position(&v);
    
    assert(v.position[0] == 30);
    assert(v.position[1] == 20);
    assert(position[0] == 30);
    assert(position[1] == 20);

    printf("\t\tOK\n");
}

void test_set_view_dimension() {
    printf("%s", __func__);

    struct view v;
    init_view(&v, 1, 30, 20, 100, 50);
    set_view_dimension(&v, 80, 120);

    assert(v.dimension[0] == 80);
    assert(v.dimension[1] == 120);

    printf("\t\tOK\n");
}

void test_set_view_id() {
    printf("%s", __func__);

    struct view v;
    init_view(&v, 1, 30, 20, 100, 50);
    set_view_id(&v, 3);
    assert(v.id == 3);

    printf("\t\tOK\n");
}


void test_set_view_position() {
    printf("%s", __func__);

    struct view v;
    init_view(&v, 1, 30, 20, 100, 50);
    set_view_position(&v, 40, 10);

    assert(v.position[0] == 40);
    assert(v.position[1] == 10);

    printf("\t\tOK\n");
}

void test_view() {
    printf("\nTEST FILE : %s\n\n", __FILE__);
    test_init_view();
    test_get_view_dimension();
    test_get_view_id();
    test_get_view_position();
    test_set_view_id();
    test_set_view_position();
    test_set_view_dimension();
}

int main(){
    test_view();
    return 0;
}