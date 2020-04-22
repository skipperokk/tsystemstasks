package com.tsystems.javaschool.tasks.calculator;

import java.util.Objects;
import java.util.Stack;

public class Calculator {

    /**
     * Evaluate statement represented as string.
     *
     * The task is implemented using Reverse Polish Notation (rpn)
     *
     * @param statement mathematical statement containing digits, '.' (dot) as decimal mark,
     *                  parentheses, operations signs '+', '-', '*', '/'<br>
     *                  Example: <code>(1 + 38) * 4.5 - 1 / 2.</code>
     * @return string value containing result of evaluation or null if statement is invalid
     */

    public String evaluate(String statement) {
        try {
            double result = rpnToAnswer(Objects.requireNonNull(expressionToRpn(statement)));
            if (Double.isInfinite(result)) {
                return null;
            }
            if (result == Math.round(result)) {
                return "" + (int) result;
            }
            return "" + result;
        } catch (Exception e) {
            return null;
        }
    }

    private String expressionToRpn(String expression) {
        int priority;
        int countOfBrackets = 0;

        StringBuilder current = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            priority = getPriority(expression.charAt(i));
            if (priority == 0) {
                current.append(expression.charAt(i));
            }
            if (priority == 1) {
                stack.push(expression.charAt(i));
                countOfBrackets++;
            }
            if (priority > 1) {
                current.append(' ');
                while (!stack.empty()) {
                    if (getPriority(stack.peek()) >= priority)
                        current.append(stack.pop());
                    else break;
                }
                stack.push(expression.charAt(i));
            }
            if (priority == -1) {
                current.append(' ');
                while (getPriority(stack.peek()) != 1) {
                    current.append(stack.pop());
                }
                stack.pop();
                countOfBrackets--;
            }
        }
        while (!stack.empty()) {
            current.append(stack.pop());
        }
        if (countOfBrackets == 0) {
            return current.toString();
        }
        return null;
    }

    private double rpnToAnswer(String rpn) {
        StringBuilder operand = new StringBuilder();
        Stack<Double> stack = new Stack<>();

        for (int i = 0; i < rpn.length(); i++) {
            if (rpn.charAt(i) == ' ') {
                continue;
            }
            if (getPriority(rpn.charAt(i)) == 0) {
                while (rpn.charAt(i) != ' ' && getPriority(rpn.charAt(i)) == 0) {
                    operand.append(rpn.charAt(i++));
                    if (i == rpn.length()) {
                        break;
                    }
                }
                stack.push(Double.parseDouble(operand.toString()));
                operand = new StringBuilder();
            }
            if (getPriority(rpn.charAt(i)) > 1) {
                double a = stack.pop();
                double b = stack.pop();

                if (rpn.charAt(i) == '+') {
                    stack.push(b + a);
                }
                if (rpn.charAt(i) == '-') {
                    stack.push(b - a);
                }
                if (rpn.charAt(i) == '*') {
                    stack.push(b * a);
                }
                if (rpn.charAt(i) == '/') {
                    stack.push(b / a);
                }
            }
        }
        return stack.pop();
    }

    private int getPriority(char token) {
        if (token == '*' || token == '/') {
            return 3;
        } else if (token == '+' || token == '-') {
            return 2;
        } else if (token == '(') {
            return 1;
        } else if (token == ')') {
            return -1;
        } else {
            return 0;
        }
    }
}
