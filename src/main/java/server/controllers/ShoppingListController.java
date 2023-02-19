package server.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import server.generic.GenericController;
import server.generic.GenericRepository;
import server.model.ShoppingList;
import server.services.ShoppingListService;

@RestController
@RequestMapping("/shoppinglist")
public class ShoppingListController extends GenericController<ShoppingList> {
    
   
    private final ShoppingListService listService;

    protected ShoppingListController(GenericRepository<ShoppingList> repository, ShoppingListService listService) {
        super(repository);
         this.listService = listService; 
    }
    
    @GetMapping("/allForUser")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<List<ShoppingList>> allForUser(@RequestParam String userId) {
        List<ShoppingList> lists = new ArrayList<>(); 
        lists.addAll(listService.getAll());
        return ResponseEntity.ok(lists);
    }
}
