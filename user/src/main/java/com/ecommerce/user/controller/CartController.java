package com.ecommerce.user.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ecommerce.library.dto.CustomUserDto;
import com.ecommerce.library.model.Category;
import com.ecommerce.library.model.CustomUser;
import com.ecommerce.library.model.MarqueProd;
import com.ecommerce.library.model.Product;
import com.ecommerce.library.model.ShoppingCart;
import com.ecommerce.library.service.CategoryService;
import com.ecommerce.library.service.MarqueService;
import com.ecommerce.library.service.ProductService;
import com.ecommerce.library.service.ShoppingCartService;
import com.ecommerce.library.service.UserService;

import net.bytebuddy.implementation.bytecode.constant.DefaultValue;

@Controller
public class CartController {

@Autowired
private UserService userService;
	@Autowired
	private ProductService productService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private MarqueService marqueService;
	@Autowired
	private ShoppingCartService cartService; 
	@GetMapping("/cart")
	public String cartForm(Model model, Principal principal,CustomUserDto userDto, HttpSession session)
	{ 	
		if(principal != null) {
			session.setAttribute("email", principal.getName());
			
					
		}else {
			session.removeAttribute("email");
			return"redirect:/login";
		}
		try {
			String email=principal.getName();
			CustomUser customUser=userService.findByEmail(email);
			ShoppingCart shoppingCart= customUser.getShoppingCart();
			if(shoppingCart!=null)
			{
				double totalPriceCart =shoppingCart.getTotalPrice();
				model.addAttribute("totalPriceCart",totalPriceCart);
				model.addAttribute("shoppingCart",shoppingCart);

			}else {
				model.addAttribute("check","no item in your shopping cart !!");
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		List<Product> productDtos=productService.allproductactive();
		List<Category> categoryactive=categoryService.getAllCategoriesActivated();
		List<MarqueProd>prodbrand=marqueService.getAllMarqueProdsActivated();
		model.addAttribute("catactive",categoryactive);
		model.addAttribute("productDtos",productDtos);
		model.addAttribute("prodbrand",prodbrand);
		model.addAttribute("title","Cart item");
		
					
						return "cart";
	}
	
	@PostMapping("/addtocart")
	public String addToCart(@RequestParam("id_prod") Long id_prod,
							@RequestParam(value="quantity", required=false , defaultValue = "1" )int quantity,
							HttpServletRequest request ,
							Model model,
							Principal principal ) 
	{
		if(principal == null) 
		{
			return "login";
		}
		
		Product product =productService.getproductbyid(id_prod);
		
		String email=principal.getName();
		CustomUser customUser=userService.findByEmail(email);
		ShoppingCart cart = cartService.addItemToCart(product, customUser, quantity);
		return "redirect:" +request.getHeader("Referer");
		
	}
	@RequestMapping(value = "/updateCart", params = "action=update", method = RequestMethod.POST)
	public String updateCartItem(@RequestParam("quantity") int quantity, @RequestParam("id_prod") Long id_prod, Principal principal, Model model)
	{
		if(principal == null)
			{
			return"redirect:/login";
			}
		Product product=productService.getproductbyid(id_prod);
		String email=principal.getName();
		CustomUser  customUser=userService.findByEmail(email);
		ShoppingCart shoppingCart=cartService.updateItemCart(product, customUser, quantity);
		model.addAttribute("shoppingCart",shoppingCart);
		
		return "redirect:/cart";
			}
	@RequestMapping(value = "updateCart", params = "action=delete", method = RequestMethod.POST)
	public String DeleteCartItem(@RequestParam("id_prod") Long id_prod , Model model , Principal principal)
	{
		if(principal == null ) {
			return "redirect:/login";
		}
		Product product =productService.getproductbyid(id_prod);
		CustomUser customUser=userService.findByEmail(principal.getName());
			ShoppingCart shoppingCart=cartService.deleteItemCart(product, customUser);
			model.addAttribute("shoppingCart",shoppingCart);
			return "redirect:/cart";

	}

}
