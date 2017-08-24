package ibmjmstest.integration;

import java.util.List;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

import ibmjmstest.model.Product;

@MessagingGateway
public interface ProductGateway {
	@Gateway(requestChannel = "productChannel")
	List<Product> getCatalog(String catagory);
}