package test1;

public class Quitter extends Throwable {
    public Quitter(String message) {
        super(message);
    }
}
