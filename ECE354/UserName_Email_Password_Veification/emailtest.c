#include <ctype.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

#define USER_NAME_LENGTH 256
#define EMAIL_LENGTH 512
#define PASSWORD_LENGTH 256

void Get_User_Data(char *message, char *username, const int MAX_LENGTH);
//void Check_Email_Name_Validity(char *email);
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
     /*printf("email addr is %s", email);
     for(int i = 0; i < 30; i++){
     	printf("char in email here: %c %d\n", email[i], email[i]);
     }*/
     	 

    char email_name_copy[EMAIL_LENGTH];
    char domain_copy[EMAIL_LENGTH];
    //char top_site_copy[EMAIL_LENGTH];
    int at_count = 0;
    int at_position = 0;
    //int dot_position = 0;
    int dot_count = 0;

    // STEP #1: check if @ sign is first char
    // only case "name missing"
    if(email[0] == '@'){
        printf("Name missing\n");
        exit(0);
    }

    // STEP #2: check if first letter is alphabetic (MUST BE);
    // only exception in STEP #1: '@'
    if (!isalpha(email[0]))
    {
        printf("Name must begin with letter\n");
        exit(0);
    }

    // STEP #3 check name character number: must less than 32
    for (int i = 0; i < strlen(email) - 1; i++){
        if(email[i] == '@'){
            if(i > 33){
                printf("Name must have fewer than 32 characters\n");
                exit(0);
            }
          
            else{
                at_position = i;
            }
        }
    }

    // STEP #4: get out the name section, check validity of email name
    // BUG REPORT: when char is 32 the last char in email_name_copy is '?'
    for (int i = 0; i < strlen(email)-1; i++){
        if (email[i] == '@' || email[i] == '.')
        {
            strncpy(email_name_copy, email, i);
            // add a backslash here for email_name_copy 
            if(email[i] == '@'){
                at_count++;
                email_name_copy[i++] = '\0';
                break;
            }

            if(email[i] == '.'){
                email_name_copy[i++] = '\0';
                break;
            }
        }else{
            if(!isalpha(email[i]) && !isdigit(email[i])){
                printf("Invalid character in name\n");
                exit(0);
            }
        }
    }
	
	
    // when @ exists
    if(at_count != 0){
        // STEP #4: get out the name section, check validity of email name
        // BUG REPORT: when char is 32 the last char in email_name_copy is '?'
        for (int i = 0; i < strlen(email_name_copy) - 1; i++)
        {
            if (!isalpha(email_name_copy[i]) && !isdigit(email_name_copy[i]))
            {
                printf("Invalid character in name\n");
                for(int i = 0; i < 30; i++){
     	printf("char in email_name_copy here: %c %d\n", email_name_copy[i], email_name_copy[i]);
     }
                printf("email name = %s\n", email_name_copy);
         
                exit(0);
            }
        }
    }else{
        // STEP #5: when no @ in email str: missing '@'
        printf("missing @\n");
        printf("at_count = %d\n", at_count);
        printf("email name copy = %s\n", email_name_copy);
        exit(0);
    }

    // Because we checked if @ sign exits or not, we can directly use
    // at_position previously stored
    strcpy(domain_copy, email + at_position + 1);

    // STEP #6: check if missing domain
    // Only one case: '.' after '@'; isalpha 'a' after '.'; 
    // '.' is only point in section

    for (int i = 0; i < strlen(domain_copy) - 1; i++){
        if(domain_copy[i] == '.'){
            dot_count++;
        }
    }

    int next_at_position = at_position + 1;
    int next_two_at_position = next_at_position + 1;
    if (email[next_at_position] == '.' && isalpha(email[next_two_at_position]) && dot_count == 1)
    {
        printf("Domain missing\n");
        exit(0);
    }

    // STEP #7: Check if domain exceeding 64 characters
    // Done this by cutting domain out, aka cut email char after '@'
    if(strlen(domain_copy) > 69){
        printf("Maximum of 64 characters in domain\n");
        exit(0);
    }

    // STEP #8: Check domain/subdomain character validities.
    // First, the first letter in domain copy must be alpha
    if(!isalpha(domain_copy[0])){
        printf("Domain or subdomain must begin with letter\n");
        exit(0);
    }

    // Second, traverse domain copy. Any place after '.' should be alphabetic
    // other place, number accepted




    //NOTE!!!!! Add "&& strlen(domain_copy) - i >= 5" in first if cause #10 test unpassed
    for (int i = 0; i < strlen(domain_copy) - 2; i++){
        if(domain_copy[i] == '.' && !isalpha(domain_copy[i+=1]) && strlen(domain_copy) - i >= 5 ){
            printf("Domain or subdomain must begin with letter\n");
            printf("domain i: %c\n", domain_copy[i]);
            printf("domain last char is %c", domain_copy[7]);
            exit(0);
        }else{
            // STEP #9: check if invalid character contained in domain
            if(!isalpha(domain_copy[i]) && !isdigit(domain_copy[i]) && domain_copy[i] != '.'){
                printf("Invalid character in domain\n");
                printf("strlen domain %d\n", strlen(domain_copy));
                printf("strlen domain %c\n", domain_copy[i]);
                exit(0);
            }
        }   
    }

    //SPECIAL CASE: when double points at end:
    if (domain_copy[strlen(domain_copy) - 3] == '.' && domain_copy[strlen(domain_copy) - 2] == '.'){
        printf("Domain or subdomain must begin with letter\n");
        exit(0);
    }
    
    // STEP #10: Final check for the last 4 character:
    // if they fit in .edu; .com; and .net
    char top_domain_copy[5];

    // take out the last four char
    strcpy(top_domain_copy, domain_copy + strlen(domain_copy)-5);
    //strncpy(real_top_domain_copy, top_domain_copy, 4);
    printf("top domain copy is %s\n", top_domain_copy);
    if ((top_domain_copy[3] == 'u' && top_domain_copy[2] == 'd' && top_domain_copy[1] == 'e' && top_domain_copy[0] == '.') || 
        (top_domain_copy[3] == 'm' && top_domain_copy[2] == 'o' && top_domain_copy[1] == 'c' && top_domain_copy[0] == '.') || 
        (top_domain_copy[3] == 't' && top_domain_copy[2] == 'e' && top_domain_copy[1] == 'n' && top_domain_copy[0] == '.'))
    {
        printf("Email Formatting is correct\n");
        printf("first is %c\n", top_domain_copy[0]);
        printf("second is %c\n", top_domain_copy[1]);
        printf("third is %c\n", top_domain_copy[2]);
    }else{
        printf("Top level domain must be .edu, .com, or .net\n");
        printf("first is %c\n", top_domain_copy[0]);
        printf("second is %c\n", top_domain_copy[1]);
        printf("third is %c\n", top_domain_copy[2]);
        printf("fourth is %c\n", top_domain_copy[3]);
        printf("%d\n",top_domain_copy[3] == 'u' && top_domain_copy[2] == 'd' && top_domain_copy[1] == 'e');
        printf("%d\n",top_domain_copy[3] == 'm' && top_domain_copy[2] == 'o' && top_domain_copy[1] == 'c' && top_domain_copy[0] != '.');
        printf("%d\n",top_domain_copy[3] == 't' && top_domain_copy[2] == 'e' && top_domain_copy[1] == 'n' && top_domain_copy[0] != '.');
        exit(0);
    }
    /*char a[4]= ".edu\0";
    char b[4]= ".com\0";
    char c[4]= ".net\0";
    char d = 'a';
    char e ='b';
    char f = 'd';

    /*domain_1[5] = '\0';
    domain_2[5] = '\0';
    domain_3[5] = '\0';
    top_domain_copy[5] = '\0';
    printf("domain_1 is %s\n", a);
    printf("domain_2 is %s\n", b);
    printf("domain_3 is %s\n", c);
    printf("domain copy is %s\n", top_domain_copy);
    for(int i = 0; i < 4; i++){
            printf("ascii top domain: %c, %d\n", top_domain_copy[i], top_domain_copy[i]);
        }
        for(int i = 0; i < 4; i++){
            printf("ascii domain_1: %c, %d\n", a[i], a[i]);
        }
        for(int i = 0; i < 4; i++){
            printf("ascii domain_2: %c, %d\n", b[i], b[i]);
        }
        for(int i = 0; i < 4; i++){
            printf("ascii domain_3: %c, %d\n", c[i], c[i]);
        }

    if(strcmp(top_domain_copy,a) == 0 || strcmp(top_domain_copy, b) == 0 || strcmp(top_domain_copy, c) == 0){
        printf("Email Formatting is correct\n");
        printf("is domain_1: %d\n",strcmp(top_domain_copy,a));
        printf("is domain_2: %d\n",strcmp(top_domain_copy,b));
        printf("is domain_3: %d\n",strcmp(top_domain_copy,c));
        printf("domain_1 is %s\n", a);
        printf("domain_2 is %s\n", b);
        printf("domain_3 is %s\n", c);
        printf("domain copy is %s\n", top_domain_copy);
        exit(0);
    }else{
        printf("Top level domain must be .edu, .com, or .net\n");
        printf("is domain_1: %d\n",strcmp(top_domain_copy,a));
        printf("is domain_2: %d\n",strcmp(top_domain_copy,b));
        printf("is domain_3: %d\n",strcmp(top_domain_copy,c));
        for(int i = 0; i < 4; i++){
            printf("ascii: %c, %d\n", top_domain_copy[i], top_domain_copy[i]);
        }
        for(int i = 0; i < 4; i++){
            printf("ascii domain_1: %c, %d\n", a[i], a[i]);
        }
        exit(0);
    }*/

    /*
    if ((domain_copy[strlen(domain_copy) - 2] != 'u' || domain_copy[strlen(domain_copy) - 2] != 'm' || domain_copy[strlen(domain_copy) - 2] != 't') &&
        (domain_copy[strlen(domain_copy) - 3] != 'd' || domain_copy[strlen(domain_copy) - 3] != 'o' || domain_copy[strlen(domain_copy) - 3] != 'e') &&
        (domain_copy[strlen(domain_copy) - 4] != 'e' || domain_copy[strlen(domain_copy) - 4] != 'c' || domain_copy[strlen(domain_copy) - 4] != 'n') &&
        (domain_copy[strlen(domain_copy) - 5] != '.'))
    {
        printf("Top level domain must be .edu, .com, or .net\n");
        exit(0);
    }*/

    
    //printf("at position %d\n", at_position);
    printf("strlen domain %d\n", strlen(domain_copy));
    printf("domain copy is %s\n", domain_copy);
    printf("domain last char is %c", domain_copy[strlen(domain_copy) -5]);
    }

void Get_User_Data(char *message, char *data, const int MAX_LENGTH)
{
    printf("%s", message);
    fgets(data, MAX_LENGTH, stdin);
    return;
}
