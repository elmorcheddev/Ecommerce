package com.ecommerce.library.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.library.model.CartItem;
import com.ecommerce.library.model.Order;
import com.ecommerce.library.model.OrderDetails;
import com.ecommerce.library.model.ShoppingCart;
import com.ecommerce.library.repository.CartitemRepository;
import com.ecommerce.library.repository.OrderRepository;
import com.ecommerce.library.repository.OrederDetailsRepository;
import com.ecommerce.library.repository.ShoppingCartRepository;
import com.ecommerce.library.service.OrderService;
@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private OrederDetailsRepository detailsRepository;
	@Autowired
	private ShoppingCartRepository cartRepository;
	@Autowired
	private CartitemRepository cartitemRepository;
	@Override
	public void saveOrder(ShoppingCart shoppingCart) {
		Order order = new Order();
		order.setOrderStatus("PINDING");
		order.setOrderDate(new Date());
		order.setTotalPrice(shoppingCart.getTotalPrice());
		order.setUser(shoppingCart.getUser());
		List<OrderDetails> orderDetailsList=new ArrayList<>();
		
		for(CartItem cartItem : shoppingCart.getCartItem())
		{
			OrderDetails details=new OrderDetails();
			details.setOrder(order);
			details.setProduct(cartItem.getProduct());
			details.setQuantity(cartItem.getQuantity());
			details.setUnitPrice(cartItem.getProduct().getCastPrice_prod());
			details.setTotalPrice(shoppingCart.getTotalPrice());
			detailsRepository.save(details);
			orderDetailsList.add(details);
			cartitemRepository.delete(cartItem);
			
		}
		order.setOrderDetailList(orderDetailsList);
		shoppingCart.setCartItem(new HashSet<>());
		shoppingCart.setTotlaitem(0);
		shoppingCart.setTotalPrice(0);
		cartRepository.save(shoppingCart);
		orderRepository.save(order);
	}

}
