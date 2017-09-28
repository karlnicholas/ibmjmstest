package ibmjmstest.test;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import ibmjmstest.IbmjmstestApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = IbmjmstestApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IntegrationTest {

	@LocalServerPort
	private int port;

	TestRestTemplate restTemplate = new TestRestTemplate();

	HttpHeaders headers = new HttpHeaders();

	@Test
	public void testRetrieveStudentCourse() throws JSONException {

		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/productservice"),
				HttpMethod.GET, entity, String.class);

		String expected = "{\"productType\":[{\"id\":1,\"sku\":\"111-AA\",\"name\":\"Widget\",\"description\":\"Cool Widget\",\"category\":\"Widget\"},{\"id\":2,\"sku\":\"112-AB\",\"name\":\"Widget-2\",\"description\":\"Really Cool Widget\",\"category\":\"Widget\"},{\"id\":3,\"sku\":\"000-OO\",\"name\":\"Spam\",\"description\":\"Classic Canned Meat Gluten Free. Fully cooked, ready to eat--cold or hot.\",\"category\":\"Food\"},{\"id\":4,\"sku\":\"123-SF\",\"name\":\"Apple iPad Air 2\",\"description\":\"Apple iPad Air 2 tablet, featuring a thin Retina display and antireflective coating.\",\"category\":\"Electronics\"}]}";

		JSONAssert.assertEquals(expected, response.getBody(), false);
	}

	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}
}
/*
	@Test
    public void test() throws Exception {       
//        get("/schema/getcatalog").then().statusCode( 200 );        
        get("/productservice").then().statusCode( 200 );        
        get("/productservice/1").then().statusCode( 200 );        
        get("/getpurchaseorderlist").then().statusCode( 200 );
        get("/getpurchaseorder/1").then().statusCode( 200 );
        // do some testing.
        // do an insert
        PurchaseOrderType purchaseOrderType = when().get("/getpurchaseorder/1").as(PurchaseOrderType.class);
        purchaseOrderType.setId(null);
        purchaseOrderType.setComment("Second Order.");
        OrderItemType orderItemType1 = purchaseOrderType.getOrderItemListType().getOrderItemType().remove(0);
        OrderItemType orderItemType2 = purchaseOrderType.getOrderItemListType().getOrderItemType().remove(0);

        given().
                contentType(ContentType.JSON).
                body(purchaseOrderType).
        when().
                post("/purchaseorderservice/addpurchaseorder").
        then().
                statusCode(200);

        // compare new PO with original.
        PurchaseOrderType poResponse = when().get("/purchaseorderservice/getpurchaseorder/2").as(PurchaseOrderType.class);
        purchaseOrderType.setId(2L);

        ObjectMapper objectMapper = new ObjectMapper();
        
        assertEquals(
                "Compare JSON response with original", 
                objectMapper.writeValueAsString(purchaseOrderType), 
                objectMapper.writeValueAsString(poResponse)
            );
        
        // do an update
        poResponse.setComment("Second Order (Modified).");
        orderItemType2.setId(null);
        poResponse.getOrderItemListType().getOrderItemType().add(orderItemType2);
        orderItemType1.setId(null);
        poResponse.getOrderItemListType().getOrderItemType().add(orderItemType1);

        given().
                contentType(ContentType.JSON).
                body(poResponse).
        when().
                put("/purchaseorderservice/updatepurchaseorder").
        then().
                statusCode(200);

        // compare response with original.
        PurchaseOrderType poResponse2 = when().get("/purchaseorderservice/getpurchaseorder/2").as(PurchaseOrderType.class);
        orderItemType2.setId(3L);
        orderItemType1.setId(4L);
        assertEquals(
                "Compare JSON response with original", 
                objectMapper.writeValueAsString(poResponse), 
                objectMapper.writeValueAsString(poResponse2)
            );
  }  
*/
