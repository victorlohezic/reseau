#ifndef _MOVE_H_
#define _MOVE_H_

#include <stdio.h>
#include <stdlib.h>
#include <sys/time.h>
#include <string.h>
#include <assert.h>

#include "aquarium.h"

extern int aquarium_x;
extern int aquarium_y;

//RandomWayPoint movement 
void random_path(struct fish*);

//Fish bouncing at the borders -> DvdBouncing
void dvd_bouncing(struct fish*);

//Fish going left->right/right->left -> 
void horizontal_path(struct fish*);


//returns the right function for the move name
void (*get_move_function(char* move_name)) (struct fish*);


#endif // _MOVE_H_
