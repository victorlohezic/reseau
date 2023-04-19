#ifndef _TEST_QUEUE_H_
#define _TEST_QUEUE_H_

#include <stdio.h>

#include <assert.h>
#include <stdlib.h>
#include <stddef.h>
#include <string.h>

#include "../src/model/queue.h"

void test_init_queue();

void test_go_to_next();

void test_add_element();

void test_del_element_head();

void test_free_queue();

void test_update_queue();



#endif //_TEST_QUEUE_H_