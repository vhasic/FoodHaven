package ba.etf.unsa.nwt.rating_service.messaging;

import ba.etf.unsa.nwt.rating_service.model.RecipeDTO;

public interface Publisher<T extends RecipeDTO> {
    void send(T data);
}
