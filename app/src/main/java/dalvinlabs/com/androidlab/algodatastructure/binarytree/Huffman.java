package dalvinlabs.com.androidlab.algodatastructure.binarytree;

import java.util.Arrays;

import dalvinlabs.com.androidlab.algodatastructure.priorityqueue.PriorityQueueGeneric;

/*
    1. Create a frequency table.
    2. Create a priority queue of 1 node binary trees. Lower frequency trees have higher priority.
    3. Create Huffman Tree from priority queue, We follow bottom up tree creation as we want to have
        frequency nodes at the top of tree.
    4. Create Codes Table by traversing Tree
    5. Use Codes Table to encode message, Technically you can use Tree to encode every time a character is
        required to encode but that won't be efficient.
    6. Use Tree to decode message
 */
public class Huffman {

    // ~ to represent new line
    // - to represent space
    private static char[] alphabets = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L'
    , 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '-', '~'};
    private static int[] frequency = new int[28];
    static String[] codesTable = new String[28];
    private static String message;
    private static PriorityQueueGeneric<BinaryTree> priorityQueue = new PriorityQueueGeneric<>(26); // 26 characters supported in message.
    static BinaryTree huffmanTree;

    static {
        for (int i = 0; i < codesTable.length; i++) {
            codesTable[i] = "";
        }
    }

    /*
        Only cap letters from A - Z are allowed
     */
    static void createFrequencyTable(String input) {
        message = input;
        char character;
        for (int i = 0; i < message.length(); i++) {
            character = message.charAt(i);
            if (character >= 65 && character <= 90) {
                frequency[character - 65]++;
            } else if (character == ' ') {
                frequency[26]++;
            } else if (character == '\n') {
                frequency[27]++;
            }
        }
        System.out.println(Arrays.toString(alphabets));
        System.out.println(Arrays.toString(frequency));
    }

    static void generatePriorityQueueFromFrequencyTable() {
        BinaryTree.Node node;
        String data = "";
        for (int i = 0; i < frequency.length; i++) {
            if (frequency[i] > 0) {
                if (i < 26) {
                    data = Character.valueOf((char) (i + 65)).toString();
                } else if (i == 26) {
                    data = "-";
                } else if (i == 27) {
                    data = "~";
                }
                node = new BinaryTree.Node(data);
                node.frequency = frequency[i];
                priorityQueue.insert(new BinaryTree(node));
            }
        }
    }

    static void createHuffmanTreeFromPriorityQueue() {
        BinaryTree left;
        BinaryTree right;
        BinaryTree tree;
        while (priorityQueue.size() > 1) {
            left = priorityQueue.remove();
            right = priorityQueue.remove();
            tree = new BinaryTree(new BinaryTree.Node("+"));
            tree.root.frequency = left.root.frequency + right.root.frequency;
            tree.root.left = left.root;
            tree.root.right = right.root;
            priorityQueue.insert(tree);
        }
        huffmanTree = priorityQueue.remove();
        huffmanTree.printTree();
    }

    static void generateCodesFromHuffmanTree(BinaryTree.Node node, String code) {
        if (node.left != null) {
            generateCodesFromHuffmanTree(node.left, code + "0");
        }
        if (node.right != null) {
            generateCodesFromHuffmanTree(node.right, code + "1");
        }
        if (node.left == null && node.right == null) {
            // LEAF
            char character = node.data.charAt(0);
            if (character == '-') {
                codesTable[26] = code;
            } else if (character == '~') {
                codesTable[27] = code;
            } else {
                codesTable[character - 65] = code;
            }
        }
    }

    static String encodeMessage() {
        char character;
        String encodeMessage = "";
        for (int i = 0; i < message.length(); i++) {
            character = message.charAt(i);
            if (character == ' ') {
                encodeMessage += codesTable[26];
            } else if (character == '\n') {
                encodeMessage += codesTable[27];
            } else {
                encodeMessage += codesTable[character - 65];
            }
            // For better readability
            //encodeMessage += " ";
        }
        return encodeMessage;
    }

    static String decodeMessage(String encodedMessage) {
        String decodedMessage = "";
        BinaryTree.Node current = huffmanTree.root;
        for (int i = 0; i < encodedMessage.length(); i++) {
            int binary = Integer.valueOf(encodedMessage.substring(i, i+1));
            current = findOutAlphabetFromTree(current, binary);
            if (current.left == null && current.right == null) {
                //LEAF
                if (current.data.equalsIgnoreCase("-")) {
                    decodedMessage += " ";
                } else if (current.data.equalsIgnoreCase("~")) {
                    decodedMessage += "\n";
                } else {
                    decodedMessage += current.data;
                }
                current = huffmanTree.root;
            }
        }
        return decodedMessage;
    }

    private static BinaryTree.Node findOutAlphabetFromTree(BinaryTree.Node node, int binary) {
        if (binary == 0) {
            node = node.left;
        } else {
            node = node.right;
        }
        return node;
    }
}
