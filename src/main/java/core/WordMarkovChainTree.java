package core;

import profile.ProfileEvaluationResponse;

/**
 * Created by Matthew on 7/5/2017.
 */
public class WordMarkovChainTree extends MarkovChainTree {

    public void addString(String... string) {
        super.addString(Delimiter.WORD, string);
    }

    public String generateString() {
        return super.generateString(Delimiter.WORD);
    }

    @Override
    ProfileEvaluationResponse evaluateAgainstProfile(String current, StringBuilder sb) {
        if(current.equals(C_END) && sb.length() < getMarkovProfile().getMinimumCount()) {
            //if the current word is too short, skip the end word char
            return ProfileEvaluationResponse.SKIP_CURRENT;
        } else if(!current.equals(C_END) && sb.length()+1 > getMarkovProfile().getMaximumCount()) {
            //if the current word is too long, change to end char
            return ProfileEvaluationResponse.END_HERE;
        } else {
            //if the current word has too many repeats
            String tempString = sb.toString();
            int currentRepeat = 0;
            if(tempString.length() > 0) {
                while (tempString.substring(tempString.length() - 1).equals(current)) {
                    tempString = tempString.substring(0, tempString.length() - 2);
                    currentRepeat++;
                }
            }
            if(currentRepeat+1 > getMarkovProfile().getMaximumRepeat()) return ProfileEvaluationResponse.SKIP_CURRENT;
        }
        if(current.equals(C_END)) return ProfileEvaluationResponse.END_HERE;
        else return ProfileEvaluationResponse.KEEP_CURRENT;
    }
}
