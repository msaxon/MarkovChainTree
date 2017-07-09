package core;

/**
 * Created by Matthew on 7/9/2017.
 */
public class MarkovProfile {
    private int minimumCount = -1;
    private int maximumCount = Integer.MAX_VALUE;
    private int maximumRepeat = Integer.MAX_VALUE;


    public int getMinimumCount() {
        return minimumCount;
    }

    public void setMinimumCount(int minimumCount) {
        this.minimumCount = minimumCount;
    }

    public int getMaximumCount() {
        return maximumCount;
    }

    public void setMaximumCount(int maximumCount) {
        this.maximumCount = maximumCount;
    }

    public int getMaximumRepeat() {
        return maximumRepeat;
    }

    public void setMaximumRepeat(int maximumRepeat) {
        this.maximumRepeat = maximumRepeat;
    }
}
