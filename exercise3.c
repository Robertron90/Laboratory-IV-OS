#include <stdio.h>
#include <stdlib.h>
#define TRUE 1
void type_prompt();
void read_command(char *command);
int main(void) {
return EXIT_SUCCESS;
}

void type_prompt() {
printf("> ");
}
void read_command(char *command){
scanf("%s", command);
}

char command[10];
while (TRUE) {
type_prompt();
read_command(command);
system(command);
}
