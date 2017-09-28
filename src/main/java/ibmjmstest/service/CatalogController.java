package ibmjmstest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ibmjmstest.model.Product;
import ibmjmstest.repository.ProductRepository;
import ibmjmstest.types.ProductListType;

@RestController
public class CatalogController {

    @Autowired
    private ProductRepository repository;

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
