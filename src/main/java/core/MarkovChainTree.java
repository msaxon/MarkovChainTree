package core;

import com.google.common.collect.TreeMultiset;
import profile.MarkovProfile;
import profile.ProfileEvaluationResponse;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import static com.google.common.collect.TreeMultiset.create;

/**
 * Created by Matthew on 7/5/2017.
 */
public abstract class MarkovChainTree {
    protected static final String C_START = "*";
    protected static final String C_END = "+";

    private HashMap<String, TreeMultiset<String>> markovTree = new HashMap<>();

    private MarkovProfile profile = new MarkovProfile();

    abstract ProfileEvaluationResponse evaluateAgainstProfile(String current, StringBuilder sb);


    void addString(Delimiter deliminator, String... string) {
        for(String s : string) {
            String current = C_START;
            for(String index : s.toLowerCase().split(deliminator.getDelimiter())) {
                if(!markovTree.containsKey(current)) {
                    markovTree.put(current, TreeMultiset.create(Collections.singleton(index)));
                } else {
                    markovTree.get(current).add(index);
                }
                current = index;
            }
            if (!markovTree.containsKey(current)) {
                markovTree.put(current, create(Arrays.asList(C_END, C_END)));
            } else {
                markovTree.get(current).add(C_END);
                markovTree.get(current).add(C_END);
            }
        }
    }

    public String generateString(Delimiter delimiter) {
        StringBuilder sb = new StringBuilder();
        String current = C_START;
        int skips = 0;
        while(!current.equals(C_END)) {
            String nextChar = getNextString(current);
            ProfileEvaluationResponse per = evaluateAgainstProfile(nextChar, sb);
            switch (per) {
                case KEEP_CURRENT:
                    sb.append(nextChar);
                    sb.append(delimiter.getDelimiter());
                    current = nextChar;
                    break;
                case SKIP_CURRENT:
                    skips++;
                    if(skips > 20) {
                        sb = new StringBuilder();
                        skips = 0;
                        current = C_START;
                    }
                    break;
                case END_HERE:
                    current = C_END;
                    break;
            }
        }
        return sb.toString().substring(0, sb.toString().length());
    }

    MarkovProfile getMarkovProfile() { return profile; }

    public void setMarkovProfile(MarkovProfile profile) { this.profile = profile; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(Map.Entry<String, TreeMultiset<String>> entry : markovTree.entrySet()) {
            sb.append(entry.getKey()).append(" - ");
            for(String s : entry.getValue()) {
                sb.append(s).append(", ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object markovChainTree) {
        return markovChainTree instanceof MarkovChainTree
                && this.markovTree.keySet().equals(((MarkovChainTree) markovChainTree).markovTree.keySet());
    }

    private String getNextString(String c) {
        TreeMultiset<String> treeMultiset = markovTree.get(c);
        if(treeMultiset == null || treeMultiset.size() < 1) throw new IllegalStateException("Must add strings to tree before generating strings.");
        int index = ThreadLocalRandom.current().nextInt(0, treeMultiset.size());
        return (String) treeMultiset.toArray()[index];
    }
}
