package com.example.todoshpp;

import java.util.Stack;

public class A {
    public static void main(String[] args) {
        String s = "()";
        String s1 = "ass(ddff))";
        s = s.replace("()", "").replace("{}", "");
        System.out.println(checkValide(s));
        System.out.println(checkValide(s1));
    }

    public static Boolean checkValide(String str) {
        char sym;
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            sym = str.charAt(i);
            if (sym == '(' || sym == '[') stack.push(sym);
            else if (stack.empty()) return false;
            else if (sym == ')' && stack.pop() != '(') return false;
            else if (sym == ']' && stack.pop() != '[') return false;
        }
        return stack.empty();
    }
}
