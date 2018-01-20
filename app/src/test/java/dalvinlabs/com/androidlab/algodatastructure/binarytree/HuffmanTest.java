package dalvinlabs.com.androidlab.algodatastructure.binarytree;


import junit.framework.Assert;

import org.junit.Test;

import java.util.Arrays;

public class HuffmanTest {

    @Test
    public void test() {
        String input = "SUSIE SAYS IT IS EASY\n";
        System.out.println(input);
        Huffman.createFrequencyTable(input);
        Huffman.generatePriorityQueueFromFrequencyTable();
        Huffman.createHuffmanTreeFromPriorityQueue();
        Huffman.generateCodesFromHuffmanTree(Huffman.huffmanTree.root, "");
        System.out.println("Codes Table");
        System.out.println(Arrays.toString(Huffman.codesTable));
        String encodedMessage = Huffman.encodeMessage();
        System.out.println("Encoded Message");
        System.out.println(encodedMessage);
        String decodedMessage = Huffman.decodeMessage(encodedMessage);
        System.out.println("Decoded Message");
        System.out.println(decodedMessage);
        Assert.assertEquals(input, decodedMessage);
    }
}
