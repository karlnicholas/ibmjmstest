package ibmjmstest.service;

import ibmjmstest.types.ProductListType;

public interface ProductService {

	ProductListType index(String category);

}