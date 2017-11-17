import java.util.HashMap;
import java.util.Map;

public class SymbolTable {
    Map<String,Integer> symboltable;

    public SymbolTable() {
        symboltable = new HashMap<String,Integer>();
    }

    public void addEntry(String symbol, int address) {
        symboltable.put(symbol, address);
    }

    public boolean contains(String symbol) {
        return symboltable.containsKey(symbol);
    }

    public int GetAddress(String symbol) {
        return symboltable.get(symbol);
    }
}
