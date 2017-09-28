package ibmjmstest.repository;

import org.springframework.data.repository.CrudRepository;

import ibmjmstest.model.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {

	Iterable<Product> findByCategory(String category);

}