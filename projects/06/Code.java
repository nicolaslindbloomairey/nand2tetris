public class Code {
    public static String dest(String in) {
        String d1, d2, d3;
        if (in.contains("M")) {
            d3 = "1";
        } else {
            d3 = "0";
        }

        if (in.contains("D")) {
            d2 = "1";
        } else {
            d2 = "0";
        }

        if (in.contains("A")) {
            d1 = "1";
        } else {
            d1 = "0";
        }

        return d1 + d2 + d3;
    }

    public static String comp(String in) {
        String a, c;
        if (in.contains("M")) {
            a = "1";
        } else {
            a = "0";
        }

        if (in.equals("0")) {
            c = "101010";
        } else if (in.equals("1")) {
            c = "111111";
        } else if (in.equals("-1")) {
            c = "111010";
        } else if (in.equals("D")) {
            c = "001100";
        } else if (in.equals("A") || in.equals("M")) {
            c = "110000";
        } else if (in.equals("!D")) {
            c = "001101";
        } else if (in.equals("!A") || in.equals("!M")) {
            c = "110001";
        } else if (in.equals("-D")) {
            c = "001111";
        } else if (in.equals("-A") || in.equals("-M")) {
            c = "110011";
        } else if (in.equals("D+1")) {
            c = "011111";
        } else if (in.equals("A+1") || in.equals("M+1")) {
            c = "110111";
        } else if (in.equals("D-1")) {
            c = "001110";
        } else if (in.equals("A-1") || in.equals("M-1")) {
            c = "110010";
        } else if (in.equals("D+A") || in.equals("D+M") || in.equals("A+D") || in.equals("M+D")) {
            c = "000010";
        } else if (in.equals("D-A") || in.equals("D-M")) {
            c = "010011";
        } else if (in.equals("A-D") || in.equals("M-D")) {
            c = "000111";
        } else if (in.equals("D&A") || in.equals("D&M") || in.equals("A&D") || in.equals("M&D")) {
            c = "000000";
        } else if (in.equals("D|A") || in.equals("D|M") || in.equals("A|D") || in.equals("M|D")) {
            c = "010101";
        } else {
            c="000000";
        }

        return a + c;
    }

    public static String jump(String in) {
        if (in.equals("JGT")) {
            return "001";
        } else if (in.equals("JEQ")) {
            return "010";
        } else if (in.equals("JGE")) {
            return "011";
        } else if (in.equals("JLT")) {
            return "100";
        } else if (in.equals("JNE")) {
            return "101";
        } else if (in.equals("JLE")) {
            return "110";
        } else if (in.equals("JMP")) {
            return "111";
        } else {
            return "000";
        }
    }
}
