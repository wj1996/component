#include<stdio.h>
#include<string.h>
#include<stdlib.h>
/*
字符串操作
*/

struct node {
    void* data;
    struct node* next;
};

void p(char* str) {
    if (str == NULL) return;
    printf("%s\n",str);
}
void pd(int num) {
    printf("%d\n",num);
}

int length(char* str) {
    if (NULL == str) return -1;
    return strlen(str);
}
char* trim(char* str) {
    if (NULL == str) return str;
    char c = *str;
    //切割前面的空格
    while (c == ' ') {
        c = *(++str);
    }
    //切割后面的空格
    int i = -1;
    for (i = length(str) - 1; i > 0; i--) {
        if (*(str+i) != ' ') break; 
    }
    //拷贝字符串
    int len = sizeof(char) * i + 2;
    char* ret = malloc(len);
    memset(ret,0,len);
    strncpy(ret,str,i+1);
    return ret;
}

char* trim2(char* str) {
    if (NULL == str) return str;
    char c = *str;
    //切割前面的空格
    while (c == ' ' || c == '\n') {
        c = *(++str);
    }
    //切割后面的空格
    int i = -1;
    for (i = length(str) - 1; i > 0; i--) {
        if (*(str+i) != ' ') break; 
    }
    //拷贝字符串
    int len = sizeof(char) * i + 2;
    char* ret = malloc(len);
    memset(ret,0,len);
    strncpy(ret,str,i+1);
    return ret;
}
char* substring(char* str,int beginIndex) {
    if (beginIndex < 0 || beginIndex > strlen(str) - 1) {
        return str;
    }
    int len = strlen(str) - beginIndex + 1;
    char* ret = malloc(sizeof(char) * len);
    memset(ret,0,len);
    strncpy(ret,str+beginIndex,len-1);
}
//包头不包尾
char* sbustring(char* str,int beginIndex,int endIndex) {
    int len = strlen(str);
    if (beginIndex < 0 || beginIndex > len - 1 || endIndex < 0 || endIndex < beginIndex || endIndex > len - 1) {
        return str;
    }
    len = endIndex - beginIndex + 1;
    char* ret = malloc(sizeof(char) * len);
    memset(ret,0,len);
    strncpy(ret,str+beginIndex,len - 1);
    return ret;
}
int indexOf(char* str,char* match) {
    char* ret = strstr(str,match);
    if (NULL == ret) return -1;
    return ret - str;
}
int lastIndexOf(char* str,char* match) {
    int index = indexOf(str,match);
    char* temp = str;
    while (-1 != index) {
        temp += index+1;
        index = indexOf(temp,match);
    }
    return temp - str - 1;
}

int contains(char* str,char* match) {
    if (NULL == str || match == NULL) return -1;
    return strstr(str,match) == NULL ? -1 : 0;
}

void* split(char* str,char* match) {
    if (NULL == str || NULL == match) return str;
    char* temp = strstr(str,match);
    struct node* head = NULL;
    struct node* ret = NULL;
    int last = 0;
    if (NULL == temp) {

    } else {
        while (NULL != temp) {
            int len = temp - str + 1 - last;
            if (head == NULL) {
                head = malloc(sizeof(struct node));
                head->next = NULL;
                char* data = malloc(sizeof(char) * len);
                memset(data,0,len);
                strncpy(data,str + last,len - 1);
                head->data = data;
                ret = head;
            } else {
                struct node* node = malloc(sizeof(struct node));
                head->next = node;
                head = node;
                char* data = malloc(sizeof(char) * len);
                memset(data,0,len);
                strncpy(data,str + last,len - 1);
                head->data = data;
                head->next = NULL;    
            }
            last = len;
            temp = strstr(temp+1,match);
        }
        struct node* node = malloc(sizeof(struct node));
        char* data = malloc(sizeof(char) * strlen(str) - last + 2);
        memset(data,0,sizeof(data));
        strncpy(data,str + last,sizeof(data) - 1);
        node->data = data;
        node->next = NULL;
        head->next = node;
        head = node;
    }

    
    return ret;
}

void parse(struct node* node) {
    if (NULL == node) return;
    while (NULL != node) {
        p(node->data);
        node = node->next;
    }
}


void test() {
    char* str = "hello world";
    char* str1 = "hello world";
    // printf("length = %d\n",length(str));
    // printf("length = %d\n",length(NULL));
    char* str2 = " hello world  ";
    // printf("1\n");
    // char* ret = trim(str2);
    // printf("1\n");
    // char* ret = substring(str2,2);
    // p(str2);
    // p(ret);
    // printf("%d\n",strlen(ret));
    char* match = "l";
    // pd(indexOf(str1,"h"));
    // p(substring(str1,indexOf(str1,"h")));
    // p(substring(str1,indexOf(str1,"l")));
    // p(substring(str1,lastIndexOf(str1,"l")));
    // p(sbustring(str1,0,3));

    // pd(contains(str1,"w"));
    // pd(contains(str1,"a"));
    // pd(contains(str1,"ld"));

    char* str6 = "www.baidu.com";
    struct node* head = split(str6,".");
    parse(head);
    p("end");
}

int main() {
    test();
    // system("pause");
    return 0;
}