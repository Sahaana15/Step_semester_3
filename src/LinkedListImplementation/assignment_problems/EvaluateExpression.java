package LinkedListImplementation.assignment_problems;
import java.util.*;

public class EvaluateExpression {

    static int precedence(char op) {
        if (op == '+' || op == '-')
            return 1;

        if (op == '*' || op == '/')
            return 2;

        return 0;
    }

    static void calculate(Stack<Integer> values,
                          Stack<Character> operators) {

        int b = values.pop();
        int a = values.pop();
        char op = operators.pop();

        switch (op) {
            case '+':
                values.push(a + b);
                break;

            case '-':
                values.push(a - b);
                break;

            case '*':
                values.push(a * b);
                break;

            case '/':
                values.push(a / b);
                break;
        }
    }

    static int evaluate(String expr) {
        Stack<Integer> values = new Stack<>();
        Stack<Character> operators = new Stack<>();

        for (int i = 0; i < expr.length(); i++) {
            char ch = expr.charAt(i);

            if (ch == ' ') {
                continue;
            }

            if (Character.isDigit(ch)) {
                int num = 0;

                while (i < expr.length() &&
                        Character.isDigit(expr.charAt(i))) {

                    num = num * 10 + (expr.charAt(i) - '0');
                    i++;
                }

                i--;
                values.push(num);
            }

            else if (ch == '(') {
                operators.push(ch);
            }

            else if (ch == ')') {
                while (!operators.isEmpty() &&
                        operators.peek() != '(') {

                    calculate(values, operators);
                }

                operators.pop();
            }

            else {
                while (!operators.isEmpty() &&
                        operators.peek() != '(' &&
                        precedence(operators.peek()) >= precedence(ch)) {

                    calculate(values, operators);
                }

                operators.push(ch);
            }
        }

        while (!operators.isEmpty()) {
            calculate(values, operators);
        }

        return values.pop();
    }

    public static void main(String[] args) {
        System.out.println(evaluate("2 * (3 + 4) - 10 / 5"));
        System.out.println(evaluate("(8 - 2) * (1 + 2) / 4"));
    }
}