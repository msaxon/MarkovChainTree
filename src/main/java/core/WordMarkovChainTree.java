package core;

/**
 * Created by Matthew on 7/5/2017.
 */
public class WordMarkovChainTree extends MarkovChainTree {

    public void addString(String... string) {
        super.addString(WORDS, string);
    }
}
