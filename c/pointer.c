#include<stdio.h>
#include<string.h>

void bubble(int* p,int length) {
    for (int i = 0; i < length - 1; i++) {
        for (int j = 0; j < length - 1 - i;j++) {
            int temp = 0;
            if (*(p+j) < *(p+j+1)) {
                temp = *(p+j);
                *(p+j) = *(p+j+1);
                *(p+j+1) = temp;
            }
        }
    }
}

void test1() {
    int arr[] = {20,30,10,40,50,70,100,90,60};
    int length = sizeof(arr) / sizeof(int);
    bubble(arr,length);

    for (int i = 0; i < length; i++) {
        printf("%d\n",arr[i]);
    }

}

void test2() {
    char ch[] = "hello world";
    char* p = strchr(ch,'o');
    printf("%s\n",p);
}


char* mystrchr(const char* p,char ch) {
    int i = 0;
    while (p[i] != '\0') {
        // printf("--%c---\n",p[i]);
        if (p[i] == ch) {
            return &p[i];
        }
        i++;

    }
    return NULL;
}

char* mystrchr2(const char* p,char ch) {
    char* addr = p;
    while (*addr != '\0') {
        if (*addr == ch) {
            return addr;
        }
        addr++;
    }
    return NULL;
}

void test3() {
    char* str = "hello world";
    printf("%s\n",mystrchr(str,'o'));
    printf("%s\n",mystrchr(str,' '));
    printf("%s\n",mystrchr2(str,'l'));

}

void test4() {
    char* arr[] = {"abc","def","lmn"};
    printf("%c\n",*arr[0]);
    printf("%c\n",*(arr[0]+1));
}

void test5() {

    int a = 10;
    int* p = &a;
    int** pp = &p;
    //*pp = p = &a 
}
void swap(int* a,int* b) {
    int temp = *a;
    *a = *b;
    *b = temp;
}
void test6() {
    int a = 10;
    int b = 20;
    swap(&a,&b);
    printf("%d,%d",a,b);
}
char* mystrstr(char* src,char* target) {
    int i = 0;
    int j = 0;
    while (src[i] != '/0') {
    //    while ()
    }
    //返回值结果
    return NULL;
}

void test8() {
    char arr[] = "hello world";
    char* p;
    p = arr;
    // *p = 'A';
    // printf("%c\n",*p);
    // p++;
    // printf("%c\n",*p);

    printf("%d\n",sizeof(arr));
    printf("%d\n",sizeof(p));
    printf("%d\n",strlen(arr));
    printf("%d\n",strlen(arr));

}

void func() {
    static int a = 10;
    printf("%d\n",a++);
}

void test10() {
    func();
    func();
}
static int global_a = 10;
int global_b = 10;
void test11() {
    global_a = 20;
    global_b = 20;
    printf("%d\n",global_a);
    printf("%d\n",global_b);
}

int main() {
    int a;
    int* p = &a;
    // printf("%X\n",p);
    // printf("%p\n",&a);
    //通过指针修改值
    *p = 10;
    // printf("%d\n",a);
    // printf("%d\n",sizeof(p));

    // p = 100;
    // printf("%c\n",*p);
    // test2();
    // test3();
    // test4();
    // test6();
    // test8();
    // test10();
    test11();
    return 0;

    
}