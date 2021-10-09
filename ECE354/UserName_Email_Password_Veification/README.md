# ECE 354 Selected Project #1: Username, Email, & Password Verification

This project verifies the correct formatting of a username, email address, and password.

This project simulates making part of an online account for a website. The program asks a user to enter their username, email address, and password.  Then it verifies the correct formatting of each of these pieces of data, and it either reports success or tells the user what error they made and exits.

Please see the template for the formatting rules, the order they are applied, and the output statements generated for each condition.

For example, the username must begin with a letter [A-Z, a-z], have a maximum of 32 characters, and may only contain letters, digits, or the underscore [A-Z, a-z, 0-9, _].  These conditions are tested in this order, so if the username is “CS354isThe_Best_Most_AwesomeClass!@#$%^&*(Ever)!!!”. The first test will pass (this doesn’t generate any output), but the second test will fail that username has 50 characters. When the second test fails, the output “Max 32 characters” is printed, and the program ends (you will need to add a return 0 to your conditional statement;).

When each of the three pieces of data has been verified, the program should print a success message.

All messages printed on the screen have been written for you.  We test your code using exact match output testing.  If you change any of the messages, the tests will fail to match. (Hopefully, I haven’t made any typos, but if I did, please do not correct them, so our grading script works.)

Detailed Speficiation can be found in the PDF file.