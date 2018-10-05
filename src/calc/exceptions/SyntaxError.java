package calc.exceptions;

/**
 * Exception for syntax error
 */
public class SyntaxError extends Exception {
    public SyntaxError() {
        super();
    }

    public SyntaxError(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        String ans = super.getMessage();
        return (ans != null ? ans : "");
    }
}
