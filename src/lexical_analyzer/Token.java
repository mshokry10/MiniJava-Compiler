package lexical_analyzer;

import java.util.regex.Pattern;

public class Token {
    private String type;
    private Pattern pattern;

    Token(String type, String regex) {
        this.type = type;
        this.pattern = Pattern.compile(regex);
    }

    public String getType() {
        return type;
    }

    public Pattern getPattern() {
        return pattern;
    }

}
