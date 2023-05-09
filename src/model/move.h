#ifndef _MOVE_H_
#define _MOVE_H_

#include <stdio.h>
#include <stdlib.h>
#include <sys/time.h>
#include <string.h>
#include <assert.h>


//RandomWayPoint movement 
void random_path(int* last_pos, int* size);

//Fish bouncing at the borders -> DvdBouncing
void dvd_bouncing(int* last_pos, int* size);

//Fish going left->right/right->left -> 
void round_trip(int* last_pos, int* size);


//returns the right function for the move name
void (*get_move_function(char* move_name)) (int*, int*);


#endif // _MOVE_H_
