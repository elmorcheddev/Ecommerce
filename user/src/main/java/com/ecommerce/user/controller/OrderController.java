package com.ecommerce.user.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ecommerce.library.dto.CustomUserDto;
import com.ecommerce.library.model.Category;
import com.ecommerce.library.model.CustomUser;
import com.ecommerce.library.model.MarqueProd;
import com.ecommerce.library.model.Order;
import com.ecommerce.library.model.OrderDetails;
import com.ecommerce.library.model.Product;
import com.ecommerce.library.model.ShoppingCart;
import com.ecommerce.library.service.CategoryService;
import com.ecommerce.library.service.MarqueService;
import com.ecommerce.library.service.OrderService;
import com.ecommerce.library.service.ProductService;
import com.ecommerce.library.service.ShoppingCartService;
import com.ecommerce.library.service.UserService;

@Controller							
public class OrderController {
	@Autowired
	private ProductService productService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private MarqueService marqueService;
	@Autowired
	private ShoppingCartService cartService; 
	@Autowired
	private UserService userService;
	@Autowired
	private OrderService orderService;
	
	
	@GetMapping("/checkout")

	public String getOrder(Model model, Principal principal,CustomUserDto userDto, HttpSession session)
	{
		List<Product> productDtos=productService.allproductactive();
		List<Category> categoryactive=categoryService.getAllCategoriesActivated();
		List<MarqueProd>prodbrand=marqueService.getAllMarqueProdsActivated();
		model.addAttribute("catactive",categoryactive);
		model.addAttribute("productDtos",productDtos);
		model.addAttribute("prodbrand",prodbrand);
		model.addAttribute("title","Cart item");
		if(principal != null) {
			session.setAttribute("email", principal.getName());
			
		}else {
			session.removeAttribute("email");
			return "redirect:/index";
		}
		
		
					String email=principal.getName();
					CustomUser customUser=userService.findByEmail(email);
					model.addAttribute("customUser",customUser);
					
					ShoppingCart shoppingCart= customUser.getShoppingCart();
					double totalPriceCart =shoppingCart.getTotalPrice();
					model.addAttribute("totalPriceCart",totalPriceCart);
					model.addAttribute("shoppingCart",shoppingCart);
					if(customUser.getDatenaissance() == null && customUser.getAdresse()== null) {
						model.addAttribute("error","You have to complet your information before checkout");
						return"redirect:/my-account";
					}	
					
				
		return "checkout";
	}
	@GetMapping("/order")
	public String order(Model  model , Principal principal)
	{
		if(principal == null)
		{
			return "redirect:/login";
		}else {
			String email=principal.getName();
			CustomUser  customUser=userService.findByEmail(email);
			List<Order>details=customUser.getOrders();
			model.addAttribute("details",details);
		}
		
		return "order";
	}
	@GetMapping("/saveOrder")
	public String saveOrder(Model model , Principal principal)
	{
		if(principal == null)
		{
			return "redirect:/login";
		}else
		{
			String email=principal.getName();
			CustomUser customUser=userService.findByEmail(email);
			ShoppingCart shoppingCart=customUser.getShoppingCart();
			orderService.saveOrder(shoppingCart);
			return "redirect:/order";
		}
		
	}

}
