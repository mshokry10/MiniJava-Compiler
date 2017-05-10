package lexical_analyzer;

import java.util.ArrayList;
import java.util.regex.Matcher;

public class Tokenizer {
    // tokens in the array list are ordered by precedence
    private static ArrayList<Token> tokens;

    public static ArrayList<Lexeme> tokenize(String source) {
        if (tokens.isEmpty()) {
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
                    lexemes.add(new Lexeme("UNMATCHED", unmatched));
                    unmatched = new String();
                } else {
                    unmatched += source.charAt(cur_pos);
                }
                ++cur_pos;
            }
        }

        return lexemes;
    }

//    private static boolean isWordEnd(char c) {
//        return " \n+-*/%&|^~!=<>{}[](),;:?.\'\"\\".indexOf(c) >= 0;
//    }

    private static void addToken(String type, String regex) {
        tokens.add(new Token(type, regex));
    }

    private static void initialize() {

    }
}
