#include <stdio.h>
#include <stdlib.h>
int main(void) {
  int i;
  for (i = 0; i < 3; i++) {
  fork();
  sleep(5);
  }
  return EXIT_SUCCESS;
}
