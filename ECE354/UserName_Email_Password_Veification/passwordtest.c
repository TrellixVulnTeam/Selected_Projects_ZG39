#include <ctype.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define USER_NAME_LENGTH 256
#define EMAIL_LENGTH 512
#define PASSWORD_LENGTH 256

void Get_User_Data(char *message, char *username, const int MAX_LENGTH);
/* add your function prototypes here */
//void Check_Name_Validity(char *username);

int main(){
    //char username[USER_NAME_LENGTH];
    //char email[EMAIL_LENGTH];
    char password_1[PASSWORD_LENGTH];
    char password_2[PASSWORD_LENGTH];
    // ################################################
    // #####  VERIFY PASSWORD  ##### 
    // ################################################
    Get_User_Data("Enter password: ", password_1, PASSWORD_LENGTH);
    Get_User_Data("Confirm password: ", password_2, PASSWORD_LENGTH);
    // May use any character except spaces // example "my password" is invalid
    // passwords must contain at least 8 characters // example "Password" has 8 characters and is valid
    // passwords have at most 16 characters // example "1234567890Abcdef" has 16 characters and is valid 
    // Must contain at least one upper case character [A-Z]
    // Must contain at least one lower case character [a-z]
    // Original Password and the Reentered Password must match
    int upper_case_count = 0;
    int lower_case_count = 0;
    char *p1 = password_1;
    char *p2 = password_2;

    for (int i = 0; i < strlen(password_1) - 1; i++)
    {
        if (isspace(p1[i]))
        {
            printf("Password may not contain spaces\n");
            exit(0);
        }

        if (islower(p1[i]))
        {
            lower_case_count++;
        }

        if (isupper(p1[i]))
        {
            upper_case_count++;
        }
    }

    if (strlen(password_1) < 8)
    {
        printf("Password must have at least 8 characters\n");
        exit(0);
    }
    else if (strlen(password_1) > 16)
    {
        printf("Password may have at most 16 character\n");
        exit(0);
    }

    if (upper_case_count < 1)
    {
        printf("Password must contain at least one upper case character\n");
        exit(0);
    }

    if (lower_case_count < 1)
    {
        printf("Password must contain at least one lower case character\n");
        exit(0);
    }

    if (strcmp(p1, p2) != 0)
    {
        printf("Passwords do not match\n");
        exit(0);
    }

    printf("Passwords match\n");
    return 0;
}

void Get_User_Data(char *message, char *data, const int MAX_LENGTH) {
    printf("%s", message);
    fgets(data, MAX_LENGTH, stdin);
    return;
}

//char *email_end = email + length while (*email_end != ‘.’)
