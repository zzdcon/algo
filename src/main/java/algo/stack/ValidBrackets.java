package algo.stack;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Stack;

public class ValidBrackets {

    Map<Character, Character> bracketMap = new HashMap<>();

    public ValidBrackets() {
        bracketMap.put(']', '[');
        bracketMap.put(')', '(');
        bracketMap.put('}', '{');
    }

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i=0; i<s.length(); i++) {
            char c = s.charAt(i);
            if(bracketMap.values().contains(c)) {
                // 出现左括号，直接入栈
                stack.push(s.charAt(i));
            } else if (bracketMap.keySet().contains(c)) {
                // 出现右括号
                if (stack.size() == 0) {
                    return false;
                }

                // 从栈中弹出右括号对应的左括号
                char pop = stack.pop();
                if (pop != (bracketMap.get(c))) {
                    // 左、右括号不匹配，返回false
                    return false;
                }
            }
        }
        return stack.size() == 0 ? true : false;
    }


}
