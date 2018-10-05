package calc;

import calc.exceptions.SyntaxError;

/**
 * Class to represent a token from the expression
 */
public class Token {

    public enum TokenType {Operator, Number, Function}

    private TokenType type;
    private String str;

    private double val; // for number
    private int opPriority; // for operator

    /**
     * Create token by its content
     * @param s - string value
     * @throws SyntaxError
     */
    public Token(String s) throws SyntaxError {
        str = s;
        try {
            val = Double.parseDouble(str);
            type = TokenType.Number;
        } catch (NumberFormatException nfe) {
            if (Definitions.isFunction(str)) {
                type = TokenType.Function;
            } else if (str.length() != 1 || !Definitions.isOperator(str.charAt(0))) {
                throw new SyntaxError("Incorrect token");
            } else {
                // is operator
                type = TokenType.Operator;
                opPriority = Definitions.getOpPriority(str.charAt(0));
            }
        }
    }

    public TokenType getType() {
        return type;
    }

    public String getStr() {
        return str;
    }

    public double getVal() {
        return val;
    }

    public int getPriority() {
        if (type == TokenType.Function) {
            return Definitions.FUNCTION_PRIORITY;
        } else {
            return opPriority;
        }
    }
}
