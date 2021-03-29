#include<stdio.h>
#include<string.h>
#include<stdlib.h>
int main() {

    // printf("%d",123);
    // char ch[100];
    // gets(ch);
    // printf("%s\n",ch);

    // scanf("%[^\n]",ch);
    // printf("%s\n",ch);

    // fgets(ch,100,stdin);
    // printf("%s",ch);
    // fputs(ch,stdout);
    
    // scanf("%[^\n]",ch); //接收换行结束
    // printf("%s\n",ch);
    // int a,b,c; 
    // scanf("%1d%2d%3d",&a,&b,&c);
    // printf("%d,%d,%d",a,b,c);
    //  char cha;
    // scanf("%*[a-z]%c",&cha);
    // printf("%c",cha);
    // char *str = "hello 123";
    // char *s;
    // char ch2[100];
    // sscanf(str,"%s %d",ch,&a);
    // printf("%s,%d\n",ch,a);
    // gets(s);
    // sscanf(s,"%s %[^\n]s",ch,ch2);
    // printf("%s\n",ch);
    // printf("%s\n",ch2);

    // char *p = strchr(str,'1');
    // printf("%s\n",p);
    // p = strstr(str,"llo");
    // printf("%s\n",p);

    // char *p = strtok(str," ");
    // printf("%s\n",p);
    char *str2 = "www.baidu.com";
    char *p2 = strtok(str2,".");
    printf("%s\n",p2);
    printf("%s\n",str2);
    printf("%s\n","hello world");

    return 0;
}