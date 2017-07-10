package main;

import core.SentenceMarkovChainTree;
import core.WordMarkovChainTree;
import profile.MarkovProfile;

/**
 * Created by Matthew on 7/5/2017.
 */
public class Main {
    public static void main(String[] args) {
        /*
            A word chain takes a series of words or names and creates a chain based off
            the probabilities of any one character following another.
            From that, you can generate a random word from that collection of words.
         */
        WordMarkovChainTree markovChainTree = new WordMarkovChainTree();
        markovChainTree.addString("Tom");

        //has a toString method to see smaller trees printed
        System.out.println(markovChainTree);

        //generate a string with the generateString method
        String generatedString = markovChainTree.generateString();
        System.out.println(generatedString);
        /*
            profiles:
                you can add a profile to set minimum, and maximum string sizes, as well as maximum repeats
                these values default to -1, MaxInt, and MaxInt respectively
         */

        MarkovProfile profile = new MarkovProfile();
        profile.setMaximumCount(7);
        profile.setMinimumCount(3);
        profile.setMaximumRepeat(2);
        String profiledString = markovChainTree.generateString();
        System.out.println(profiledString);

        /*
            A sentence chain takes a series of sentences and creates a chain based off
            the probabilities of any one word following another.
            From that, you can generate a random sentence from that collection of sentences.
            It has all the same features that word chains have
         */

        SentenceMarkovChainTree secondMarkovChainTree = new SentenceMarkovChainTree();
        secondMarkovChainTree.addString("How my daughter is.",
                "How are you doing today.",
                "How is my favorite daughter.",
                "How can I be of service.",
                "How now brown cow doing.",
                "How is your customer service.",
                "How can it be.");
        System.out.println(secondMarkovChainTree);
        System.out.println(secondMarkovChainTree.generateString());

    }

}
