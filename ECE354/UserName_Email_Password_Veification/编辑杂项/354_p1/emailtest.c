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
            if(email[i] == '@'){
                at_count++;
                break;
            }

            if(email[i] == '.'){
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
                printf("email name = %s\n", email_name_copy);
                exit(0);
            }
        }
    }else{
        // STEP #5: when no @ in email str: missing '@'
        printf("missing @\n");
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
    if ((domain_copy[strlen(domain_copy) - 2] != 'u' || domain_copy[strlen(domain_copy) - 2] != 'm' || domain_copy[strlen(domain_copy) - 2] != 't') &&
        (domain_copy[strlen(domain_copy) - 3] != 'd' || domain_copy[strlen(domain_copy) - 3] != 'o' || domain_copy[strlen(domain_copy) - 3] != 'e') &&
        (domain_copy[strlen(domain_copy) - 4] != 'e' || domain_copy[strlen(domain_copy) - 4] != 'c' || domain_copy[strlen(domain_copy) - 4] != 'n') &&
        (domain_copy[strlen(domain_copy) - 5] != '.'))
    {
        printf("Top level domain must be .edu, .com, or .net\n");
        exit(0);
    }

    printf("Passed\n");
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
