package lexical_analyzer;

import java.util.ArrayList;

public class Tokenizer {
    private static ArrayList<Token> tokens;

    public static ArrayList<Lexeme> tokenize(String source) {
        initialize();

        ArrayList<Lexeme> lexemes = new ArrayList<>();


        return lexemes;
    }

    private static boolean isWordEnd(char c) {
        return " \n+-*/&|^~!=<>{}[](),;:?.\'\"\\".indexOf(c) >= 0;
    }

    private static void addToken(String type, String regex) {
        tokens.add(new Token(type, regex));
    }

    private static void initialize() {

    }
}
