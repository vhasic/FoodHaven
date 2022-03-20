package ba.etf.unsa.nwt.user_service.config;

public class InternalError extends Throwable {
    private String message;

    public InternalError(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
