import java.nio.file.NoSuchFileException;

public class NoSuchPizzaException extends Exception {
    public NoSuchPizzaException(String msg) {
        super(msg);
    }
}