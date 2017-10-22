// This file is part of www.nand2tetris.org
// and the book "The Elements of Computing Systems"
// by Nisan and Schocken, MIT Press.
// File name: projects/04/Mult.asm

// Multiplies R0 and R1 and stores the result in R2.
// (R0, R1, R2 refer to RAM[0], RAM[1], and RAM[2], respectively.)

// Put your code here.
//r2 is the running sum


@R2 //init product to zero
M=0 

@R1 //set loop counter variable to value of r1
D=M
@R3
M=D


(LOOP)

@R3 //if r1 = 0, end now (out zero)
D=M
@END
D;JEQ

@R0 //if r0 = 0, end now (out zero)
D=M
@END
D;JEQ

@R0 //load r0 into data register
D=M

@R2 //add r0 to r2
M=M+D

@R3  //decrement R1
M=M-1

@LOOP //loop
0;JMP

(END)

