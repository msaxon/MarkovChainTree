package core;

import org.junit.Test;
import org.testng.Assert;
import profile.MarkovProfile;
import sun.plugin.dom.exception.InvalidStateException;

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

    @Test
    public void generateString_WordTree_MaxRepeats() {
        int maxRepeats = 1;
        MarkovProfile profile = new MarkovProfile();

        profile.setMaximumRepeat(maxRepeats);

        WordMarkovChainTree wordMarkovChainTree = new WordMarkovChainTree();
        wordMarkovChainTree.addString("Donny", "Danny", "Tonny", "Rinny", "Sinnty");
        wordMarkovChainTree.setMarkovProfile(profile);
        for(int i = 0; i < 100; i++) {
            Assert.assertFalse(checkForDuplicateRuns(wordMarkovChainTree.generateString(), maxRepeats));
        }
    }

    @Test
    public void generateString_WordTree_MinMaxLength() {
        int minLength = 5;
        int maxLength = 3;

        WordMarkovChainTree wordMarkovChainTree = new WordMarkovChainTree();
        wordMarkovChainTree.addString("Donny", "Danny", "Tonny", "Rinny", "Sinnty");

        MarkovProfile profile = new MarkovProfile();
        profile.setMinimumCount(minLength);
        wordMarkovChainTree.setMarkovProfile(profile);

        for(int i = 0; i < 100; i++) {
            String t = wordMarkovChainTree.generateString();
            Assert.assertTrue(t.length() >= minLength);
        }

        profile = new MarkovProfile();
        profile.setMaximumCount(maxLength);
        wordMarkovChainTree.setMarkovProfile(profile);
        for(int i = 0; i < 100; i++) {
            String t = wordMarkovChainTree.generateString();
            Assert.assertTrue(t.length() <= maxLength);
        }
    }

    @Test
    public void generateString_WordTree_NoStringsAddedShouldThrowException() {
        WordMarkovChainTree wordMarkovChainTree = new WordMarkovChainTree();
        try {
            wordMarkovChainTree.generateString();
            Assert.fail("Should have thrown an exception generating string without collection");
        } catch (IllegalStateException e) {
            Assert.assertEquals(e.getMessage(), "Must add strings to tree before generating strings.");
        }
    }


    private boolean checkForDuplicateRuns(String string, int maxRepeats) {
        int duplicates = 1;
        String pastString = "";
        for(String s : string.split("")) {
            if(s.equals(pastString)) duplicates++;
            else duplicates = 1;
            if(duplicates > maxRepeats) return true;
        }
        return false;
    }
}
