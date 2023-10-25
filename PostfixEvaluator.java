import java.util.Stack;

public class PostfixEvaluator {
    private Stack<Integer> stack;

    public PostfixEvaluator() {
        stack = new Stack<>();
    }

    public void push(int value) {
        stack.push(value);
    }

    public int pop() {
        if (!isEmpty()) {
            return stack.pop();
        } else {
            throw new IllegalStateException("Stack is empty.");
        }
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public int evaluatePostfixExpression(String postfixExpression) {
        String[] tokens = postfixExpression.split("\\s+");

        for (String token : tokens) {
            if (isNumeric(token)) {
                int value = Integer.parseInt(token);
                push(value);
            } else if (isOperator(token)) {
                int operand2 = pop();
                int operand1 = pop();
                int result = applyOperator(operand1, operand2, token);
                push(result);
            }
        }

        return pop();
    }

    private boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isOperator(String str) {
        return str.matches("[+\\-*/]");
    }

    private int applyOperator(int operand1, int operand2, String operator) {
        switch (operator) {
            case "+":
                return operand1 + operand2;
            case "-":
                return operand1 - operand2;
            case "*":
                return operand1 * operand2;
            case "/":
                if (operand2 == 0) {
                    throw new ArithmeticException("Division by zero");
                }
                return operand1 / operand2;
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }

    public static void main(String[] args) {
        PostfixEvaluator evaluator = new PostfixEvaluator();
        String postfixExpression = "5 1 2 + 4 * + 3 -";
        int result = evaluator.evaluatePostfixExpression(postfixExpression);
        System.out.println("Result: " + result);
    }
}
