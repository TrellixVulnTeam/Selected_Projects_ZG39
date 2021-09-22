// CODE IN C VERSION
// int main(){
//    
//    int *a = getArr();
//    int i, j, temp;
//    int n = ARR_SIZE;
//
//    for (i = 0; i < n - 1; i++)
//    {
//        // Last i elements are already in place
//        for (j = 0; j < n - i - 1; j++)
//        {
//            if (a[j] > a[j + 1])
//            {
//                temp = a[j];
//                a[j] = a[j + 1];
//                a[j + 1] = temp;
//            }
//        }
//    }
//
//    printArr(a);
//    
//    return 0;
// }
// CODE IN C VERSION 

	.globl	main
	.type	main, @function
main:
// function caller 
	pushl	%ebp
	movl	%esp, %ebp

// call getArr to get the Arr; no value now in %eax 
	call	getArr@PLT

// int temp: -12(%ebp)
// int n: each time get to %eax from -16(%ebp)
// ARR_SIZE: -16(%ebp)
// Array pointer: -20(%ebp)
// int j: -24(%ebp)
// int i: -28(%ebp)

// write your code here 
	movl	%eax, -20(%ebp)		// Array pointer
	movl	$20, -16(%ebp)		// ARR_SIZE store at -16(%ebp)
	movl	$0, -28(%ebp)		// set i = 0; i store at -28(%ebp)
	jmp	.L2						// go into first for loop: for (i = 0; i < n - 1; i++) 	

.L6:
	movl	$0, -24(%ebp)		// set j = 0; j store at -24(%ebp)
	jmp	.L3						// go to 2nd for loop: for (j = 0; j < n - i - 1; j++)

// if (a[j] > a[j + 1])
.L5:							
	movl	-24(%ebp), %eax		// move j value into %eax
	leal	0(,%eax,4), %edx	// %edx = &%eax, load effective addr of array
	movl	-20(%ebp), %eax		// array pointer into %eax 
	addl	%edx, %eax			// (array pointer + array effective addr)[j]
	movl	(%eax), %edx		// store a[j] at %ecx
	
	movl	-24(%ebp), %eax		// %eax usable again; store j into  
	addl	$1, %eax			// increment j;
	leal	0(,%eax,4), %ecx	// %edx = &%eax, load effective addr and store at %ecx
	movl	-20(%ebp), %eax		// array pointer into %eax 
	addl	%ecx, %eax			// array pointer + array effective addr
	movl	(%eax), %eax		// store mem into reg
	cmpl	%eax, %edx			// compare a[j] and a[j + 1] (use bubble sort here)
	jle	.L4						// if a[j] <= a[j + 1] go to L4

	// temp = a[j];
	movl	-24(%ebp), %eax		// else: store j into %eax
	leal	0(,%eax,4), %edx	// load effective addr of arr and store at %edx
	movl	-20(%ebp), %eax		// array pointer into %eax
	addl	%edx, %eax			// array pointer + array effective addr
	movl	(%eax), %eax		// store mem into reg
	movl	%eax, -12(%ebp)		// store a[j] to temp; temp at -12(%ebp)

	// a[j] = a[j + 1];
	movl	-24(%ebp), %eax		// store j into %eax
	addl	$1, %eax			// increment j (we use j + 1 here)
	leal	0(,%eax,4), %edx	// load effective addr of arr and store at %edx
	movl	-20(%ebp), %eax		// array pointer into %eax
	addl	%edx, %eax			// array pointer + array effective addr
	movl	-24(%ebp), %edx		// store a[j + 1] at %edx
	leal	0(,%edx,4), %ecx	// load effective addr of arr and store at %edx
	movl	-20(%ebp), %edx		// array pointer into %eax
	addl	%ecx, %edx			// array pointer + array effective addr
	movl	(%eax), %eax		// store mem into reg
	movl	%eax, (%edx)		// store a[j + 1] into a[j]

	// a[j + 1] = temp;
	movl	-24(%ebp), %eax		// store j into %eax	
	addl	$1, %eax			// increment j as use j + 1 here
	leal	0(,%eax,4), %edx	// load effective addr of arr and store at %edx
	movl	-20(%ebp), %eax		// array pointer into %eax
	addl	%eax, %edx			// array pointer + array effective addr
	movl	-12(%ebp), %eax		// mov value store at temp (-12(%ebp)) to %eax
	movl	%eax, (%edx)		// store back to a[j + 1] (at (%edx))

.L4:							
	addl	$1, -24(%ebp)		// increment j (go to next val in array)

.L3:
	movl	-16(%ebp), %eax		// store ARR_SIZE into %eax (n)
	subl	-28(%ebp), %eax		// decrement: n <- n - i
	subl	$1, %eax			// decrement: n <- n - 1
	cmpl	%eax, -24(%ebp)		// compare n and j 
	jl	.L5						// if n > j; go to L5
	addl	$1, -28(%ebp)		// if not, increment j for 1
	
.L2:
	movl	-16(%ebp), %eax		// store ARR_SIZE into %eax (n) and 
	subl	$1, %eax			// decrement n
	cmpl	%eax, -28(%ebp)		// compare the n and i
	jl	.L6						// if n > i, go to L6

	movl	-20(%ebp), %eax		// else, exit loop
	movl	%eax, %edi

// printing out the sorted array
    pushl   %eax
	call	printArr@PLT
    popl    %eax

// "leave" and return to function caller
    movl	$0, %eax
	popl	%ebp
	ret
