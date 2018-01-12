package dalvinlabs.com.androidlab.algodatastructure.stacks.use;


import dalvinlabs.com.androidlab.algodatastructure.stacks.LinkedListBased.StackGeneric;

class DelimiterParser {

    private static  StackGeneric<String> stack = new StackGeneric<>();

    /*
        Validates if opening brackets (, {, [ are followed by closing brackets ), }, ]
        For e.g. the way inner scope closes before outer in a code
     */
    static boolean validate(String delimiter) {
        if (delimiter.matches("[({\\[]")) {
            stack.push(delimiter);
            return true;
        } else {
            String openning = stack.pop();
            if (delimiter.equalsIgnoreCase(")")) {
                if (openning.equalsIgnoreCase("(")) {
                    return true;
                }
            } else if (delimiter.equalsIgnoreCase("}")) {
                if (openning.equalsIgnoreCase("{")) {
                    return true;
                }
            } else if (delimiter.equalsIgnoreCase("]")) {
                if (openning.equalsIgnoreCase("[")) {
                    return true;
                }
            }
            return false;
        }
    }
}
