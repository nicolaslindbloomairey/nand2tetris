import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;

public class CodeWriter {
    BufferedWriter out;

    public CodeWriter(String inputfile) throws IOException {
        out = new BufferedWriter(new FileWriter(new File(inputfile.substring(0, inputfile.indexOf('.')) + ".asm")));
    }

    //public void setFileName(String fileName) {

    public void writeArithmetic(String command) {
    }
}
