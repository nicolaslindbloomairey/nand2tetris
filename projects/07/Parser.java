import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class Parser {

    ArrayList<String> file = new ArrayList<String>(); //file with each line as a string in the list
    int c; //current index
    String command, arg1;
    int arg2;

    public Parser(String filelocation) {

        //read in file line by line into arraylist leaving out all comments and unneccessary whitespace
        try {
            Scanner scan = new Scanner(new File(filelocation));
            while(scan.hasNextLine()) {
                String line = scan.nextLine();
                line = line.trim();

                if (!line.startsWith("//")) {
                    if (line.contains("//")) {
                        String woComment = line.substring(0, nextline.indexOf("/")).trim();
                        file.add(woComment);
                    } else {
                        file.add(line);
                    }
                }
            }
        } catch (Exception e) {
        }

        reset();
    }

    public void reset() {
        c=-1;
        advance();
    }

    public boolean hasMoreCommands() {
        return c<(file.size());
    }

    public void advance() {
        c++;
        if (hasMoreCommands()) {
            String[] line = file.get(c).split("\\s+"); //split by any number of consecutive spaces
            command = line[0];
            arg1 = line[1];
            arg2 = Integer.parseInt(line[2]);
        }
    }

    public String commandType() {
        if (command.equalsIgnoreCase("add") || command.equalsIgnoreCase("sub") || command.equalsIgnoreCase("neg") || command.equalsIgnoreCase("eq") || command.equalsIgnoreCase("gt") || command.equalsIgnoreCase("lt") || command.equalsIgnoreCase("and") || command.equalsIgnoreCase("or") || command.equalsIgnoreCase("not")) {
            return "C_ARITHMETIC";
        } else if (command.equalsIgnoreCase("push")) {
            return "C_PUSH";
        } else if (command.equalsIgnoreCase("pop")) {
            return "C_POP";
        } else if (command.equalsIgnoreCase("label")) {
            return "C_LABEL";
        } else if (command.equalsIgnoreCase("goto")) {
            return "C_GOTO";
        } else if (command.equalsIgnoreCase("if-goto")) {
            return "C_IF";
        } else if (command.equalsIgnoreCase("function")) {
            return "C_FUNCTION";
        } else if (command.equalsIgnoreCase("return")) {
            return "C_RETURN";
        } else if (command.equalsIgnoreCase("call")) {
            return "C_CALL";
        }
    }

    public String arg1() {
        if (commandType().equals("C_ARITHMETIC")) {
            return command;
        } else if (commandType().equals("C_RETURN")) {
            return "NULL";
        } else {
            return arg1;
        }
    }

    public int arg2() {
        if (commandType().equals("C_PUSH") || commandType().equals("C_POP") || commandType().equals("C_FUNCTION") || commandType().equals("C_CALL")) {
            return arg2;
        } else {
            return -1;
        }
    }
}
