package dalvinlabs.com.androidlab.algodatastructure.stacks.use;


import dalvinlabs.com.androidlab.algodatastructure.stacks.ArrayBased.FlexibleCapacityStackOfStringsV4;

class ReverseWord {

    static String run(String word) {
        FlexibleCapacityStackOfStringsV4 stack = new FlexibleCapacityStackOfStringsV4();
        for (int i = 0; i < word.length(); i++) {
            try {
                stack.push(word.substring(i, i+1));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        String reverse = "";
        for (int i = 0; i < word.length(); i++) {
            try {
                reverse += stack.pop();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return reverse;
    }

}
