#include<stdio.h>


void func() {
    printf("hello world\n");
}
int add(int a,int b) {
    return a + b;
}

int p(char* str,char* str2) {
    printf("%s,%s\n",str,str2);
    return 0;
}

void test() {
    printf("%p\n",&func);
    void(*my)() = &func;
    my();
}
typedef void(FUN_TYPE)();
// typedef int(FUN_TYPE2)(a,b);
typedef int(FUN_TYPE3)(int,int);
void test2() {
    FUN_TYPE *fc = &func;
    fc();
}

void test3() {
    // FUN_TYPE2 *fc2 = &add;
    // int result = fc2(3,4);
    // printf("%d\n",result);
    // fc2 = &p;
    // fc2("hello","world");
    // FUN_TYPE3 *fc3 = &p;
    // printf("%d\n",fc3(1,2));
    // fc3("hello","world");
    
}

int count1(int a,int b) {
    return a + b;
}
int count2(int a,int b) {
    return a + b + 10;
}
int count3(int a,int b){
    return a + b - 10;
}

void test4() {
    int(*funcP)(int,int) = count2;
    printf("%d\n",funcP(10,20));
}

void test5(int(*funcP)(int,int)) {
    int a = 10;
    int b = 20;
    int ret = funcP(a,b);
    printf("%d\n",ret);
}
void p1() {
    printf("hello\n");
}
void p2() {
    printf("hello2\n");
}
void p3() {
    printf("hello3\n");
}
void test6() {
    void(*func_array[3])();
    func_array[0] = p1;
    func_array[1] = p2;
    func_array[2] = p3;
    for (int i = 0; i < 3; i++) {
        func_array[i]();
    }
}

void printArray(void* arr,int eleSize,int len) {
    char* start = arr;
    for (int i = 0; i < len; i++) {
        printf("%d\n",start + i * eleSize);
    }
}


int main() {
    // test2();
    // test3();
    // test4();
    // test5(count1);
    // test5(count2);
    test6();
    return 0;
}