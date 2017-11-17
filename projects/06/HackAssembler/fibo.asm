//fibonacci generator

//n-2 = register 2 (r2)
//n-1 = register 1 (r1)
//n = register 0 (r0) (this register is the screen)

//init r1 and r2 = 1

@1  //set r1 = 1
M=A 

@1  //set r2 = 1 
D=A
@2
M=D 

//main
(LOOP)

@1
D=M
@2
D=D+M //add r1 and r2

@0
M=D //save result to r0

@1
D=M
@2
M=D //set r2 to r1

@0
D=M
@1
M=D //set r1 to r0

@LOOP
0;JMP
