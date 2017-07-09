package core;

import profile.ProfileEvaluationResponse;

import java.util.Arrays;

/**
 * Created by Matthew on 7/6/2017.
 */
public class SentenceMarkovChainTree extends MarkovChainTree {

    public void addString(String... string) {
        super.addString(SENTENCE, string);
    }

    public String generateString() {
        return super.generateString(SENTENCE);
    }

    @Override
    ProfileEvaluationResponse evaluateAgainstProfile(String current, StringBuilder sb) {
        int numWords = sb.toString().split(" ").length;
        if(current.equals(C_END) && numWords < getMarkovProfile().getMinimumCount()) {
            return ProfileEvaluationResponse.SKIP_CURRENT;
        } else if(!current.equals(C_END) && numWords+1 > getMarkovProfile().getMaximumCount()) {
            return ProfileEvaluationResponse.END_HERE;
        } else {
            //check for repeats
            String[] tempString = sb.toString().split(" ");
            int currentRepeat = 0;
            if (tempString.length > 1) {
                while (tempString[tempString.length - 1].equals(current)) {
                    if(tempString.length > 1) {
                        tempString = Arrays.copyOfRange(tempString, 0, tempString.length - 1);
                        currentRepeat++;
                    } else {
                        currentRepeat++;
                        break;
                    }
                }
            }
            if (currentRepeat + 1 > getMarkovProfile().getMaximumRepeat())
                return ProfileEvaluationResponse.SKIP_CURRENT;
        }
        if(current.equals(C_END)) return ProfileEvaluationResponse.END_HERE;
        else return ProfileEvaluationResponse.KEEP_CURRENT;
    }


}
