.section .data
.section .text
.globl countOnes

countOnes:
// write a C version of your code to return the number of ones in the bit representation of the 
// integer parameter as a comment
//
// ### CODE IN C VERSION ###
// unsigned int countOnes(unsigned int n)
// {
//    unsigned int count = 0;
//
//    while (n) {
//        count += n & 1;
//        n >>= 1;
//    }
//    
//    return count;
// }
// ### CODE IN C VERSION ###
//
// translate your C code to assembly
// comment every line
// do not use assemblier directives (e.g. .cfi_something)
    // int countOnes (int n){
    pushl   %ebp                      // create return addr
    movl    %esp,     %ebp            // stack pointer
    subl	$16,      %esp            // reserve spaces
    movl    $0,       -4(%ebp)        // count stores at -4(%ebp)
    jmp     .L2                       // go to while loop 

.L3:
    movl    8(%ebp),  %eax            // n val pass to %eax; n store at 8(%ebp)
    andl    $1,       %eax            // n & 1
    addl    %eax,     -4(%ebp)        // count += (n & 1) from L15; update count
    shrl    8(%ebp)                   // n >> = 1, update n value

.L2:
    cmpl    $0,       8(%ebp)         // while(n); compare "n - 0"
    jne     .L3                       // if result != 0, go to while loop
    movl    -4(%ebp), %eax            // if result = 0; return count from -4(%ebp) 
  
    leave                             // clean up stack
    ret                               // return to caller
