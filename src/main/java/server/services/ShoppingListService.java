package server.services;

import org.springframework.stereotype.Service;

import server.generic.GenericService;
import server.model.ShoppingList;
import server.repository.ShoppingListRepository;

@Service
public class ShoppingListService  extends GenericService<ShoppingList>{



    protected ShoppingListService(ShoppingListRepository repository) {
        super(repository);
       
    }
}
