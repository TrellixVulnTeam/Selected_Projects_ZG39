// YOUR PREFERED NAME 
// WISC NETID
// CANVAS USERNAME
// WISC ID NUMBER
// SUBMISSION DATE (MADISON TIME ZONE)
// COMMENTS FOR THE GRADER (OPTIONAL)

#include <ctype.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>


#define USER_NAME_LENGTH 256
#define EMAIL_LENGTH 512
#define PASSWORD_LENGTH 256

void Get_User_Data(char *message, char *username, const int MAX_LENGTH);
/* add your function prototypes here */
void Check_Name_Validity(char *username);

int main(){
    char username[USER_NAME_LENGTH];
    //char email[EMAIL_LENGTH];
    //char password_1[PASSWORD_LENGTH];
    //char password_2[PASSWORD_LENGTH];

    // ################################################
    // #####  VERIFY USERNAME  ##### 
    // ################################################
    Get_User_Data("Enter username: ", username, USER_NAME_LENGTH);
    
    Check_Name_Validity(username);
    
    printf("Username formatting is correct\n");
}

void Get_User_Data(char *message, char *data, const int MAX_LENGTH) {
    printf("%s", message);
    fgets(data, MAX_LENGTH, stdin);
    return;
}

/* add your function definitions here */

void Check_Name_Validity(char *username){
    if (!isalpha(username[0]))
    {
        printf("Invalid username starting character\n");
        exit(0);
    }

    // max 32 charcters
    /* Add your code here */
    if (strlen(username) > 33)
    {
        printf("Max 32 charcters\n");
        exit(0);
    }

    // may contain only letters, underscore, or digits [A-Z, a-z, _, 0-9]
    /* Add your code here */
    for (int i = 0; i < strlen(username) - 1; i++)
    {
        if (!islower(username[i]) && !isupper(username[i]) && !isdigit(username[i]) && username[i] != '_')
        {
            printf("Invalid character in username\n");
            exit(0);
        }
    }
}