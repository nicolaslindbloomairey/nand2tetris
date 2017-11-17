import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;

public class CodeWriter {
    PrintWriter out;
    private static int line = 0;

    public CodeWriter(String inputfile) throws IOException {
        out = new PrintWriter(new BufferedWriter(new FileWriter(new File(inputfile.substring(0, inputfile.indexOf('.')) + ".asm"))));
    }

    //public void setFileName(String fileName) {

    public void writeArithmetic(String command) {

        if (command.equalsIgnoreCase("add")) {
            //add in assembly
            out.println("@SP");
            out.println("A=M");
            out.println("A=A-1");
            out.println("D=M");
            out.println("A=A-1");
            out.println("M=D+M");
            out.println("@SP");
            out.println("M=M-1");
        } else if (command.equalsIgnoreCase("sub")) {
            out.println("@SP");
            out.println("A=M");
            out.println("A=A-1");
            out.println("D=M");
            out.println("A=A-1");
            out.println("M=M-D");
            out.println("@SP");
            out.println("M=M-1");
        } else if (command.equalsIgnoreCase("neg")) {
            out.println("@SP");
            out.println("A=M");
            out.println("A=A-1");
            out.println("M=-M");
        } else if (command.equalsIgnoreCase("eq")) {
            out.println("@SP");
            out.println("A=M");
            out.println("A=A-1");
            out.println("D=M");
            out.println("A=A-1");
            out.println("D=M-D"); //subtract numbers

            out.println("@EQTRUE"+line);
            out.println("D;JEQ"); //jump if result of x-y=0
            out.println("@EQFALSE"+line);
            out.println("0;JMP"); //else return false
   
            out.println("(EQTRUE"+line+")");

            out.println("@SP");
            out.println("A=M");
            out.println("A=A-1");
            out.println("A=A-1"); //reset A reg to stackpointer-2

            out.println("M=-1");
            out.println("@EQEND"+line);
            out.println("0;JMP"); //jump past setting false
            out.println("(EQFALSE"+line+")");
            out.println("@SP");
            out.println("A=M");
            out.println("A=A-1");
            out.println("A=A-1"); //reset A reg to stackpointer-2
            out.println("M=0");
            out.println("(EQEND"+line+")");

            out.println("@SP");
            out.println("M=M-1");

        } else if (command.equalsIgnoreCase("gt")) {
            out.println("@SP");
            out.println("A=M");
            out.println("A=A-1");
            out.println("D=M");
            out.println("A=A-1");
            out.println("D=M-D"); //subtract numbers

            out.println("@GTTRUE"+line);
            out.println("D;JGT"); //jump if result of x-y>0
            out.println("@GTFALSE"+line);
            out.println("0;JMP"); //else return false
   
            out.println("(GTTRUE"+line+")");

            out.println("@SP");
            out.println("A=M");
            out.println("A=A-1");
            out.println("A=A-1"); //reset A reg to stackpointer-2

            out.println("M=-1");
            out.println("@GTEND"+line);
            out.println("0;JMP"); //jump past setting false
            out.println("(GTFALSE"+line+")");

            out.println("@SP");
            out.println("A=M");
            out.println("A=A-1");
            out.println("A=A-1"); //reset A reg to stackpointer-2

            out.println("M=0");
            out.println("(GTEND"+line+")");

            out.println("@SP");
            out.println("M=M-1");
        } else if (command.equalsIgnoreCase("lt")) {
            out.println("@SP");
            out.println("A=M");
            out.println("A=A-1");
            out.println("D=M");
            out.println("A=A-1");
            out.println("D=M-D"); //subtract numbers

            out.println("@LTTRUE"+line);
            out.println("D;JLT"); //jump if result of x-y<0
            out.println("@LTFALSE"+line);
            out.println("0;JMP"); //else return false
   
            out.println("(LTTRUE"+line+")");

            out.println("@SP");
            out.println("A=M");
            out.println("A=A-1");
            out.println("A=A-1"); //reset A reg to stackpointer-2

            out.println("M=-1");
            out.println("@LTEND"+line);
            out.println("0;JMP"); //jump past setting false
            out.println("(LTFALSE"+line+")");

            out.println("@SP");
            out.println("A=M");
            out.println("A=A-1");
            out.println("A=A-1"); //reset A reg to stackpointer-2

            out.println("M=0");
            out.println("(LTEND"+line+")");

            out.println("@SP");
            out.println("M=M-1");
        } else if (command.equalsIgnoreCase("and")) {
            out.println("@SP");
            out.println("A=M");
            out.println("A=A-1");
            out.println("D=M");
            out.println("A=A-1");
            out.println("M=M&D"); //and
            out.println("@SP");
            out.println("M=M-1");
        } else if (command.equalsIgnoreCase("or")) {
            out.println("@SP");
            out.println("A=M");
            out.println("A=A-1");
            out.println("D=M");
            out.println("A=A-1");
            out.println("M=M|D"); //or
            out.println("@SP");
            out.println("M=M-1");
        } else if (command.equalsIgnoreCase("not")) {
            out.println("@SP");
            out.println("A=M");
            out.println("A=A-1");
            out.println("M=!M"); //not
        }
        line++;
    }

    public void writePushPop(String command, String segment, int index) {
        if (command.equals("C_PUSH")) {
            if (segment.equalsIgnoreCase("constant")) {
                //push constant in assembly
                out.println("@"+index);
                out.println("D=A");
                out.println("@SP");
                out.println("A=M");
                out.println("M=D");
                out.println("@SP");
                out.println("M=M+1");
            } else if (segment.equalsIgnoreCase("local")) {
                //push
                out.println("@"+index);
                out.println("D=A");
                out.println("@LCL");
                out.println("A=M+D");
                out.println("D=M"); //contents of register (local + index) stored in D reg

                out.println("@SP");
                out.println("A=M");
                out.println("M=D"); //add contents of D reg to top of stack

                out.println("@SP");
                out.println("M=M+1"); //increment stack pointer
            } else if (segment.equalsIgnoreCase("argument")) {
                //push
                out.println("@"+index);
                out.println("D=A");
                out.println("@ARG");
                out.println("A=M+D");
                out.println("D=M"); //contents of register (argument + index) stored in D reg

                out.println("@SP");
                out.println("A=M");
                out.println("M=D"); //add contents of D reg to top of stack

                out.println("@SP");
                out.println("M=M+1"); //increment stack pointer
            } else if (segment.equalsIgnoreCase("this")) {
                //push
                out.println("@"+index);
                out.println("D=A");
                out.println("@THIS");
                out.println("A=M+D");
                out.println("D=M"); //contents of register (local + index) stored in D reg

                out.println("@SP");
                out.println("A=M");
                out.println("M=D"); //add contents of D reg to top of stack

                out.println("@SP");
                out.println("M=M+1"); //increment stack pointer
            } else if (segment.equalsIgnoreCase("that")) {
                //push
                out.println("@"+index);
                out.println("D=A");
                out.println("@THAT");
                out.println("A=M+D");
                out.println("D=M"); //contents of register (local + index) stored in D reg

                out.println("@SP");
                out.println("A=M");
                out.println("M=D"); //add contents of D reg to top of stack

                out.println("@SP");
                out.println("M=M+1"); //increment stack pointer
            }
        } else if (command.equals("C_POP")) {
            if (segment.equalsIgnoreCase("local")) {

            }
        }
        line++;
    }

    public void close() {
        out.close();
    }
}
