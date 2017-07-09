package core;

/**
 * Created by Matthew on 7/6/2017.
 */
public class SentenceMarkovChainTree extends MarkovChainTree {

    public void addString(String... string) {
        super.addString(SENTENCE, string);
    }




}
