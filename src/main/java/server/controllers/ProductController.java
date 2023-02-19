package server.controllers;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import server.generic.GenericController;
import server.model.Product;
import server.repository.ProductsRepository;

@RestController
@RequestMapping("/product")
public class ProductController extends GenericController<Product>{

    public ProductController(ProductsRepository repository) {
        super(repository);
    }
    
    
}
