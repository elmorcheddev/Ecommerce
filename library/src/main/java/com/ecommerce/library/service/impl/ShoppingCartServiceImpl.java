package com.ecommerce.library.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.library.model.CartItem;
import com.ecommerce.library.model.CustomUser;
import com.ecommerce.library.model.Product;
import com.ecommerce.library.model.ShoppingCart;
import com.ecommerce.library.repository.CartitemRepository;
import com.ecommerce.library.repository.ShoppingCartRepository;
import com.ecommerce.library.service.ShoppingCartService;
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

	@Autowired
	private CartitemRepository cartitemRepository;
	@Autowired
	private ShoppingCartRepository shoppingCartRepository;
	
	@Override
	public ShoppingCart addItemToCart(Product product, CustomUser customUser, int quantity) {
		ShoppingCart cart=customUser.getShoppingCart();
		if(cart == null) {
			cart = new ShoppingCart();
		}
		
		Set<CartItem>cartItems=cart.getCartItem();
		CartItem cartItem=findCartItem(cartItems, product.getId_prod());
		
		if(cartItems == null) {
			cartItems= new HashSet<>();
				if(cartItem == null) {
				cartItem = new CartItem();
				cartItem.setProduct(product);
				cartItem.setTotalPrice(quantity * product.getCastPrice_prod());
				cartItem.setQuantity(quantity);
				cartItem.setCart(cart);
				cartItems.add(cartItem);
				cartitemRepository.save(cartItem);
				
				}
		}else {
						if(cartItem == null) {
							cartItem = new CartItem();
							cartItem.setProduct(product);
							cartItem.setTotalPrice(quantity * product.getCastPrice_prod());
							cartItem.setQuantity(quantity);
							cartItem.setCart(cart);
							cartItems.add(cartItem);
							cartitemRepository.save(cartItem);
							
				}else {
					cartItem.setQuantity(cartItem.getQuantity() + quantity);
					cartItem.setTotalPrice(cartItem.getTotalPrice() +(quantity * product.getCastPrice_prod()) );
					cartitemRepository.save(cartItem);
				}
	}
				cart.setCartItem(cartItems);
				
				int totalItem=totalItem(cart.getCartItem());
				double totalPrice=totalPrice(cart.getCartItem());
				
				cart.setTotalPrice(totalPrice);
				cart.setTotlaitem(totalItem);
				cart.setUser(customUser);
				
				return shoppingCartRepository.save(cart);
		}

	private CartItem findCartItem(Set<CartItem>items , Long id_prod) {
		if(items == null) {
			return null;
		}
		CartItem cartItem =null;
				for(CartItem item : items) {
					if(item.getProduct().getId_prod() == id_prod)
					{
						cartItem=item;
					}
				}
				return cartItem;
	}
	private int totalItem(Set<CartItem> cartItems) {
		int totalItem = 0;
		for(CartItem item :  cartItems) {
			totalItem+=item.getQuantity();
		}
		return totalItem;
	}
	private double totalPrice(Set<CartItem>cartItems) {
		double totalPrice = 0;
		for(CartItem item : cartItems) {
			totalPrice+=item.getTotalPrice();
		}
		return totalPrice;
	}

	@Override
	public ShoppingCart updateItemCart(Product product, CustomUser customUser, int quantity) {
		ShoppingCart shoppingCart = customUser.getShoppingCart();
		Set<CartItem>cartItems=shoppingCart.getCartItem();
		CartItem cartItem=findCartItem(cartItems, product.getId_prod());
		cartItem.setQuantity(quantity);
		cartItem.setTotalPrice(quantity * product.getCastPrice_prod());
		cartitemRepository.save( cartItem);

		int totalItem=totalItem(shoppingCart.getCartItem());
		double totalPrice=totalPrice(shoppingCart.getCartItem());
		
		shoppingCart.setTotalPrice(totalPrice);
		shoppingCart.setTotlaitem(totalItem);
		
		return shoppingCartRepository.save(shoppingCart);
		
	}

	@Override
	public ShoppingCart deleteItemCart(Product product, CustomUser customUser) {
		ShoppingCart shoppingCart = customUser.getShoppingCart();
		Set<CartItem>cartItems=shoppingCart.getCartItem();
		CartItem cartItem=findCartItem(cartItems, product.getId_prod());
		cartItems.remove(cartItem);
		cartitemRepository.delete( cartItem);

		int totalItem=totalItem(cartItems);
		double totalPrice=totalPrice(cartItems);
		shoppingCart.setCartItem(cartItems);
		shoppingCart.setTotalPrice(totalPrice);
		shoppingCart.setTotlaitem(totalItem);
		
		return shoppingCartRepository.save(shoppingCart);
	}

}
