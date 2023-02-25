package LeetCode4;

import java.util.Map;
import java.util.TreeMap;

public class Atoms {
    public String countOfAtoms(String formula) {
        TreeMap<String, Integer> elems = new TreeMap<>();
        parse(formula, 0, elems);
        StringBuilder result = new StringBuilder();
        for (String key : elems.keySet()) {
            result.append(key);
            if (elems.get(key) > 1) {
                result.append(elems.get(key));
            }
        }
        return result.toString();
    }

    public int parse(String formula, int index, Map<String, Integer> upperElems) {
        TreeMap<String, Integer> elems = new TreeMap<>();
        for (int i = index; i < formula.length(); i++) {
            char ch = formula.charAt(i);
            if (ch >= 'A' && ch <= 'Z') {
                int start = i;
                i++;
                while (i < formula.length() && formula.charAt(i) >= 'a' && formula.charAt(i) <= 'z') {
                    i++;
                }

                if (i >= formula.length()) {
                    addOrMake(elems, formula.substring(start, i), 1);
                    break;
                } else if (formula.charAt(i) >= '0' && formula.charAt(i) <= '9') {
                    addOrMake(elems, formula.substring(start, i), getInt(formula, i));
                } else {
                    addOrMake(elems, formula.substring(start, i), 1);
                    i--;
                }
            } else if (ch == '(') {
                i++;
                i = parse(formula, i, elems);
            } else if (ch == ')') {
                if ((i + 1) < formula.length() && formula.charAt(i + 1) >= '0' && formula.charAt(i + 1) <= '9') {
                    i++;
                    multiply(elems, getInt(formula, i));
                }
                merge(upperElems, elems);
                return i;
            }
        }
        merge(upperElems, elems);
        return formula.length();
    }

    public void addOrMake(Map<String, Integer> elems, String key, int value) {
        if (elems.containsKey(key)) {
            elems.put(key, elems.get(key) + value);
        } else {
            elems.put(key, value);
        }
    }

    public void merge(Map<String, Integer> elems1, Map<String, Integer> elems2) {
        for (String key : elems2.keySet()) {
            addOrMake(elems1, key, elems2.get(key));
        }
    }

    public void multiply(Map<String, Integer> elems, int value) {
        elems.replaceAll((k, v) -> elems.get(k) * value);
    }

    public int getInt(String formula, int index) {
        int start = index;
        while (index < formula.length() && formula.charAt(index) >= '0' && formula.charAt(index) <= '9') {
            index++;
        }
        return Integer.parseInt(formula.substring(start, index));
    }
}
