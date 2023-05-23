#include <stdio.h>
#include <stdarg.h>

int already_open = 0;

void log_message(const char* format, ...) {
     FILE* file;
    if (already_open) {
        file = fopen("controller.log", "a"); 
    } else {
        file = fopen("controller.log", "w");
        already_open = 1;
    }

    if (file != NULL) {
        va_list args;
        va_start(args, format);

        vfprintf(file, format, args); 

        va_end(args);
        fclose(file); 
    } else {
        printf("Erreur : Impossible d'ouvrir/ou créer le fichier controller.log\n");
    }
}