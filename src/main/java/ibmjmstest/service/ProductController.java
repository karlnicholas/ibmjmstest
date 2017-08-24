package ibmjmstest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ibmjmstest.integration.ProductGateway;
import ibmjmstest.model.Product;

@RestController
public class ProductController {

	@Autowired
	private ProductGateway gateway;

	@RequestMapping(path = "/")
	public List<Product> index(@RequestParam String catalog) {
		List<Product> productList = gateway.getCatalog(catalog);
		return productList;
	/*		
	@Autowired
	private ProductRepository repository;
		List<Product> productList = new ArrayList<>();
		productList.addAll((Collection<Product>) repository.findAll());
		return productList;
		// return productList.toArray(new Product[productList.size()]);
	 */		
	}
}
