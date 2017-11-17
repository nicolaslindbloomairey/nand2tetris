// This file is part of www.nand2tetris.org
// and the book "The Elements of Computing Systems"
// by Nisan and Schocken, MIT Press.
// File name: projects/04/Mult.asm

// Multiplies R0 and R1 and stores the result in R2.
// (R0, R1, R2 refer to RAM[0], RAM[1], and RAM[2], respectively.)

// Put your code here.
//r2 is the running sum

//multiply 5 by 3
//set register 14 to 5 and register 15 to 3 
//output to register 0


@0 //init product to zero
M=0 

@3 //init r15 to 3
D=A
@15
M=D

@5 //init r14 to 5
D=A
@14
M=D

//we will use r15 as loop counter

(LOOP)


@15 //if r15 = 0, end now (out zero)
D=M
@END
D;JEQ


@14 //if r14 = 0, end now (out zero)
D=M
@END
D;JEQ

@14 //load r14 into data register
D=M

@0 //add r14 to r0
M=M+D

@15 //decrement r15
M=M-1

@LOOP //loop
0;JMP

(END)

