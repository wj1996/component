#include<stdio.h>
#include<stdlib.h>
int main() {

    char *str = "aaa bbb ccc";
    char str2[] = "aaa bbb ccc";

    char *p = strtok(str2," ");
    printf("%s\n",p);
    char *p2 = strtok(str," ");
    printf("%c\n",p2);
}