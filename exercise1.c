#include <stdio.h>
#include <sys/types.h>

void main()
{
    pid_t n = getpid();
    printf("Hello from parent [PID - %d]\n, n);
    fork();
    n = getpid();
    printf("Hello from child [PID - %d]\n, n);
}
