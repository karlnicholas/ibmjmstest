package ibmjmstest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ibmjmstest.model.Product;
import ibmjmstest.repository.ProductRepository;
import ibmjmstest.types.ProductListType;

@RestController
public class ProductServiceImpl implements ProductService {

	@Autowired
    private ProductRepository repository;

    public void setRepository(ProductRepository repository) {
		this.repository = repository;
	}

    /* (non-Javadoc)
	 * @see ibmjmstest.service.ProductService#index(java.lang.String)
	 */
    @Override
	@RequestMapping(path = "/productservice")
    public ProductListType index(@RequestParam(required = false) String category) {
        ProductListType  plt = new ProductListType();
        Iterable<Product> pl;
        if (category == null || category.isEmpty()) {
            pl = repository.findAll();
        } else {
            pl = repository.findByCategory(category);
        }
        for ( Product p: pl ) {
            plt.getProductType().add(p.asProductType());
        }
        return plt;
    }
}
