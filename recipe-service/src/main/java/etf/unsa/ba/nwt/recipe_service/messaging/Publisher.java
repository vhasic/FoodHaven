package etf.unsa.ba.nwt.recipe_service.messaging;

import etf.unsa.ba.nwt.recipe_service.model.RecipeDTO;

public interface Publisher<T extends RecipeDTO> {
    void send(T data);
}
