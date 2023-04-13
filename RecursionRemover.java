import java.util.*;
import java.util.stream.Collectors;
import symbol.*;
import static symbol.Symbol.Type.*; 

public class RecursionRemover {
    public static void removeRecursion(CFG cfg) {
        List<String> nonTerminals = new ArrayList<>(cfg.nonTerminals);
        Set<String> processedNTs = new HashSet<>();
        for (int i = 0; i < nonTerminals.size(); i++) {
            String currNT = nonTerminals.get(i);
            for (int j = 0; j < i - 1; j++) {
                String innerNT = nonTerminals.get(j);
                List<List<Symbol>> prodsToAdd = new ArrayList<>();
                List<List<Symbol>> prodsToRemove = new ArrayList<>();
                for (List<Symbol> prod : cfg.prodRules.get(currNT)) {
                    if (prod.get(0).rep == innerNT) {
                        prodsToRemove.add(prod);
                        List<List<Symbol>> innerProdList = new ArrayList<>(cfg.prodRules.get(innerNT));
                        for (List<Symbol> exProd : innerProdList) {
                            List<Symbol> newProd = new ArrayList<>(exProd);
                            newProd.addAll(new ArrayList<>(prod.subList(1, prod.size())));
                            prodsToAdd.add(newProd);
                        }
                    }
                }
                cfg.prodRules.get(currNT).removeAll(prodsToRemove);
                cfg.prodRules.get(currNT).addAll(prodsToAdd);
            }
            List<List<Symbol>> recursiveRules = cfg.prodRules.get(currNT).stream().filter(prod -> prod.get(0).rep == currNT).collect(Collectors.toList());
            List<List<Symbol>> nonRecursiveRules = cfg.prodRules.get(currNT).stream().filter(prod -> prod.get(0).rep != currNT).collect(Collectors.toList());
            if (recursiveRules.size() > 0) {
                cfg.prodRules.remove(currNT);
                Set<List<Symbol>> newCurrNTRules = new HashSet<>();
                for (List<Symbol> rule : nonRecursiveRules) {
                    rule.add(new Symbol(NONTERMINAL, currNT+"*"));
                    newCurrNTRules.add(rule);
                }
                Set<List<Symbol>> newNTRules = new HashSet<>();
                for (List<Symbol> rule : recursiveRules) {
                    List<Symbol> newRule = new ArrayList<>();
                    newRule.addAll(new ArrayList<>(rule.subList(1, rule.size())));
                    newRule.add(new Symbol(NONTERMINAL, currNT+"*"));
                    newNTRules.add(newRule);
                }
                newNTRules.add(new ArrayList<>(List.of(new Symbol(TERMINAL, ""))));
                cfg.prodRules.put(currNT, newCurrNTRules);
                cfg.prodRules.put(currNT+"*", newNTRules);
                processedNTs.add(currNT);
            }
        }
    }
}