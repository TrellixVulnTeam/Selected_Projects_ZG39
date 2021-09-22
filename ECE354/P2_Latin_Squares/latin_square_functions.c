// Shi Kaiwen
// kshi42
// SHI KAIWEN
// 9082594194
// 2021.7.20 
// Received extension permission from prof. M.Doescher

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/* This function takes the name of the file containing the latin square puzzle
 * and reads in the data to the the latin_square variable in main.  
 *
 * n :: the dimensions of the board.  Note all boards tested will be square (i.e. nxn)
 * filename :: the name of the file to read in
 * latin_square_in :: a pointer to the latin square variable in main
 */
void Read_Latin_Square_File(char *filename, char ***latin_square_in, int *n) {
    // CLARIFICATION: "WEIRD THING" USED:
    // SEEK_SET: pointer pointing to head of file
    // EOF: end of file
    
    // STEP #1: OPEN FILE & INITIALIZE VARIABLE
    FILE *fileReader = fopen(filename, "r");
    *n = 0;
    char charReading;
    int dimensionCount = 0;
    int colCount = 0;
    int rowCount = 0;

    // if file not exist, directly return
    if(fileReader == NULL) { return; }

    // STEP #2, 3: FIGURE OUT PUZZLE DIMENSION
    while((charReading = fgetc(fileReader)) != EOF){
        if(charReading == '\n' && !*n) { *n = dimensionCount; }
        dimensionCount++;
    }

    // STEP #4: RESERVE MEMORY FOR latin_square WITH malloc
    // 4a) reserve memory location for pointers
    char **latinSquareArr = malloc((*n) * sizeof(char *));

    // 4b) reserve an array of characters for each row
    for (int i = 0; i < *n; i++){ *(latinSquareArr + i) = malloc((*n) * sizeof(char));}

    // STEP #5: Fill in latin_data structure
    // read the file start from beginning
    fseek(fileReader, 0, SEEK_SET);
    *latin_square_in = latinSquareArr;

    //traverse
    while((charReading = fgetc(fileReader)) != EOF){
        if(charReading == '\n'){
            rowCount++;
            colCount = 0;
            continue;
        } 
        *(*(latinSquareArr + rowCount) + colCount) = charReading;
        colCount++;
    }

    // STEP #6: close the file
    fclose(fileReader);
    return;
}
 

/* This function checks to see that exactly n symbols are used and that each symbol is used exactly n times.
 * Valid symbols include charcters with ascii codes 33 to 126 (inclusive)
 * Google "man ascii" to pull up the table
 *
 * n :: the dimensions of the puzzle - all puzzles will be square (i.e. nxn)
 * latin_square :: the puzzle data structure
 * return :: 0 if the alphabet is invalid and 1 if the alphabet is valid
 */
int Verify_Alphabet(int n, char **latin_square) {
    // if file not exist, directly return
    if (latin_square == NULL) {
        printf("Verify_Alphabet - latin_square is NULL\n");
        return 0;
    }

    // CONDITION: 1. exactly n symbols used
    //            2. each symbol used exactly n times
    //            3. symbol ascii value between 33~126

    // STEP #1: COUNT FREQUENCY OF ALL SYMBOLS
    // Distribute Memory location for store frequency & initialize var
    char charReading;
    char *frequencyCount = (char*)calloc(n, sizeof(char));
    int symbolUsed = 0;

    // Traverse latin square
    for(int i = 0; i < n; i++){
        for(int j = 0; j < n; j++){
            // CHECKED CONDITION 3: SYMBOL ASCII VALUE BETWEEN 33~126
            // IF DISCOVERED INVALID SYMBOL, DIRECTLY RETURN
            if (*(*(latin_square + i) + j) < 33 || (*(*(latin_square + i) + j)) > 126) { return 0; }

            charReading = *(*(latin_square + i) + j);
            *(frequencyCount + charReading) += 1;
        }
    }

    // STEP #2: CHECK EACH SYMBOL USED n TIMES
    // Traverse frequency counter array: Note: only traverse "valid" section
    for(int i = 33; i <= 126; i++){
        // CHECKED CONDITION #2: EACH SYMBOL USE EXACTLY 0/n times
        // if not, directly return
        if(*(frequencyCount + i) != 0 && *(frequencyCount + i) != n) { return 0; }

        // if satisfied, increment symbol used counter
        if(*(frequencyCount + i) == n) { symbolUsed++; }
    }

    // STEP #3: CHECK USED n SYMBOLS
    // CHECKED CONDITION #1: EXACTLY n SYMBOLS USED.
    return symbolUsed == n;
}

/* This function verifies that no symbol is used twice in a row or column.
 * It prints an error message alerting the user which rows or columns have duplicate symbols
 * Test all rows first then test all columns.
 * Error messages have been included for you.
 * 
 * n :: the dimensions of the puzzle - all puzzles will be square (i.e. nxn)
 * latin_square :: the puzzle data structure
 * return :: 0 if the any row or column contains a duplicate symbol otherwise return 1
 */
int Verify_Rows_and_Columns(int n, char **latin_square){
    // if file not exist, directly return
    if (latin_square == NULL)
    {
        printf("Verify_Rows_and_Columns - latin_square is NULL\n");
        return 0;
    }

    // Anytime when meet repititive symbol the related sign will 
    // turn to 0; only when 2 sign both = 1(all pass), 
    // return true; otherwise will return false
    int rowPass = 1;
    int colPass = 1;

    // STEP 1: CHECK NO SYMBOL USED TWICE IN ROW
    // Every row
    for (int row = 0; row < n; row++)
    {
        // Within each row
        for (int i = 0; i < n; i++)
        {
            // compare with all previous char in this row
            for (int j = 0; j < i; j++)
            {
                if (*(*(latin_square + row) + i) == *(*(latin_square + row) + j))
                {
                    // discovered repititive case, set sign to false;
                    printf("Error in row %d\n", row);
                    rowPass = 0;
                }
            }
        }
    }

    // STEP 2: CHECK NO SYMBOL USED TWICE IN COLUMN
    // Every col
    for (int col = 0; col < n; col++){
        // Within each col
        for (int i = 0; i < n; i++){
            // compare with all previous char in this col
            for (int j = 0; j < i; j++){
                if (*(*(latin_square + i) + col) == *(*(latin_square + j) + col))
                {
                    // discovered repititive case, set sign to false;
                    printf("Error in column %d\n", col);
                    colPass = 0;
                }
            }
        }
    }

    // STEP 3: WHEN PASSED PREVIOUS 2 TESTS: COL & ROW ALL VALID
    // RETURN TRUE: OTHERWISE ALL FALSE.
    return (colPass == 1) && (rowPass == 1);
}

/* This function calls free to return all memory used by the latin_square puzzle
 * note you will have n+1 calls to free where n is the size of the puzzle.
 *
 * n :: the dimensions of the puzzle - all puzzles will be square (i.e. nxn)
 * latin_square :: the puzzle data structure
 */ 
void Free_Memory(int n, char **latin_square) {
    for(int i = 0; i < n; i++) { free(*(latin_square + i)); }

    free(latin_square);
    return;
}