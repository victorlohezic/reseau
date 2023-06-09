#ifndef _TEST_MODEL_H_
#define _TEST_MODEL_H_

#include <stdio.h>

#include <assert.h>
#include <stdlib.h>
#include <stddef.h>
#include <string.h>

#include "../src/model/model.h"


// testing structure movement

void test_init_movement();
void test_shifting();
void test_add_latest_pos(); 

// testing structure fish


void test_init_fish();
void test_get_fish_name();
void test_get_fish_pos();
void test_set_fish_pos();
void test_get_fish_dim();
void test_set_fish_dim();
void test_set_fish_mov();
void test_shift_fish();
void test_add_future_pos(); 
void test_next_future_pos(); 
void test_generate_future_position(); 




#endif //_TEST_MODEL_H_