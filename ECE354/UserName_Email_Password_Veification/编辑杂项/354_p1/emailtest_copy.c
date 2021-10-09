#include <ctype.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>


#define USER_NAME_LENGTH 256
#define EMAIL_LENGTH 512
#define PASSWORD_LENGTH 256

void Get_User_Data(char *message, char *username, const int MAX_LENGTH);
void Check_Email_Name_Validity(char *username);
void Check_At_Sign(char *email);


int main()
{
    char email[EMAIL_LENGTH];
    // ################################################
    // #####  VERIFY EMAIL ADDRESS  ##### 
    // ################################################
    Get_User_Data("Enter email address: ", email, EMAIL_LENGTH);
    // email address have 4 parts in this order
        // name
            // max 32 characters
            // must start with letter
            // may contain [letters or digits]
        // @ symbol 
        // domain name
            // max of 64 characters
            // consists of a domain name and subdomains separated by . 
            // each domain or subdomain  must begin with a letter
            // domain and subdomains may contain only [letters, digits]
        // top-level domain 
            // must be [.edu, .com, .net]
            
    // First, check if input is "splitable": if there is "@"
    // Second, check if "@" is first letter
    // Third, check if name format is valid
    Check_At_Sign(email);


    //Fourth, check domain
    
    char *p = email;
    char at_sign = '@';
    char dot_sign = '.';
    int dot_count = 0;

    // only satisfy format when last 4 char is .edu; .com; or .net
    /*
    if ((p[strlen(email) - 2] != 'u' || p[strlen(email) - 2] != 'm' || p[strlen(email) - 2] != 't') &&
        (p[strlen(email) - 3] != 'd' || p[strlen(email) - 3] != 'o' || p[strlen(email) - 3] != 'e') &&
        (p[strlen(email) - 4] != 'e' || p[strlen(email) - 4] != 'c' || p[strlen(email) - 4] != 'n') &&
        (p[strlen(email) - 5] != '.')){
        printf("Top level domain must be .edu, .com, or .net\n");
        printf("%c \n", p[strlen(email) - 4]);
        printf("%c \n", p[strlen(email) - 3]);
        printf("%c \n", p[strlen(email) - 2]);
        printf("%d \n", strlen(email));
        printf("%s \n", email);
        exit(0);
    }*/

    //cut out domain section
    char domain_copy[EMAIL_LENGTH];
    for (int i = 0; i < strlen(email)-1; i++){
        if(email[i] == at_sign){
            if (strlen(email) - i - 1 > 69)
            {
                printf("Maximum of 64 characters in domain\n");
                exit(0);
            }
            else
            {
                strcpy(domain_copy, email+i+1);
                break;
            }
        }
    }

    printf("%c \n", domain_copy[strlen(domain_copy) - 4]);
    printf("%c \n", domain_copy[strlen(domain_copy) - 3]);
    printf("%c \n", domain_copy[strlen(domain_copy) - 2]);
    printf("%d \n", strlen(domain_copy));
    printf("%s \n", domain_copy);

    if(!isalpha(domain_copy[0])){
        if (domain_copy[0] == dot_sign && isalpha(domain_copy[1]))
        {
            printf("Domain missing\n");

            exit(0);
        }
        else
        {
            printf("Domain or subdomain must begin with letter\n");
            exit(0);
        }
    }



    for (int i = 0; i < strlen(domain_copy)-1; i++)
    {
        if (!isalpha(domain_copy[i]) && !isdigit(domain_copy[i]) && domain_copy[i] != dot_sign)
        {
            printf("Invalid character in domain\n");
            exit(0);
        }

        if(domain_copy[i] == '.'){
            if (!isalpha(domain_copy[i + 1]))
            {
                printf("Domain or subdomain must begin with letter\n");
                exit(0);
            }
            else
            {
                dot_count++;
            }
        }
    }

    
    if ((domain_copy[strlen(domain_copy) - 2] != 'u' || domain_copy[strlen(domain_copy) - 3] != 'd' || domain_copy[strlen(domain_copy) - 4] != 'e') &&
        (domain_copy[strlen(domain_copy) - 2] != 'm' || domain_copy[strlen(domain_copy) - 3] != 'o' || domain_copy[strlen(domain_copy) - 4] != 'c') &&
        (domain_copy[strlen(domain_copy) - 2] != 't' || domain_copy[strlen(domain_copy) - 3] != 'e' || domain_copy[strlen(domain_copy) - 4] != 'n') &&
        (domain_copy[strlen(domain_copy) - 5] != '.'))
    {
        printf("Top level domain must be .edu, .com, or .net\n");
        printf("%c \n", domain_copy[strlen(domain_copy) - 5]);
        printf("%c \n", domain_copy[strlen(domain_copy) - 4]);
        printf("%c \n", domain_copy[strlen(domain_copy) - 3]);
        printf("%c \n", domain_copy[strlen(domain_copy) - 2]);
        printf("%d \n", strlen(email));
        printf("%s \n", email);
        exit(0);
    }
/*
    if(dot_count == 0){
        printf("Top level domain must be .edu, .com, or .net\n");
        exit(0);
    }*/

    printf("Email formatting is correct\n");
    printf("%c \n", p[strlen(email) - 4]);
    printf("%c \n", p[strlen(email) - 3]);
    printf("%c \n", p[strlen(email) - 2]);
    printf("%d \n", strlen(email));
    printf("%s \n", email);
}

void Get_User_Data(char *message, char *data, const int MAX_LENGTH)
{
    printf("%s", message);
    fgets(data, MAX_LENGTH, stdin);
    return;
}

void Check_At_Sign(char *email){
    int at_count = 0;
    int at_position = 0;
    for (int i = 0; i < strlen(email) - 1; i++)
    {
        if (email[i] == '@')
        {
            at_count++;
            at_position = i;
        }
    }

    if (at_count != 0)
    {
        //check if name exists
        if (at_position == 0)
        {
            printf("Name missing\n");
            exit(0);
        }
        else
        {
            //Name exist, so split email into names and "@xxx"
            //printf("at_position = %d\n", at_position);
            const char at_sign[1] = "@";
            char email_copy[EMAIL_LENGTH];
            strcpy(email_copy, email);

            char *p_email_name;
            p_email_name = strtok(email_copy, at_sign);
            //Report any error message here about email name
            Check_Email_Name_Validity(p_email_name);
        }
    }
    else
    {
        printf("missing @\n");
        exit(0);
    }
}


void Check_Email_Name_Validity(char *email_copy)
{
    if (!isalpha(email_copy[0]))
    {
        printf("Name must begin with letter\n");
        exit(0);
    }

    if (strlen(email_copy) > 33)
    {
        printf("Name must have fewer than 32 characters\n");
        exit(0);
    }

    // may contain only letters, underscore, or digits [A-Z, a-z, _, 0-9]
    for (int i = 0; i < strlen(email_copy) - 1; i++)
    {
        if (!islower(email_copy[i]) && !isupper(email_copy[i]) && !isdigit(email_copy[i]))
        {
            printf("Invalid character in name\n");
            exit(0);
        }
    }
}
