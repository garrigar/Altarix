package calc;

import calc.exceptions.MathError;
import calc.exceptions.SyntaxError;

import java.io.IOException;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Scanner;
import java.util.Stack;

/**
 * Main calculator class
 */
public class Calculator {

    /** main method */
    public static void main() {
        System.out.println(Definitions.HELLO);

        Scanner input = new Scanner(System.in);

        Memory memory = null;
        boolean MEMORY_OK = true;
        try {
            memory = new Memory(Definitions.MEMORY_FILE);
        }
        catch (IOException ioe){
            System.out.println("Memory error, continue without it");
            MEMORY_OK = false;
        }

        int i = 1;
        boolean wantContinue = true;
        String cur;
        while (wantContinue){
            System.out.println("@" + (i++));
            cur = input.nextLine();
            if (cur.length() > 0){
                if (cur.charAt(0) == '#'){
                    String cmd = cur.substring(1);
                    if (cmd.isEmpty()){
                        System.out.println("Wrong command!");
                    } else{
                        cmd = cmd.trim();
                        try{
                            int offset = Integer.parseInt(cmd);
                            if (MEMORY_OK){
                                try {
                                    System.out.println(memory.getItemOffFromLast(offset));
                                } catch (IndexOutOfBoundsException e) {
                                    System.out.println("Index too large, there ain't so much records");
                                }
                            } else
                                System.out.println("Memory disabled!");
                        } catch (NumberFormatException nfe){
                            if (cmd.equals("clear")){
                                if (MEMORY_OK){
                                    try {
                                        memory.clear();
                                    } catch (IOException e) {
                                        System.out.println("Failed to clear memory");
                                    }
                                } else
                                    System.out.println("Memory disabled!");
                            } else if (cmd.equals("exit")){
                                wantContinue = false;
                                if (MEMORY_OK)
                                    memory.close();
                            } else{
                                System.out.println("Wrong command!");
                            }
                        }
                    }
                } else {
                    try {
                        double result = calculate(cur);
                        System.out.println("= " + result);
                        if (MEMORY_OK) {
                            try {
                                memory.record(cur.trim().replaceAll("\\s+", " ") + " = " + result);
                            } catch (IOException e) {
                                System.out.println("Failed to record to memory");
                            }
                        }
                    } catch (SyntaxError se){
                        System.out.println("Syntax error! " + se.getMessage());
                    } catch (MathError me){
                        System.out.println("Math error! " + me.getMessage());
                    }
                }
            }

        }

    }

    /**
     * Evaluates an expression given in String
     * Can be called from outside
     * @param s - expression
     * @return - answer
     * @throws SyntaxError if there is a syntax error
     * @throws MathError if there is a math error
     */
    public static double calculate(String s) throws SyntaxError, MathError {
        return calcByRPN(Tokenizer.parseToRPN(s));
    }

    /**
     * Evaluates an expression given in reversed polish notation
     * @param a - tokens in RPN
     * @return - answer
     * @throws SyntaxError if there is a syntax error
     * @throws MathError if there is a math error
     */
    private static double calcByRPN(ArrayList<Token> a) throws SyntaxError, MathError {

        double result = 0;
        Stack<Double> stack = new Stack<Double>();

        for (Token curToken : a) {
            if (curToken.getType() == Token.TokenType.Number) {
                stack.push(curToken.getVal());
            } else
                try {
                    if (curToken.getType() == Token.TokenType.Operator) {
                        if (curToken.getStr().equals("±")) {
                            double x = stack.pop();
                            stack.push(x * (-1));
                        } else {
                            double y = stack.pop();
                            double x = stack.pop();

                            switch (curToken.getStr().charAt(0)) {
                                case '+':
                                    result = x + y;
                                    break;
                                case '-':
                                    result = x - y;
                                    break;
                                case '*':
                                    result = x * y;
                                    break;
                                case '/':
                                    if (y == 0) {
                                        throw new MathError("Division by 0");
                                    }
                                    result = x / y;
                                    break;
                                case '^':
                                    result = Math.pow(x, y);
                                    if (Double.isNaN(result)) {
                                        throw new MathError();
                                    }
                                    break;
                            }
                            stack.push(result);
                        }
                    } else { // function
                        double x = stack.pop();

                        if (curToken.getStr().equals("abs")) {
                            result = Math.abs(x);
                        } else if (curToken.getStr().equals("sqrt")) {
                            result = Math.sqrt(x);
                        } else if (curToken.getStr().equals("exp")) {
                            result = Math.exp(x);
                        } else if (curToken.getStr().equals("log")) {
                            result = Math.log(x);

                        } else if (curToken.getStr().equals("sin")) {
                            result = Math.sin(x);
                        } else if (curToken.getStr().equals("cos")) {
                            result = Math.cos(x);
                        } else if (curToken.getStr().equals("tan")) {
                            result = Math.tan(x);

                        } else if (curToken.getStr().equals("asin")) {
                            result = Math.asin(x);
                        } else if (curToken.getStr().equals("acos")) {
                            result = Math.acos(x);
                        } else if (curToken.getStr().equals("atan")) {
                            result = Math.atan(x);
                        }

                        if (Double.isNaN(result)) {
                            throw new MathError();
                        }

                        stack.push(result);

                    }
                } catch (EmptyStackException ese) {
                    throw new SyntaxError();
                }
        }
        // must have exactly 1 item
        double ans;
        try {
            ans = stack.pop();
        } catch (EmptyStackException ese) {
            throw new SyntaxError();
        }
        if (!stack.empty()){
            throw new SyntaxError();
        }

        return ans;

    }

}
