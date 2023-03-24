#ifndef _TEST_MODEL_H_
#define _TEST_MODEL_H_

#include <stdio.h>

#include <assert.h>
#include <stdlib.h>
#include <stddef.h>
#include <string.h>

#include "../src/model/model.h"

// testing structure coordinates

void test_init_coord();
void test_getx();
void test_gety();
void test_setx();
void test_sety();


// testing structure movement

void test_init_movement();
void test_shifting();


// testing structure fish

void test_init_fish();
void test_get_fish_loc();
void test_set_fish_loc();
void test_get_fish_dim();
void test_set_fish_dim();
void test_set_fish_mov();
void test_shift_fish();






#endif //_TEST_MODEL_H_