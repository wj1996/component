#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<stddef.h>
struct student {
    char name[100];
    int age;
    int score;
    char sex;
};
struct s2 {
    char* name;
    int age;
};
void test1(){
    int length = sizeof(struct student);
    printf("%d\n",length);
}


void test2(){
    struct s2 s1;
    s1.name = malloc(sizeof(char) * 20);
    s1.name = "jack";
    s1.age = 20;
    printf("%s,%d\n",s1.name,s1.age);
    
    struct s2 s2;
    struct s2* p = &s2;
    p->name = malloc(sizeof(char) * 20);
    p->name = "july";
    p->age = 20;
    printf("%s,%d\n",s2.name,s2.age);
    printf("%s,%d\n",p->name,p->age);

}
extern void printAddr(struct s2* s);
void printS2(struct s2* s) {
    printf("name = %s,age = %d\n",s->name,s->age);
    printAddr(s);
    printf("---------------------------------------------------\n");
}
void printAddr(struct s2* s) {
    printf("s address is %p\n",s);
    printf("name address = %p age address = %p\n",s->name,&s->age);
}
void test3() {
    struct s2* p = malloc(sizeof(struct s2));
    strcpy(p->name,"jack");
    p->age = 20;
    struct s2 s;
    s.age = 10;
    s.name = "july";    
    printS2(&s);
    printS2(p);
}

void test4() {
    struct s2  s = {"hello",21};
    int offset = offsetof(struct s2,age);
    printf("%d\n",offset);
    printf("%p\n",s);
    printf("%p\n",&s);
    int* pp = (int *)((char*)&s+offset);
    printf("age is %d\n",*pp);

}
int main() {
    // test1();
    // test2();
    int a = 0;
    // printf("%p\n",&a);
    // test3();
    // printf("-----end----");
    test4();
    exit(0);
    return 0;
}