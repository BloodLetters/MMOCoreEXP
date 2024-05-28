package me.ashesh.Utils;
import java.util.*;

public class calculator {

    public static double evaluate(String expression) {
        List<String> postfix = infixToPostfix(expression);
        return evaluatePostfix(postfix);
    }

    private static List<String> infixToPostfix(String expression) {
        List<String> result = new ArrayList<>();
        Stack<String> stack = new Stack<>();
        StringTokenizer tokenizer = new StringTokenizer(expression, "()+-*/%", true);

        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken().trim();
            if (token.isEmpty()) {
                continue;
            }
            if (isNumber(token)) {
                result.add(token);
            } else if (token.equals("(")) {
                stack.push(token);
            } else if (token.equals(")")) {
                while (!stack.isEmpty() && !stack.peek().equals("(")) {
                    result.add(stack.pop());
                }
                stack.pop();
            } else if (isOperator(token)) {
                while (!stack.isEmpty() && precedence(stack.peek()) >= precedence(token)) {
                    result.add(stack.pop());
                }
                stack.push(token);
            }
        }

        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }

        return result;
    }

    private static double evaluatePostfix(List<String> postfix) {
        Stack<Double> stack = new Stack<>();

        for (String token : postfix) {
            if (isNumber(token)) {
                stack.push(Double.parseDouble(token));
            } else if (isOperator(token)) {
                if (token.equals("%")) {
                    double a = stack.pop();
                    stack.push(a / 100);
                } else {
                    double b = stack.pop();
                    double a = stack.pop();
                    switch (token) {
                        case "+": stack.push(a + b); break;
                        case "-": stack.push(a - b); break;
                        case "*": stack.push(a * b); break;
                        case "/": stack.push(a / b); break;
                    }
                }
            }
        }

        return stack.pop();
    }

    private static boolean isNumber(String token) {
        try {
            Double.parseDouble(token);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean isOperator(String token) {
        return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/") || token.equals("%");
    }

    private static int precedence(String operator) {
        switch (operator) {
            case "+": case "-": return 1;
            case "*": case "/": return 2;
            case "%": return 3;
            default: return 0;
        }
    }

//    public static void main(String[] args) {
//        String expression = "1 + (2 / 3) * 5 %";
//        System.out.println("Result: " + evaluate(expression));
//    }
}
