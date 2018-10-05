package calc.exceptions;

/**
 * Exception for math error, for example sqrt(-4)
 */
public class MathError extends Exception{
    public MathError() {
        super();
    }

    public MathError(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        String ans = super.getMessage();
        return (ans != null ? ans : "");
    }
}
