#include<stdio.h>
#include<string.h>

void test() {
    while (1) {
        char str[200];
        scanf("%[^\n]",str);
        getchar();
        printf("%s\n",str);
        // memset(str,0,sizeof(str));
    }
}
void test2() {
    char str[100] = "www.badiu.com";
    char* p2 = str;
    for (int i = 0; i < 100; i++) {
        printf("%c---",p2+i);
    }
    printf("%s\n",str);
    char* p = strtok(str,".");
    if (NULL == p) {
        printf("failure....\n");
        return;
    }
    printf("%s\n",str);
    for (int i = 0; i < 100; i++) {
        printf("%c---",p2++);
    }
}
int main() {
    // test();
    test2();
    return 0;
}