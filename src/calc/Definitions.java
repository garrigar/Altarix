package calc;

import java.util.Arrays;

/**
 * Class, contains needed definitions
 */
public class Definitions {
    public static final String HELLO = "CONSOLE CALCULATOR\n" +
            "This is a console calculator. Write an expression, press Enter and it will be evaluated.\n" +
            "Supported operations: + - * / ^ ()\n" +
            "Supported functions: abs, sqrt, exp, log, sin, cos, tan, asin, acos, atan\n" +
            "Function compositions must use braces, for example: sin(cos(0.5))\n" +
            "Use decimal point only (not decimal comma)\n\n" +
            "This calculator has a memory, memory file location: " + Definitions.MEMORY_FILE + "\n" +
            "You can access previous calculations at any time. For that, type \"# n\", where n is an offset from your " +
            "last calculation.\n" +
            "For example, \"# 0\" means you wanna see your last calculation, \"# 1\" - the one before the last, " +
            "\"# 2\" - the second before the last and so on.\n" +
            "You can clear all memory by typing \"# clear\"\n" +
            "To exit, type \"# exit\"";

    public static final String MEMORY_FILE = "CalculatorMemoryFile.txt"; // in root directory of project

    public static final String[] FUNCTIONS = {"abs", "sqrt", "exp", "log", "sin", "cos", "tan", "asin", "acos", "atan"};
    public static final String OPERATORS = "+-*/^()±";

    public static boolean isOperator(char c) {
        return (OPERATORS.indexOf(c) != -1);
    }

    public static boolean isFunction(String f){
        return Arrays.asList(FUNCTIONS).contains(f);
    }

    public static int getOpPriority(char op){
        switch (op)
        {
            case '(': return 0;
            case ')': return 1;
            case '+': return 2;
            case '-': return 2;
            case '*': return 3;
            case '/': return 3;
            case '±': return 4; // unary minus
            case '^': return 5;
            default: return 6;
        }
    }

    public static final int FUNCTION_PRIORITY = 4;

}
