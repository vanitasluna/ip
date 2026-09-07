/**
 * Represents an error caused by invalid input to the Vani chatbot.
 */
public class VaniException extends Exception {

    /**
     * Creates a Vani exception with the supplied error message.
     *
     * @param message the message describing the input error
     */
    public VaniException(String message) {
        super(message);
    }
}