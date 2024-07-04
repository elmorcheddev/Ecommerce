package com.ecommerce.library.service;

import com.ecommerce.library.model.CustomUser;
import com.ecommerce.library.model.Product;
import com.ecommerce.library.model.ShoppingCart;

public interface ShoppingCartService {

	ShoppingCart addItemToCart(Product product, CustomUser customUser, int quantity);
	ShoppingCart updateItemCart(Product product, CustomUser customUser, int quantity);
	ShoppingCart deleteItemCart(Product product, CustomUser customUser);

}
