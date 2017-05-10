package lexical_analyzer;

public class Lexeme {
    String token_type;
    String content;

    Lexeme(String type, String content) {
        this.token_type = type;
        this.content = content;
    }

    @Override
    public String toString() {
        return '<' + token_type + ", " + content + '>';
    }
}
