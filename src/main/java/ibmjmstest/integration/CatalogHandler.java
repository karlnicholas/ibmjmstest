package ibmjmstest.integration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Service;

import ibmjmstest.model.Product;
import ibmjmstest.repository.ProductRepository;

@Service
public class CatalogHandler {
	@Autowired
	private ProductRepository repository;

	@ServiceActivator(inputChannel="catalogChannel")
	public List<Product> getCatalog(String category) {
		List<Product> productList = new ArrayList<>();
		if ( category.isEmpty() ) {
			productList.addAll((Collection<Product>) repository.findAll());
		} else {
			productList.addAll((Collection<Product>) repository.findByCategory(category));
		}
		return productList;
	}

}
