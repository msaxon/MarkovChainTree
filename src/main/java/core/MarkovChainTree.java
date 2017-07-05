package core;

import com.google.common.collect.TreeMultiset;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Matthew on 7/5/2017.
 */
public abstract class MarkovChainTree {
    protected static final Character _START = '*';
    protected static final Character _END = '+';
    protected HashMap<Character, TreeMultiset<Character>> markovTree = new HashMap<>();

    public abstract void addString(String... string);
    public abstract String generateString();

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(Map.Entry<Character, TreeMultiset<Character>> entry : markovTree.entrySet()) {
            sb.append(entry.getKey()).append(" - ");
            for(Character character : entry.getValue()) {
                sb.append(character).append(", ");
            }
            sb.append(".\n");
        }
        return sb.toString();
    }
}
