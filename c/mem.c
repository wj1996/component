#include<stdio.h>
#include<string.h>
void test() {

    int* p = malloc(sizeof(int) * 10); //申请10个int地址空间
    for (int i = 0; i < 10; i++) {
        *(p + i) = i;
    }

    for (int i = 0; i < 10; i++) {
        printf("%d\n",*(p+i));
    }

    //释放空间
    if (NULL != p) {
        free(p);
    }
}

void test2() {
    int* p = malloc(sizeof(int) * 10);
    memset(p,0,40);
    for (int i = 0; i < 10; i++) {
        printf("%d\n",*(p+i));
    }
}
int main() {
    // test();
    test2();
    return 0;
}