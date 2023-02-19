package server.repository;

import org.springframework.stereotype.Repository;

import server.generic.GenericRepository;
import server.model.Product;
@Repository
public interface ProductsRepository extends GenericRepository<Product>{ 
}
