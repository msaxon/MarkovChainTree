package core;

import org.junit.Test;
import org.testng.Assert;

/**
 * Created by Matthew on 7/9/2017.
 */
public class WordMarkovChainTreeTest {
    @Test
    public void toString_WordTree() {
        //adding
        WordMarkovChainTree wordMarkovChainTree = new WordMarkovChainTree();
        wordMarkovChainTree.addString("Tom");

        String printedTree = wordMarkovChainTree.toString();
        Assert.assertTrue(printedTree.contains("t - o, ."));
        Assert.assertTrue(printedTree.contains("* - t, ."));
        Assert.assertTrue(printedTree.contains("m - +, ."));
        Assert.assertTrue(printedTree.contains("o - m, ."));
    }

    @Test
    public void equals_WordTree() {
        WordMarkovChainTree wordMarkovChainTree1 = new WordMarkovChainTree();
        wordMarkovChainTree1.addString("Tom");
        WordMarkovChainTree wordMarkovChainTree2 = new WordMarkovChainTree();
        wordMarkovChainTree2.addString("Tom");
        Assert.assertEquals(wordMarkovChainTree1, wordMarkovChainTree2);

        wordMarkovChainTree1.addString("Dylan", "Sandy");
        wordMarkovChainTree2.addString("Sandy", "Dylan");
        Assert.assertEquals(wordMarkovChainTree1, wordMarkovChainTree2);
    }

    //Simple test of name generator
    @Test
    public void generateString_WordTree() {
        WordMarkovChainTree wordMarkovChainTree = new WordMarkovChainTree();
        wordMarkovChainTree.addString("Tom", "Dylan", "Tony", "Dan", "Rick", "Emily", "Stacy", "Sandy",
                "Sana", "Sonny", "Sunny", "Sara", "Sarah");
        for(int i = 0; i < 10; i++) {
            Assert.assertTrue(wordMarkovChainTree.generateString().length() > 1);
        }
    }
}
