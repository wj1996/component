#include<stdio.h>
#include<string.h>

struct stu {
    int id;
    char name[20];
    int age;
    int score;
};


char* split(char *str) {
    char* temp = str;
    int length = strlen(temp);
    int start = 0;
    int end = 0;
    int flag = 0;
    for (int i = 0; i < length; i++) {
        if (*(temp+i) == ' ') {
            if (!flag) {
                continue;
            } else {
                end = i;
            }
            
        } else {
            start = i;
        }

    }
    int total = end - start;
    char *data = malloc(sizeof(total));



}

int parseCreateSql(char *str) {
    
}


int main(int argc,char *argv[]) {
    if (argc > 1) {
        for (int i = 0; i < argc;i++) {
            printf("argv[%d] is %s\n",i,argv[i]);
        }
    }
    char str[2000];
    while (1) {
        printf("please ennter a command...\n");
        gets(str);
        printf("%s\n",str);

        memset(str,2000,sizeof(char));
    }
   
    return 0;
}