package core;

import com.google.common.collect.TreeMultiset;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import static com.google.common.collect.TreeMultiset.create;

/**
 * Created by Matthew on 7/5/2017.
 */
public abstract class MarkovChainTree {
    private static final String C_START = "*";
    private static final String C_END = "+";
    final Delimiter SENTENCE = new MarkovChainTree.Delimiter(" ");
    final Delimiter WORDS = new MarkovChainTree.Delimiter("");
    private HashMap<String, TreeMultiset<String>> markovTree = new HashMap<>();

    protected void addString(Delimiter deliminator, String... string) {
        for(String s : string) {
            String current = C_START;
            for(String index : s.toLowerCase().split(deliminator.getDelimiterString())) {
                if(!markovTree.containsKey(current)) {
                    markovTree.put(current, TreeMultiset.create(Collections.singleton(index)));
                } else {
                    markovTree.get(current).add(index);
                }
                current = index;
            }
            if (!markovTree.containsKey(current)) {
                markovTree.put(current, create(Collections.singleton(C_END)));
            } else {
                markovTree.get(current).add(C_END);
            }
        }
    }

    public String generateString() {
        StringBuilder sb = new StringBuilder();
        String current = C_START;
        while(!current.equals(C_END)) {
            String nextChar = getNextChar(current);
            sb.append(nextChar);
            sb.append(" ");
            current = nextChar;
        }
        return sb.toString().substring(0, sb.toString().length());
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(Map.Entry<String, TreeMultiset<String>> entry : markovTree.entrySet()) {
            sb.append(entry.getKey()).append(" - ");
            for(String s : entry.getValue()) {
                sb.append(s).append(", ");
            }
            sb.append(".\n");
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object markovChainTree) {
        return markovChainTree instanceof MarkovChainTree
                && this.markovTree.keySet().equals(((MarkovChainTree) markovChainTree).markovTree.keySet());
    }

    private String getNextChar(String c) {
        TreeMultiset<String> treeMultiset = markovTree.get(c);
        int index = ThreadLocalRandom.current().nextInt(0, treeMultiset.size());
        return (String) treeMultiset.toArray()[index];
    }

    protected class Delimiter {
        String s;

        Delimiter(String string) {
            s = string;
        }

        String getDelimiterString() { return s; }
    }
}
