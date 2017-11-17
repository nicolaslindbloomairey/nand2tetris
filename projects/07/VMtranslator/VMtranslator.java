import java.io.IOException;

public class VMtranslator {
    public static void main(String[] args) throws IOException {
        Parser parser = new Parser(args[0]);
        CodeWriter out = new CodeWriter(args[0]);

        while (parser.hasMoreCommands()) {
            if (parser.commandType().equals("C_ARITHMETIC")) {
                out.writeArithmetic(parser.arg1());
            } else if (parser.commandType().equals("C_PUSH") || parser.commandType().equals("C_POP")) {
                out.writePushPop(parser.commandType(), parser.arg1(), parser.arg2());
            }

            parser.advance();
        }

        out.close();
    }
}
