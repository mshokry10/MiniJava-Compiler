package lexical_analyzer;

public class Token {
    private String type;
    private String regex;

    Token(String type, String regex) {
        this.type = type;
        this.regex = regex;
    }

    public String getType() {
        return type;
    }

    public boolean matches(String word) {
        return word.matches(regex);
    }
}
