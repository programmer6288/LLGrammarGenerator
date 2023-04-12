package symbol;

public class Symbol {
    public enum Type {
        TERMINAL, NONTERMINAL
    }
    public Type type;
    public String rep;
    public Symbol(Type type, String rep) {
        this.type = type;
        this.rep = rep;
    }
}