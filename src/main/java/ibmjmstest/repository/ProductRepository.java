package ibmjmstest.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ibmjmstest.model.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {

	List<Product> findByCategory(String category);

}