#include "prompt_command.h"

void prompt_add_view(char* command, struct aquarium* controller_aquarium) {
    int id_view, x, y, width, height;
    if (sscanf(command, "add view N%i %dx%d+%d+%d", &id_view, &x, &y, &width, &height) != 5)  {
        return;
    }
    struct view new_view;
    init_view(&new_view, id_view, x, y, width, height);

    if (add_view(controller_aquarium, &new_view) == -1) {
        return;
    }
    printf("-> view added\n");
}

void prompt_del_view(char* command, struct aquarium* controller_aquarium) {
    int id_view;
    if (sscanf(command, "del view N%i", &id_view) != 1) {
        return;
    }

    if (del_view(controller_aquarium, id_view) == -1) {
        return;
    }
    printf("-> view N%i deleted\n", id_view);
}