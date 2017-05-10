package main;

import lexical_analyzer.Lexeme;
import lexical_analyzer.Tokenizer;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        String source_file_name = "source_code.txt"; // a text file that contains mini-java code

        Scanner scanner = null;
        try {
            scanner = new Scanner(new FileReader(source_file_name));
        } catch (FileNotFoundException e) {
            System.out.println("File \'" + source_file_name + "\' not found.");
        }

        String source_code = new String();
        while (scanner.hasNext()) {
            source_code += scanner.nextLine() + '\n';
        }

        PrintWriter printWriter = null;
        try {
            printWriter = new PrintWriter("tokens.txt");
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }

        ArrayList<Lexeme> lexemes = Tokenizer.tokenize(source_code);

        printWriter.println(Arrays.deepToString(lexemes.toArray()));

        printWriter.close();
        scanner.close();
    }

}
