package etf.unsa.ba.nwt.recipe_service.config;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.aspectj.bridge.Message;
import org.springframework.http.HttpStatus;

@EqualsAndHashCode(callSuper = false)
@Data
public abstract class BaseException extends RuntimeException {
    private final HttpStatus httpStatus;
    private final String message;

    public BaseException(final String message, final HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
