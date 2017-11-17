import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

public class Assembler {
    public static void main(String[] args) throws IOException {
        Parser p = new Parser(args[0]);
        SymbolTable s = new SymbolTable();
        BufferedWriter writer = new BufferedWriter(new FileWriter(new File(args[0].substring(0, args[0].indexOf('.')) + ".hack")));

        //initialization of predefined symbols
        s.addEntry("SP", 0);
        s.addEntry("LCL", 1);
        s.addEntry("ARG", 2);
        s.addEntry("THIS", 3);
        s.addEntry("THAT", 4);
        s.addEntry("R0", 0);
        s.addEntry("R1", 1);
        s.addEntry("R2", 2);
        s.addEntry("R3", 3);
        s.addEntry("R4", 4);
        s.addEntry("R5", 5);
        s.addEntry("R6", 6);
        s.addEntry("R7", 7);
        s.addEntry("R8", 8);
        s.addEntry("R9", 9);
        s.addEntry("R10", 10);
        s.addEntry("R11", 11);
        s.addEntry("R12", 12);
        s.addEntry("R13", 13);
        s.addEntry("R14", 14);
        s.addEntry("R15", 15);
        s.addEntry("SCREEN", 16384);
        s.addEntry("KBD", 24576);

        //first pass generates symbol table with just the labels, not the variables
        int line = 0; // keep track of line number
        while(p.hasMoreCommands()) {
            if (p.commandType().equals("C_COMMAND") || p.commandType().equals("A_COMMAND")) {
                line++; //if the line was an A or C command increment line number count
            } else if (p.commandType().equals("L_COMMAND")) {
                s.addEntry(p.symbol(), line); //if the line was a label add it to the symbol table with the line number it refers to
            }
            p.advance(); //move to next line
        }

        //second pass generates code
        p.reset(); //move parser back to top of file
        int variable = 16; //variables refer to addresses starting with 16
        while(p.hasMoreCommands()) {
            String machine = null;
            if (p.commandType().equals("C_COMMAND")) {
                machine = "111" + Code.comp(p.comp()) + Code.dest(p.dest()) + Code.jump(p.jump()); //create the machine language code from the parsed sections
            } else if (p.commandType().equals("A_COMMAND")) {
                try {
                    machine = Integer.toBinaryString(Integer.parseInt(p.symbol())); //if the symbol is a number, parse it and output in binary
                } catch (Exception e) {
                    if (!s.contains(p.symbol())) { //if the symbol is not a number and not in the symbol table yet, add it
                        s.addEntry(p.symbol(), variable);
                        variable++;
                    }
                    machine = Integer.toBinaryString(s.GetAddress(p.symbol())); //parse the non-number symbol into its address
                }
                while (machine.length()<16) {
                    machine = "0" + machine; //make sure that the length is 16 bits 
                }
            }
            if (machine != null) {
                writer.write(machine); //write to file if any machine code was generated for that line
                writer.newLine();
            }
            p.advance(); //move to next line
        }

        writer.close();
    }
}
