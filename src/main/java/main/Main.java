package main;

import core.SentenceMarkovChainTree;
import core.WordMarkovChainTree;

/**
 * Created by Matthew on 7/5/2017.
 */
public class Main {
    //sample
    public static void main(String[] args) {
        WordMarkovChainTree markovChainTree = new WordMarkovChainTree();
        markovChainTree.addString("Tom");
        System.out.println(markovChainTree);


        SentenceMarkovChainTree secondMarkovChainTree = new SentenceMarkovChainTree();
        secondMarkovChainTree.addString("How my daughter is.",
                "How are you doing today.",
                "How is my favorite daughter.",
                "How can I be of service.",
                "How now brown cow doing.",
                "How is your customer service.",
                "How can it be.");
        System.out.println(secondMarkovChainTree);
        for(int i = 0; i < 10; i++) {
            //System.out.println(secondMarkovChainTree.generateString());
        }
    }

}
