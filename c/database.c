#include<stdio.h>
#include<string.h>
#include<stdlib.h>
struct field {
    char* name;
    int len;
};
struct list {
    struct filed* node;
    struct filed* next;
};

struct table {
    char* tablename;
    struct list* list;
};
int validate(int argc,char* argv) {
    /* if (argc < 2 || argv == NULL) {
        printf("param is not valid....\n");
        return -1;
    } */  
    return 0;
}
char* trim(char* str) {
    if (NULL == str) return;
    while (*str == ' ' || *str == '\n') {
        str++;
    }
    return str;
}
char* getData(char* str,const char* del) {
    if (NULL == str || del == NULL) return NULL;
    while (NULL != str) {
        str = strtok(NULL,del);
        if (str != del) {
            return trim(str);;
        }
    }
    return NULL;
}
int validFormat(char* str,int len,char* match) {
    trim(str);
    if (strncmp(str,match,len) == 0) {
        str++;
        return 0;
    }
    return 1;
}
struct field* getField(char* field) {
    trim(field);
    if (NULL == field) return NULL;
    strtok(field," ");
    struct field* p = malloc(sizeof(struct field));
    p->name = field;

    return p;


}

int checkCreateFormat(char* param) {
    char* data = getData(param," ");
    if (strcmp(data,"table") != 0) {
        printf("create syntax error...\n");
        return -1;
    }
    //获取表名称
    // printf("param:%s\n",param);
    struct table* t = malloc(sizeof(struct table));
    char* tablename = getData(param,"(");
    // printf("tablename:%s\n",tablename);
    char* isValid = strchr(tablename,' ');
    // printf("tablename:%s\n",isValid);
    if (NULL != isValid) {
        printf("tablename is not valid...\n");
        return -1;
    }
    t->tablename = tablename;
    //字段解析
    char* field = getData(param,",");
    if (validFormat(field,1,"(")) {
        printf("syntax is not valid...\n");
        return -1;
    }
    struct field* f  = getField(field);

    
}
int handleCreate(char* param) {
    int ret = checkCreateFormat(param);
    if (ret != 0) {
        return ret;
    }
    
}
int handle(char* command) {
    // printf("command:%s\n",command);
    char* str = strtok(command," ");
    //
    // printf("%s\n",str);
    int result = -1;
    if (strcmp(str,"create") == 0) {
        printf("create....\n");
        //create
        result = handleCreate(str);
    }
    if (strcmp(str,"insert") == 0) {

    }

    if (strcmp(str,"update") == 0) {

    }

    if (strcmp(str,"delete") == 0) {

    }
   /*  while (NULL != str) {
        // printf("%s\n",str);
        // str = strtok(NULL," ");
    } */
    return result;
    
}
int accept() {
    printf("please enter a command....\n");
    char command[500];
    memset(command,0,sizeof(command));
    // gets(command);
    scanf("\n%[^;]",command);
    getchar();
    // printf("accept:%s\n",command);
    handle(command);

}
int main(int argc,char* argv) {
    int ret = validate(argc,argv);
    if (0 != ret) {
        return -1;
    }
    while (ret == 0) {
        ret = accept();
    }
    system("pause");
    return 0;
}