package ibmjmstest.service;

/**
 * Comments
 */
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ibmjmstest.integration.CatalogGateway;
import ibmjmstest.model.Product;

@RestController
public class CatalogController {

	@Autowired
	private CatalogGateway gateway;

	@RequestMapping(path = "/")
	public List<Product> index(@RequestParam(required=false) String category) {
		if ( category == null ) category = new String();
		List<Product> productList = gateway.getCatalog(category);
		return productList;
	}
}
