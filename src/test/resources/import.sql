INSERT INTO PRODUCT (SKU, NAME, DESCRIPTION, CATEGORY) VALUES ('111-AA', 'Widget', 'Cool Widget', 'Widget');
INSERT INTO PRODUCT (SKU, NAME, DESCRIPTION, CATEGORY) VALUES ('112-AB', 'Widget-2', 'Really Cool Widget', 'Widget');
INSERT INTO PRODUCT (SKU, NAME, DESCRIPTION, CATEGORY) VALUES ('000-OO', 'Spam', 'Classic Canned Meat Gluten Free. Fully cooked, ready to eat--cold or hot.', 'Food');
INSERT INTO PRODUCT (SKU, NAME, DESCRIPTION, CATEGORY) VALUES ('123-SF', 'Apple iPad Air 2', 'Apple iPad Air 2 tablet, featuring a thin Retina display and antireflective coating.', 'Electronics');
INSERT INTO PURCHASE_ORDER (ID, COMMENT, ORDER_DATE) VALUES (1, 'First Order, Yes!', '2017-03-31');
INSERT INTO ORDER_ITEM (PURCHASE_ORDER_ID, order_item_list_order, PRODUCT_ID, QUANTITY, ITEM_PRICE) VALUES (1, 0, 2, 1, 2.99);
INSERT INTO ORDER_ITEM (PURCHASE_ORDER_ID, order_item_list_order, PRODUCT_ID, QUANTITY, ITEM_PRICE) VALUES (1, 1, 1, 1, 1.99);