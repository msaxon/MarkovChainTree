package core;

import com.google.common.collect.TreeMultiset;

import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Matthew on 7/5/2017.
 */
public class WordMarkovChainTree extends MarkovChainTree {

    @Override
    public void addString(String... string) {
        for(String s : string) {
            Character current = _START;
            for (Character index : s.toLowerCase().toCharArray()) {
                if (!markovTree.containsKey(current)) {
                    markovTree.put(current, TreeMultiset.create(Collections.singleton(index)));
                } else {
                    markovTree.get(current).add(index);
                }
                current = index;
            }
            if (!markovTree.containsKey(current)) {
                markovTree.put(current, TreeMultiset.create(Collections.singleton(_END)));
            } else {
                markovTree.get(current).add(_END);
            }
        }
    }

    @Override
    public String generateString() {
        StringBuilder sb = new StringBuilder();
        Character current = _START;
        while(current != _END) {
            Character nextChar = getNextChar(current);
            sb.append(nextChar);
            current = nextChar;
        }
        return sb.toString().substring(0, sb.toString().length()-1);
    }

    private Character getNextChar(Character c) {
        TreeMultiset<Character> treeMultiset = markovTree.get(c);
        int index = ThreadLocalRandom.current().nextInt(0, treeMultiset.size()-1);
        return (Character) treeMultiset.toArray()[index];
    }
}
