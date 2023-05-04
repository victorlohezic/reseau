#ifndef _MOVE_H_
#define _MOVE_H_

#include <stdio.h>
#include <stdlib.h>
#include <sys/time.h>
#include <string.h>
#include <assert.h>


//RandomWayPoint movement 
void random_path(int* c);

//Fish bouncing at the borders -> DvdBouncing
void dvd_bouncing(int* c);

//Fish going left->right/right->left -> 
void round_trip(int* c);


//returns the right function for the move name
void (*get_move_function(char* move_name)) (int*);


#endif // _MOVE_H_
