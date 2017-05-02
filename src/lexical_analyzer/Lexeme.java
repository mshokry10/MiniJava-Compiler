package lexical_analyzer;

public class Lexeme {
    Token token;
    String content;

    Lexeme(Token token, String content) {
        this.token = token;
        this.content = content;
    }

    @Override
    public String toString() {
        return '<' + token.getType() + ", " + content + '>';
    }
}
