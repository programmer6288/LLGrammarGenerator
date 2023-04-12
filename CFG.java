import java.util.*;
import symbol.*;

public class CFG {
    public Symbol startSymbol;
    public Set<String> nonTerminals;
    public Set<String> terminals;
    public Map<String, Set<List<Symbol>>> prodRules;
}