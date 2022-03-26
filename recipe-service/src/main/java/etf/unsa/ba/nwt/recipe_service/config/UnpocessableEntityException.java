package etf.unsa.ba.nwt.recipe_service.config;

import org.springframework.http.HttpStatus;

public class UnpocessableEntityException extends BaseException {
    public UnpocessableEntityException(final String message) {
        super(message, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
