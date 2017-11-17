import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class Parser {

    ArrayList<String> file = new ArrayList<String>(); //file with each line as a string in the list
    int c; //current index
    String command; //current command

    public Parser(String filename) {
        try {
            Scanner scan = new Scanner(new File(filename));
            while(scan.hasNextLine()) {
                String nextline = scan.nextLine();
                nextline = nextline.trim();
                if (!nextline.startsWith("//")) {
                    if (nextline.contains("//")) {
                        String woComment = nextline.substring(0, nextline.indexOf("/")).trim();
                        file.add(woComment);
                    } else {
                        file.add(nextline);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        reset();
    }

    public void reset() {
        c=0;
        command = file.get(c);
    }

    public boolean hasMoreCommands() {
        return c<(file.size());
    }

    public void advance() {
        c++;
        if (hasMoreCommands()) {
            command = file.get(c);
        }
    }

    public String commandType() {
        if (command.contains("@")) {
            return "A_COMMAND";
        } else if (command.contains("(") && command.contains(")")) {
            return "L_COMMAND";
        } else if (command.contains("D") || command.contains("A") || command.contains("M")) {
            return "C_COMMAND";
        }
        return "";
    }

    public String symbol() {
        if (commandType().equals("A_COMMAND")) {
            return command.substring(command.indexOf('@')+1);
        } else if (commandType().equals("L_COMMAND")) {
            return command.substring(command.indexOf('(')+1, command.indexOf(')'));
        }
        return "";
    }

    public String dest() {
        if (commandType().equals("C_COMMAND")) {
            if (command.contains("=")) {
                return command.substring(0, command.indexOf('='));
            }
        }
        return "";
    }

    public String comp() {
        if (commandType().equals("C_COMMAND")) {
            if (command.contains("=")) {
                if (command.contains(";")) {
                    return command.substring(command.indexOf('=')+1, command.indexOf(';'));
                } else {
                    return command.substring(command.indexOf('=')+1);
                }
            } else {
                if (command.contains(";")) {
                    return command.substring(0, command.indexOf(';'));
                } else {
                    return command;
                }
            }
        }
        return "";
    }

    public String jump() {
        if (commandType().equals("C_COMMAND")) {
            if (command.contains(";")) {
                return command.substring(command.indexOf(';')+1);
            }
        }
        return "";
    }
}
