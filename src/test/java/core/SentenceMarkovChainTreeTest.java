package core;

import org.junit.Test;
import org.testng.Assert;
import profile.MarkovProfile;

/**
 * Created by Matthew on 7/9/2017.
 */
public class SentenceMarkovChainTreeTest {
    @Test
    public void toString_SentenceTree() {
        SentenceMarkovChainTree sentenceMarkovChainTree = new SentenceMarkovChainTree();
        sentenceMarkovChainTree.addString("Hello world");

        String printedTree = sentenceMarkovChainTree.toString();
        Assert.assertTrue(printedTree.contains("hello - world, "));
        Assert.assertTrue(printedTree.contains("* - hello, "));
        Assert.assertTrue(printedTree.contains("world - +, +, "));
    }

    @Test
    public void equals_SentenceTree() {
        SentenceMarkovChainTree sentenceMarkovChainTree1 = new SentenceMarkovChainTree();
        SentenceMarkovChainTree sentenceMarkovChainTree2 = new SentenceMarkovChainTree();
        sentenceMarkovChainTree1.addString("Hello world");
        sentenceMarkovChainTree2.addString("Hello world");

        Assert.assertEquals(sentenceMarkovChainTree1, sentenceMarkovChainTree2);

        sentenceMarkovChainTree1.addString("Hello and good morning", "Good morning and hello");
        sentenceMarkovChainTree2.addString("Good morning and hello", "Hello and good morning");
        Assert.assertEquals(sentenceMarkovChainTree1, sentenceMarkovChainTree2);
    }

    @Test
    public void generateString_SentenceTree() {
        SentenceMarkovChainTree sentenceMarkovChainTree = new SentenceMarkovChainTree();
        sentenceMarkovChainTree.addString("Hello world", "Good morning and hello",
                "Hello and good morning", "Hello to all my friends", "Hello and goodbye",
                "Hello how are you", "Hello mother how are you");
        for(int i = 0; i < 10; i++) {
            Assert.assertTrue(sentenceMarkovChainTree
                    .generateString()
                    .split(" ").length > 0);
        }
    }

    @Test
    public void generateString_SentenceTree_MaxRepeats() {
        int maxRepeats = 1;
        MarkovProfile profile = new MarkovProfile();

        profile.setMaximumRepeat(maxRepeats);

        SentenceMarkovChainTree sentenceMarkovChainTree = new SentenceMarkovChainTree();
        sentenceMarkovChainTree.addString("Hello hello hello hello");
        sentenceMarkovChainTree.setMarkovProfile(profile);
        for(int i = 0; i < 100; i++) {
            Assert.assertFalse(checkForDuplicateRuns(sentenceMarkovChainTree.generateString(), maxRepeats));
        }
    }

    private boolean checkForDuplicateRuns(String string, int maxRepeats) {
        int duplicates = 1;
        String pastString = "";
        for(String s : string.split(" ")) {
            if(s.equals(pastString)) duplicates++;
            else duplicates = 1;
            if(duplicates > maxRepeats) return true;
        }
        return false;
    }
}
