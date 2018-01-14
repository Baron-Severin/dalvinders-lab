package dalvinlabs.com.androidlab.algodatastructure.stacks.use;


import dalvinlabs.com.androidlab.algodatastructure.stacks.LinkedListBased.StackGeneric;

public class InfixToPostfix {

    static String convert(String infix) throws Exception{
        String postfix = "";
        StackGeneric<String> stack = new StackGeneric();
        String current = "";
        String opTop = "";
        String opThis = "";
        int opTopPrecedence = 0;
        int opThisPrecdence = 0;
        for (int i = 0; i < infix.length(); i++) {
            current = infix.substring(i, i+1);
            if (current.matches("\\d")) {
                // Operand, append to output
                postfix += current;
            } else if (current.equalsIgnoreCase("(")) {
                // Open bracket push it to stack
                stack.push(current);
            } else if (current.equalsIgnoreCase(")")) {
                while (!stack.isEmpty()) {
                    opTop = stack.pop();
                    if (opTop.equalsIgnoreCase("(")) {
                        break;
                    } else {
                        postfix += opTop;
                    }
                }
            } else {
                if (stack.isEmpty()) {
                    stack.push(current);
                } else {
                    opThis = current;
                    while (!stack.isEmpty()) {
                        opTop = stack.pop();
                        if (opTop.equalsIgnoreCase("(")) {
                            stack.push(opTop);
                            break;
                        }
                        opTopPrecedence = precedence(opTop);
                        opThisPrecdence = precedence(opThis);
                        if (opTopPrecedence >= opThisPrecdence) {
                            // Keep on popping and adding into output if operator in stack has higher precedence than current operator
                            postfix += opTop;
                        } else {
                            stack.push(opTop);
                            break;
                        }
                    }
                    stack.push(current);
                }
            }
        }
        while (!stack.isEmpty()) {
            postfix += stack.pop();
        }
        return postfix;
    }

    private static int precedence(String operator) throws Exception{
        switch (operator) {
            case "+":
            case "-":
                return 0;
            case "*":
            case "/":
                return 1;
            case "^":
                return 2;
            case "(":
                return 4;
            default: throw new Exception("Operator not supported");
        }
    }

    public static int calculate(String postfix) {
        String current;
        StackGeneric<Integer> stack = new StackGeneric<>();
        for (int i = 0; i < postfix.length(); i++) {
            current = postfix.substring(i, i+1);
            switch (current) {
                case "+":
                    stack.push(stack.pop() + stack.pop());
                    break;
                case "-":
                    int second = stack.pop();
                    int first = stack.pop();
                    stack.push(first - second);
                    break;
                case "*":
                    stack.push(stack.pop() * stack.pop());
                    break;
                case "/":
                    stack.push(stack.pop() / stack.pop());
                    break;
                case "^":
                    int power = stack.pop();
                    int base = stack.pop();
                    double result = Math.pow(base, power);
                    stack.push((int)result);
                    break;
                default:
                    stack.push(Integer.parseInt(current));
            }
        }
        return stack.pop();
    }


}
