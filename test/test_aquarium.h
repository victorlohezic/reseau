#ifndef _TEST_AQUARIUM_H_
#define _TEST_AQUARIUM_H_

#include <stdio.h>

#include <assert.h>
#include <stdlib.h>
#include <stddef.h>
#include <string.h>

#include "../src/model/aquarium.h"

void test_add_view();
void test_del_view();
void test_add_fish();
void test_del_fish();
void test_save_load();
void test_find_view();
void test_fishes_in_view();

#endif //_TEST_AQUARIUM_H_