package ibmjmstest.service;

import ibmjmstest.types.PurchaseOrderListType;
import ibmjmstest.types.PurchaseOrderType;

public interface PurchaseOrderService {

	/**
	 * REST endpoint for getPurchaseOrderList
	 * /rest/purchaseorderservice/getpurchaseorderlist
	 * @return {@link PurchaseOrderListType}
	 */
	PurchaseOrderListType getPurchaseOrderList() throws Exception;

	/**
	 * REST endpoint for getPurchaseOrder
	 * /rest/purchaseorderservice/getpurchaseorder/{id}
	 * @return {@link PurchaseOrderType}
	 */
	PurchaseOrderType getPurchaseOrder(Long id) throws Exception;

	/**
	 * REST endpoint for addPurchaseOrder
	 * /rest/purchaseorderservice/addpurchaseorder
	 * @return {@link String}
	 */
	String createPurchaseOrder(PurchaseOrderType purchaseOrderType) throws Exception;

	/**
	 * REST endpoint for updatepurchaseorder
	 * /rest/purchaseorderservice/updatepurchaseorder
	 * @return {@link String}
	 */
	String updatePurchaseOrder(PurchaseOrderType purchaseOrderType) throws Exception;

}