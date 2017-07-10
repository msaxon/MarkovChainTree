package core;

/**
 * Created by Matthew on 7/10/2017.
 */
public enum Delimiter {
    WORD(""),
    SENTENCE(" ");

    private String delimiter;

    Delimiter(String d) {
        delimiter = d;
    }

    public String getDelimiter() {
        return delimiter;
    }
}
