package me.ashesh.Utils;
import java.util.*;

public class calculator {
//    public static void main(String[] args) {
//        String expression = "((1 + 1) * (1 * 2)) + 3";
//        String expressionWithRandom = "((((1 + 1) * (1 * 2)) + 3) = 100) / 2";
//        try {
//            double result = evaluateExpression(expression);
//            System.out.println("Hasil: " + result);
//
//            double resultWithRandom = evaluateExpression(expressionWithRandom);
//            System.out.println("Hasil dengan random: " + resultWithRandom);
//        } catch (Exception e) {
//            System.out.println("Terjadi kesalahan: " + e.getMessage());
//        }
//    }

    public static double evaluateExpression(String expression) throws Exception {
        Stack<Double> values = new Stack<>();
        Stack<Character> operators = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);

            if (Character.isWhitespace(ch)) {
                continue;
            }

            if (Character.isDigit(ch)) {
                StringBuilder sb = new StringBuilder();
                while (i < expression.length() && (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.')) {
                    sb.append(expression.charAt(i++));
                }
                i--; // adjust for the next iteration
                values.push(Double.parseDouble(sb.toString()));
            } else if (ch == '(') {
                operators.push(ch);
            } else if (ch == ')') {
                while (operators.peek() != '(') {
                    values.push(applyOperation(operators.pop(), values.pop(), values.pop()));
                }
                operators.pop(); // pop the '('
            } else if (ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '=') {
                while (!operators.isEmpty() && hasPrecedence(ch, operators.peek())) {
                    values.push(applyOperation(operators.pop(), values.pop(), values.pop()));
                }
                operators.push(ch);
            }
        }

        while (!operators.isEmpty()) {
            values.push(applyOperation(operators.pop(), values.pop(), values.pop()));
        }

        return values.pop();
    }

    public static boolean hasPrecedence(char op1, char op2) {
        if (op2 == '(' || op2 == ')') {
            return false;
        }
        if (op1 == '=' && (op2 == '+' || op2 == '-' || op2 == '*' || op2 == '/')) {
            return false;
        }
        if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-')) {
            return false;
        } else {
            return true;
        }
    }

    public static double applyOperation(char op, double b, double a) throws Exception {
        switch (op) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b == 0) {
                    throw new Exception("Divide with zero!");
                }
                return a / b;
            case '=':
                return getRandomInRange(a, b);
            default:
                throw new Exception("Operator not valid");
        }
    }

    public static double getRandomInRange(double min, double max) {
        Random rand = new Random();
        return min + (max - min) * rand.nextDouble();
    }
}
