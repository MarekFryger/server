package server.repository;

import org.springframework.stereotype.Repository;

import server.generic.GenericRepository;
import server.model.ShoppingList;

@Repository
public interface ShoppingListRepository extends GenericRepository<ShoppingList>{ 
}
