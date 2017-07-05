package main;

import core.MarkovChainTree;
import core.WordMarkovChainTree;

/**
 * Created by Matthew on 7/5/2017.
 */
public class Main {
    //sample
    public static void main(String[] args) {
        MarkovChainTree markovChainTree = new WordMarkovChainTree();
        markovChainTree.addString("Tom", "Dylan", "Derrick", "Timothy", "Taryn", "Daniel", "Dillion", "Damion",
                "Talon", "Tristan", "Trisha", "Rebecka", "Tibbers");
        System.out.println(markovChainTree);
        for(int i = 0; i < 10; i++) {
            System.out.println(markovChainTree.generateString());
        }
    }

}
