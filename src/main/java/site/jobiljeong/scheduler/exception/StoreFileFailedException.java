package site.jobiljeong.scheduler.exception;

public class StoreFileFailedException extends RuntimeException {
    public StoreFileFailedException() {
        super();
    }

    public StoreFileFailedException(String message) {
        super(message);
    }
}
