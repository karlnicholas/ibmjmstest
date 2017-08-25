package ibmjmstest.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Service;

import ibmjmstest.model.Product;
import ibmjmstest.repository.ProductRepository;

@Service
public class ProductHandler {
	@Autowired
	private ProductRepository repository;

	@ServiceActivator(inputChannel="productChannel")
	public Product getProduct(Long id) {
		System.out.println( "getProduct: " + Thread.currentThread().toString() );
		Product product = repository.findOne(id);
		return product;
	}
}
