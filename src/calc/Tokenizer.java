package calc;

import calc.exceptions.SyntaxError;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

/**
 * Class to work with original string, parse it into tokens and prepare them for further operations
 */
public class Tokenizer {

    /**
     * Makes an array of tokens in reversed polish notation by String
     * @param s - given string
     * @return - tokens in RPN
     * @throws SyntaxError if there is a syntax error
     */
    public static ArrayList<Token> parseToRPN(String s) throws SyntaxError {
        checkSyntax(s);

        Scanner scanner = new Scanner(addSpaces(handleUnaryMinuses(s)));

        ArrayList<Token> ans = new ArrayList<Token>();
        Stack<Token> operStack = new Stack<Token>();

        while (scanner.hasNext()){

            Token cur = new Token(scanner.next());
            if (cur.getType() == Token.TokenType.Number){
                ans.add(cur);
            } else {
                if (cur.getStr().equals("(")){
                    operStack.push(cur);
                } else if (cur.getStr().equals(")")){
                    Token tmp = operStack.pop();
                    while (!tmp.getStr().equals("(")){
                        ans.add(tmp);
                        tmp = operStack.pop();
                    }
                } else {
                    while (!operStack.empty() && (cur.getPriority() <= operStack.peek().getPriority())){
                        ans.add(operStack.pop());
                    }
                    operStack.push(cur);
                }
            }
        }
        while (!operStack.empty()){
            ans.add(operStack.pop());
        }

        return ans;
    }

    /**
     * Make unary minuses recognizable for evaluations
     * @param s - given string
     * @return - string with "good" minuses
     */
    private static String handleUnaryMinuses(String s) {
        if (s.length() < 2) return s;
        StringBuilder string = new StringBuilder(s);
        for (int i = 0; i < string.length(); ++i) {
            if (string.charAt(i) == '-') {
                if (string.charAt(i + 1) == '(' || Character.isDigit(string.charAt(i + 1))){
                    int index = findPrevNonSpaceCharIndex(string, i);
                    if (index == -1 || string.charAt(index) == '(') {
                        string.setCharAt(i, '±');
                    }
                } else if (Character.isLetter(string.charAt(i + 1))) {
                    int index = findPrevNonSpaceCharIndex(string, i);
                    if (index == -1 || string.charAt(index) == '(') {
                        string.insert(i + 1, "(±1)*");
                        string.deleteCharAt(i);
                    }
                }

            }
        }
        return string.toString();
    }

    /**
     * Adds spaces between elements to make the string easy readable by Scanner
     * @param s - given string
     * @return - prepared string
     */
    private static String addSpaces(String s){
        StringBuilder sb = new StringBuilder();
        boolean afterFunc = false;
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (Definitions.isOperator(c)) {
                sb.append(' ').append(c).append(' ');
            } else if (Character.isLetter(c)) {
                if (!afterFunc){
                    sb.append(' ');
                }
                afterFunc = true;
                sb.append(c);
            } else if (afterFunc) {
                afterFunc = false;
                sb.append(' ').append(c);
            } else {
                sb.append(c);
            }

        }
        sb.append(' ');
        return sb.toString();
    }

    private static int findPrevNonSpaceCharIndex(StringBuilder sb, int index){
        while (--index >= 0){
            if (sb.charAt(index) != ' ') return index;
        }
        return index;
    }

    /**
     * Checks syntax
     * If SyntaxError is not thrown, it does not mean the string is fully correct, this method recognizes only some of errors
     * In future the string will be checked with other methods too
     * @param s - string to check
     * @throws SyntaxError if there is a syntax error
     */
    private static void checkSyntax(String s) throws SyntaxError {
        int count = 0;
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (c != ' ' && !Character.isLetter(c) && !Definitions.isOperator(c) && !Character.isDigit(c) && c != '.'){
                throw new SyntaxError("Incorrect characters");
            }
            if (c == '('){
                count++;
            } else if (c == ')'){
                count--;
            }
        }
        if (count != 0){
            throw new SyntaxError("Incorrect brackets usage");
        }
    }
}
