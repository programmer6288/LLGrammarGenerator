import symbol.*;
import static symbol.Symbol.Type.*; 

import java.util.*;
import static java.util.Map.entry;
public class GrammarGenerator {
    public static void main(String[] args) {
        CFG cfg = new CFG();
        cfg.startSymbol = new Symbol(NONTERMINAL, "tiger-program");
        /*
        cfg.prodRules = new HashMap<>(Map.ofEntries(
            entry("Fee", new HashSet<>(Set.of(
                new ArrayList<>(List.of(
                    new Symbol(NONTERMINAL, "Fee"),
                    new Symbol(TERMINAL, "a")
                )),
                new ArrayList<>(List.of(
                    new Symbol(TERMINAL, "b")
                ))
            )))
        ));
        */

        cfg.prodRules = new HashMap<>(Map.ofEntries(
            entry("Expr", new HashSet<>(Set.of(
                new ArrayList<>(List.of(
                    new Symbol(NONTERMINAL, "Expr"),
                    new Symbol(TERMINAL, "+"),
                    new Symbol(NONTERMINAL, "Term")
                )),
                new ArrayList<>(List.of(
                    new Symbol(NONTERMINAL, "Expr"),
                    new Symbol(TERMINAL, "-"),
                    new Symbol(NONTERMINAL, "Term")
                )),
                new ArrayList<>(List.of(
                    new Symbol(TERMINAL, "Term")
                ))
            ))),
            entry("Term", new HashSet<>(Set.of(
                new ArrayList<>(List.of(
                    new Symbol(NONTERMINAL, "Term"),
                    new Symbol(TERMINAL, "*"),
                    new Symbol(NONTERMINAL, "Factor")
                )),
                new ArrayList<>(List.of(
                    new Symbol(NONTERMINAL, "Term"),
                    new Symbol(TERMINAL, "/"),
                    new Symbol(NONTERMINAL, "Factor")
                )),
                new ArrayList<>(List.of(
                    new Symbol(NONTERMINAL, "Factor")
                ))
            )))
        ));

        /*
        cfg.prodRules = new HashMap<>(Map.ofEntries(
            entry("tiger-program", new HashSet<>(Set.of(
                new ArrayList<>(List.of(
                    new Symbol(TERMINAL, "main"),
                    new Symbol(TERMINAL, "let"),
                    new Symbol(NONTERMINAL, "declaration-statement"),
                    new Symbol(TERMINAL, "in"),
                    new Symbol(TERMINAL, "begin"),
                    new Symbol(NONTERMINAL, "stat-seq"),
                    new Symbol(TERMINAL, "end")
                ))
            ))),
            entry("declaration-statement", new HashSet<>(Set.of(
                new ArrayList<>(List.of(
                    new Symbol(NONTERMINAL, "var-declaration-list"),
                    new Symbol(NONTERMINAL, "funct-declaration-list")
                ))
            ))),
            entry("var-declaration-list", new HashSet<>(Set.of(
                new ArrayList<>(List.of(
                    new Symbol(TERMINAL, "")
                )),
                new ArrayList<>(List.of(
                    new Symbol(NONTERMINAL, "var-declaration"),
                    new Symbol(NONTERMINAL, "var-declaration-list")
                ))
            ))),
            entry("var-declaration", new HashSet<>(Set.of(
                new ArrayList<>(List.of(
                    new Symbol(TERMINAL, "var"),
                    new Symbol(NONTERMINAL, "id-list"),
                    new Symbol(TERMINAL, ":"),
                    new Symbol(NONTERMINAL, "type"),
                    new Symbol(NONTERMINAL, "optional-init"),
                    new Symbol(TERMINAL, ";")
                ))
            ))),
            entry("funct-declaration-list", new HashSet<>(Set.of(
                new ArrayList<>(List.of(
                    new Symbol(TERMINAL, "")
                )),
                new ArrayList<>(List.of(
                    new Symbol(NONTERMINAL, "funct-declaration"),
                    new Symbol(NONTERMINAL, "funct-declaration-list")
                ))
            ))),
            entry("funct-declaration", new HashSet<>(Set.of(
                new ArrayList<>(List.of(
                    new Symbol(TERMINAL, "function"),
                    new Symbol(TERMINAL, "ID"),
                    new Symbol(TERMINAL, "("),
                    new Symbol(NONTERMINAL, "param-list"),
                    new Symbol(TERMINAL, ")"),
                    new Symbol(NONTERMINAL, "ret-type"),
                    new Symbol(TERMINAL, "begin"),
                    new Symbol(NONTERMINAL, "stat-seq"),
                    new Symbol(TERMINAL, "end")
                ))
            ))),
            entry("type", new HashSet<>(Set.of(
                new ArrayList<>(List.of(
                    new Symbol(NONTERMINAL, "type-id")
                )),
                new ArrayList<>(List.of(
                    new Symbol(TERMINAL, "array"),
                    new Symbol(TERMINAL, "["),
                    new Symbol(TERMINAL, "INTLIT"),
                    new Symbol(TERMINAL, "]"),
                    new Symbol(TERMINAL, "of"),
                    new Symbol(NONTERMINAL, "type-id")
                ))
            ))),
            entry("type-id", new HashSet<>(Set.of(
                new ArrayList<>(List.of(
                    new Symbol(TERMINAL, "int")
                )),
                new ArrayList<>(List.of(
                    new Symbol(TERMINAL, "float")
                ))
            ))),
            entry("id-list", new HashSet<>(Set.of(
                new ArrayList<>(List.of(
                    new Symbol(TERMINAL, "ID")
                )),
                new ArrayList<>(List.of(
                    new Symbol(TERMINAL, "ID"),
                    new Symbol(TERMINAL, ","),
                    new Symbol(NONTERMINAL, "id-list")
                ))
            ))),
            entry("optional-init", new HashSet<>(Set.of(
                new ArrayList<>(List.of(
                    new Symbol(TERMINAL, "")
                )),
                new ArrayList<>(List.of(
                    new Symbol(TERMINAL, ":="),
                    new Symbol(NONTERMINAL, "const")
                ))
            ))),
            entry("param-list", new HashSet<>(Set.of(
                new ArrayList<>(List.of(
                    new Symbol(TERMINAL, "")
                )),
                new ArrayList<>(List.of(
                    new Symbol(NONTERMINAL, "param"),
                    new Symbol(NONTERMINAL, "param-list-tail")
                ))
            ))),
            entry("param-list-tail", new HashSet<>(Set.of(
                new ArrayList<>(List.of(
                    new Symbol(TERMINAL, "")
                )),
                new ArrayList<>(List.of(
                    new Symbol(TERMINAL, ","),
                    new Symbol(NONTERMINAL, "param"),
                    new Symbol(NONTERMINAL, "param-list-tail")
                ))
            ))),
            entry("ret-type", new HashSet<>(Set.of(
                new ArrayList<>(List.of(
                    new Symbol(TERMINAL, "")
                )),
                new ArrayList<>(List.of(
                    new Symbol(TERMINAL, ";"),
                    new Symbol(NONTERMINAL, "type")
                ))
            ))),
            entry("param", new HashSet<>(Set.of(
                new ArrayList<>(List.of(
                    new Symbol(TERMINAL, "ID"),
                    new Symbol(TERMINAL, ":"),
                    new Symbol(NONTERMINAL, "type")
                ))
            ))),
            entry("stat-seq", new HashSet<>(Set.of(
                new ArrayList<>(List.of(
                    new Symbol(NONTERMINAL, "stat")
                )),
                new ArrayList<>(List.of(
                    new Symbol(NONTERMINAL, "stat"),
                    new Symbol(NONTERMINAL, "stat-seq")
                ))
            ))),
            entry("stat", new HashSet<>(Set.of(
                new ArrayList<>(List.of(
                    new Symbol(NONTERMINAL, "lvalue"),
                    new Symbol(TERMINAL, ":="),
                    new Symbol(NONTERMINAL, "expr"),
                    new Symbol(TERMINAL, ";")
                )),
                new ArrayList<>(List.of(
                    new Symbol(TERMINAL, "if"),
                    new Symbol(NONTERMINAL, "expr"),
                    new Symbol(TERMINAL, "then"),
                    new Symbol(NONTERMINAL, "stat-seq"),
                    new Symbol(TERMINAL, "endif"),
                    new Symbol(TERMINAL, ";")
                )),
                new ArrayList<>(List.of(
                    new Symbol(TERMINAL, "if"),
                    new Symbol(NONTERMINAL, "expr"),
                    new Symbol(TERMINAL, "then"),
                    new Symbol(NONTERMINAL, "stat-seq"),
                    new Symbol(TERMINAL, "else"),
                    new Symbol(NONTERMINAL, "stat-seq"),
                    new Symbol(TERMINAL, "endif"),
                    new Symbol(TERMINAL, ";")
                )),
                new ArrayList<>(List.of(
                    new Symbol(TERMINAL, "while"),
                    new Symbol(NONTERMINAL, "expr"),
                    new Symbol(TERMINAL, "do"),
                    new Symbol(NONTERMINAL, "stat-seq"),
                    new Symbol(TERMINAL, "enddo"),
                    new Symbol(TERMINAL, ";")
                )),
                new ArrayList<>(List.of(
                    new Symbol(TERMINAL, "for"),
                    new Symbol(TERMINAL, "ID"),
                    new Symbol(TERMINAL, ":="),
                    new Symbol(NONTERMINAL, "expr"),
                    new Symbol(TERMINAL, "to"),
                    new Symbol(NONTERMINAL, "expr"),
                    new Symbol(TERMINAL, "do"),
                    new Symbol(NONTERMINAL, "stat-seq"),
                    new Symbol(TERMINAL, "enddo"),
                    new Symbol(TERMINAL, ";")
                )),
                new ArrayList<>(List.of(
                    new Symbol(NONTERMINAL, "opt-prefix"),
                    new Symbol(TERMINAL, "ID"),
                    new Symbol(TERMINAL, "("),
                    new Symbol(NONTERMINAL, "expr-list"),
                    new Symbol(TERMINAL, ")"),
                    new Symbol(TERMINAL, ";")
                )),
                new ArrayList<>(List.of(
                    new Symbol(TERMINAL, "break"),
                    new Symbol(TERMINAL, ";")
                )),
                new ArrayList<>(List.of(
                    new Symbol(TERMINAL, "return"),
                    new Symbol(NONTERMINAL, "expr"),
                    new Symbol(TERMINAL, ";")
                )),
                new ArrayList<>(List.of(
                    new Symbol(TERMINAL, "let"),
                    new Symbol(NONTERMINAL, "declaration-statement"),
                    new Symbol(TERMINAL, "in"),
                    new Symbol(NONTERMINAL, "stat-seq"),
                    new Symbol(TERMINAL, "end")
                ))
            ))),
            entry("opt-prefix", new HashSet<>(Set.of(
                new ArrayList<>(List.of(
                    new Symbol(NONTERMINAL, "lvalue"),
                    new Symbol(TERMINAL, ":=")
                )),
                new ArrayList<>(List.of(
                    new Symbol(TERMINAL, "")
                ))
            ))),
            entry("expr", new HashSet<>(Set.of(
                new ArrayList<>(List.of(
                    new Symbol(NONTERMINAL, "const")
                )),
                new ArrayList<>(List.of(
                    new Symbol(NONTERMINAL, "lvalue")
                )),
                new ArrayList<>(List.of(
                    new Symbol(NONTERMINAL, "expr"),
                    new Symbol(NONTERMINAL, "binary-operator"),
                    new Symbol(NONTERMINAL, "expr")
                )),
                new ArrayList<>(List.of(
                    new Symbol(TERMINAL, "("),
                    new Symbol(NONTERMINAL, "expr"),
                    new Symbol(TERMINAL, ")")
                ))
            ))),
            entry("const", new HashSet<>(Set.of(
                new ArrayList<>(List.of(
                    new Symbol(TERMINAL, "INTLIT")
                )),
                new ArrayList<>(List.of(
                    new Symbol(TERMINAL, "FLOATLIT")
                ))
            ))),
            entry("binary-operator", new HashSet<>(Set.of(
                new ArrayList<>(List.of(
                    new Symbol(TERMINAL, "+")
                )),
                new ArrayList<>(List.of(
                    new Symbol(TERMINAL, "-")
                )),
                new ArrayList<>(List.of(
                    new Symbol(TERMINAL, "*")
                )),
                new ArrayList<>(List.of(
                    new Symbol(TERMINAL, "/")
                )),
                new ArrayList<>(List.of(
                    new Symbol(TERMINAL, "=")
                )),
                new ArrayList<>(List.of(
                    new Symbol(TERMINAL, "<>")
                )),
                new ArrayList<>(List.of(
                    new Symbol(TERMINAL, "<")
                )),
                new ArrayList<>(List.of(
                    new Symbol(TERMINAL, ">")
                )),
                new ArrayList<>(List.of(
                    new Symbol(TERMINAL, "<=")
                )),
                new ArrayList<>(List.of(
                    new Symbol(TERMINAL, ">=")
                )),
                new ArrayList<>(List.of(
                    new Symbol(TERMINAL, "&")
                )),
                new ArrayList<>(List.of(
                    new Symbol(TERMINAL, "|")
                ))
            ))),
            entry("expr-list", new HashSet<>(Set.of(
                new ArrayList<>(List.of(
                    new Symbol(TERMINAL, "")
                )),
                new ArrayList<>(List.of(
                    new Symbol(NONTERMINAL, "expr"),
                    new Symbol(NONTERMINAL, "expr-list-tail")
                ))
            ))),
            entry("expr-list-tail", new HashSet<>(Set.of(
                new ArrayList<>(List.of(
                    new Symbol(TERMINAL, ","),
                    new Symbol(NONTERMINAL, "expr"),
                    new Symbol(NONTERMINAL, "expr-list-tail")
                )),
                new ArrayList<>(List.of(
                    new Symbol(TERMINAL, "")
                ))
            ))),
            entry("lvalue", new HashSet<>(Set.of(
                new ArrayList<>(List.of(
                    new Symbol(TERMINAL, "ID"),
                    new Symbol(NONTERMINAL, "lvalue-tail")
                ))
            ))),
            entry("lvalue-tail", new HashSet<>(Set.of(
                new ArrayList<>(List.of(
                    new Symbol(TERMINAL, "["),
                    new Symbol(NONTERMINAL, "expr"),
                    new Symbol(TERMINAL, "]")
                )),
                new ArrayList<>(List.of(
                    new Symbol(TERMINAL, "")
                ))
            )))
        ));
        */

        cfg.nonTerminals = new HashSet<>(cfg.prodRules.keySet());
        RecursionRemover.removeRecursion(cfg);
        cfg.prodRules.entrySet().forEach(entry -> {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        });
    }
}