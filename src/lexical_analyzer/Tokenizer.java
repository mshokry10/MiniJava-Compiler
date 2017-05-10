package lexical_analyzer;

import java.util.ArrayList;
import java.util.regex.Matcher;

public class Tokenizer {
    // tokens in the array list are ordered by precedence
    private static ArrayList<Token> tokens;

    public static ArrayList<Lexeme> tokenize(String source) {
        if (tokens == null) {
            initialize();
        }

        ArrayList<Lexeme> lexemes = new ArrayList<>();

        int cur_pos = 0;
        String unmatched = new String();

        while (cur_pos < source.length()) {
            boolean found_match = false;
            for (Token token : tokens) {
                Matcher matcher = token.getPattern().matcher(source);
                matcher.region(cur_pos, source.length());
                if (matcher.lookingAt() && !matcher.group().isEmpty()) {
                    found_match = true;
                    lexemes.add(new Lexeme(token.getType(), matcher.group()));
                    cur_pos = matcher.end();
                    if (!unmatched.isEmpty()) {
                        lexemes.add(new Lexeme("UNMATCHED", unmatched));
                        unmatched = new String();
                    }
                    break;
                }
            }

            if (!found_match) {
                if (Character.isWhitespace(source.charAt(cur_pos))) {
                    if (!unmatched.isEmpty()) {
                        lexemes.add(new Lexeme("UNMATCHED", unmatched));
                        unmatched = new String();
                    }
                } else {
                    unmatched += source.charAt(cur_pos);
                }
                ++cur_pos;
            }
        }

        return lexemes;
    }

    private static void addToken(String type, String regex) {
        tokens.add(new Token(type, regex));
    }

    private static void initialize() {
        // ordered by precedence

        tokens = new ArrayList<>();

        addToken("LINE_COMMENT", "\\/\\/.*");
        addToken("MULTILINE_COMMENT", "\\/\\*((?!\\/\\*)[\\w\\W])*\\*\\/");

        // In case of unmatched comment key characters.
        addToken("COMMENT_L", "\\/\\*");
        addToken("COMMENT_R", "\\*\\/");

        // Arithmetic operators.
        addToken("PLUS", "\\+");
        addToken("MINUS", "-");
        addToken("MULTIPLY", "\\*");
        addToken("DIVIDE", "\\/");

        addToken("EQUAL", "=");

        addToken("LESS_THAN", "<");
        addToken("GREATER_THAN", "<");

        addToken("IF", "\\bif\\b");
        addToken("ELSE", "\\belse\\b");
        addToken("WHILE", "\\bwhile\\b");

        addToken("RETURN", "\\breturn\\b");

        addToken("THIS", "\\bthis\\b");

        addToken("CLASS", "\\bclass\\b");

        addToken("PUBLIC", "\\bpublic\\b");
        addToken("PRIVATE", "\\bprivate\\b");

        addToken("LENGTH", "\\blength\\b");

        addToken("EXTENDS", "\\bextends\\b");

        addToken("SYSTEM.OUT.PRINTLN", "\\bSystem\\.out\\.println\\b");

        addToken("STATIC", "\\bstatic\\b");

        addToken("NEW", "\\bnew\\b");

        addToken("FLOAT", "\\bfloat\\b");
        addToken("INT", "\\bint\\b");
        addToken("CHARACTER", "\\bchar\\b");
        addToken("BOOLEAN", "\\bboolean\\b");
        addToken("String", "\\bString\\b");
        addToken("VOID", "\\bvoid\\b");

        addToken("TRUE", "\\btrue\\b");
        addToken("FALSE", "\\bfalse\\b");

        // Brackets.
        addToken("LEFT_CURLY_BRACKET", "\\{");
        addToken("RIGHT_CURLY_BRACKET", "\\}");
        addToken("LEFT_SQUARE_BRACKET", "\\[");
        addToken("RIGHT_SQUARE_BRACKET", "\\]");
        addToken("LEFT_ROUND_BRACKET", "\\(");
        addToken("RIGHT_ROUND_BRACKET", "\\)");

        addToken("COMMA", ",");
        addToken("SEMICOLON", ";");
        addToken("DOT", "\\.");
        addToken("NOT", "\\!");

        addToken("AND", "&&");

        // Literals.
        addToken("FLOAT_LITERAL", "\\b[0-9]*\\.[0-9]*\\b");
        addToken("INTEGRAL_LITERAL", "\\b[0-9]+\\b");
        addToken("CHARACTER_LITERAL", "'.'");
        addToken("STRING_LITERAL", "\".*\"");

        addToken("Identifier", "\\b[a-zA-Z]+\\w*\\b");

        addToken("SINGLE_QUOTE", "'");
        addToken("DOUBLE_QUOTE", "\"");
    }
}
