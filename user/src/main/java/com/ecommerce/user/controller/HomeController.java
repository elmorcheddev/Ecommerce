package com.ecommerce.user.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ecommerce.library.dto.CustomUserDto;
import com.ecommerce.library.dto.ProductDto;
import com.ecommerce.library.model.Category;
import com.ecommerce.library.model.CustomUser;
import com.ecommerce.library.model.MarqueProd;
import com.ecommerce.library.model.Product;
import com.ecommerce.library.model.ShoppingCart;
import com.ecommerce.library.service.CategoryService;
import com.ecommerce.library.service.MarqueService;
import com.ecommerce.library.service.ProductService;
import com.ecommerce.library.service.UserService;

@Controller
public class HomeController {

	@Autowired
	private UserService userService;
	@Autowired
	private ProductService productService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private MarqueService marqueService;
@RequestMapping(value = {"/index","/"} ,method = RequestMethod.GET )
	public String indexPage(Model model, Principal principal,CustomUserDto userDto, HttpSession session)
	{ 	
		if(principal != null) {
			session.setAttribute("email", principal.getName());
			
					
		}else {
			session.removeAttribute("email");
		}try {
			CustomUser customUser=userService.findByEmail(principal.getName());
			ShoppingCart shoppingCart=customUser.getShoppingCart();
			if(shoppingCart != null) {
				session.setAttribute("totalitems", shoppingCart.getTotlaitem());

			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		List<Product> productDtos=productService.allproductactive();
		List<Category> categoryactive=categoryService.getAllCategoriesActivated();
		List<MarqueProd>prodbrand=marqueService.getAllMarqueProdsActivated();

		model.addAttribute("catactive",categoryactive);
		model.addAttribute("productDtos",productDtos);
		model.addAttribute("title","Home");
		model.addAttribute("prodbrand",prodbrand);
		return "index";
	}
	@GetMapping("/about-us")
	public String aboutus(Model model, Principal principal,CustomUserDto userDto, HttpSession session)
	{ 	
		if(principal != null) {
			session.setAttribute("email", principal.getName());
			
					
		}else {
			session.removeAttribute("email");
		}
		try {
			CustomUser customUser=userService.findByEmail(principal.getName());
			ShoppingCart shoppingCart=customUser.getShoppingCart();
			if(shoppingCart != null) {
				session.setAttribute("totalitems", shoppingCart.getTotlaitem());

			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("title","About-Us");

List<Category> categoryactive=categoryService.getAllCategoriesActivated();
model.addAttribute("catactive",categoryactive);
List<MarqueProd>prodbrand=marqueService.findAllMarqueProds();
model.addAttribute("prodbrand",prodbrand);
		return "about-us";
	}
	@GetMapping("/contact-us")
	public String contactus(Model model, Principal principal,CustomUserDto userDto, HttpSession session)
	{ 	
		if(principal != null) {
			session.setAttribute("email", principal.getName());
			
					
		}else {
			session.removeAttribute("email");
		}try {
			CustomUser customUser=userService.findByEmail(principal.getName());
			ShoppingCart shoppingCart=customUser.getShoppingCart();
			if(shoppingCart != null) {
				session.setAttribute("totalitems", shoppingCart.getTotlaitem());

			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("title","Contzct-Us");

List<Category> categoryactive=categoryService.getAllCategoriesActivated();
model.addAttribute("catactive",categoryactive);
List<MarqueProd>prodbrand=marqueService.getAllMarqueProdsActivated();
model.addAttribute("prodbrand",prodbrand);
		return "contact-us";
	}
	@GetMapping("/blog")
	public String blog(Model model, Principal principal,CustomUserDto userDto, HttpSession session)
	{ 	
		if(principal != null) {
			session.setAttribute("email", principal.getName());
			
					
		}else {
			session.removeAttribute("email");
		}try {
			CustomUser customUser=userService.findByEmail(principal.getName());
			ShoppingCart shoppingCart=customUser.getShoppingCart();
			if(shoppingCart != null) {
				session.setAttribute("totalitems", shoppingCart.getTotlaitem());

			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("title","Blog");

List<Category> categoryactive=categoryService.getAllCategoriesActivated();
model.addAttribute("catactive",categoryactive);
List<MarqueProd>prodbrand=marqueService.getAllMarqueProdsActivated();
model.addAttribute("prodbrand",prodbrand);
	return "blog";	
	}
	@GetMapping("/product")
	public String shop(Model model, Principal principal,CustomUserDto userDto, HttpSession session)
	{ 	
		if(principal != null) {
			session.setAttribute("email", principal.getName());
			
					
		}else {
			session.removeAttribute("email");
		}try {
			CustomUser customUser=userService.findByEmail(principal.getName());
			ShoppingCart shoppingCart=customUser.getShoppingCart();
			if(shoppingCart != null) {
				session.setAttribute("totalitems", shoppingCart.getTotlaitem());

			}
		}catch(Exception e) {
			e.printStackTrace();
		}

List<Category> categoryactive=categoryService.getAllCategoriesActivated();
model.addAttribute("catactive",categoryactive);
		List<Product> productDtos=productService.allproductactive();
		List<MarqueProd>marqueProds=marqueService.findAllMarqueProds();
				model.addAttribute("marqueProds",marqueProds);
				model.addAttribute("productDtos",productDtos);
		model.addAttribute("title","Shop");
		List<MarqueProd>prodbrand=marqueService.getAllMarqueProdsActivated();
		model.addAttribute("prodbrand",prodbrand);
		return "product";
	}
	@GetMapping("/productlbybrand")
	public String shoplist(Model model, Principal principal,CustomUserDto userDto, HttpSession session)
	{ 	
		if(principal != null) {
			session.setAttribute("email", principal.getName());
			
					
		}else {
			session.removeAttribute("email");
		}
		try {
			CustomUser customUser=userService.findByEmail(principal.getName());
			ShoppingCart shoppingCart=customUser.getShoppingCart();
			if(shoppingCart != null) {
				session.setAttribute("totalitems", shoppingCart.getTotlaitem());

			}
		}catch(Exception e) {
			e.printStackTrace();
		}

List<Category> categoryactive=categoryService.getAllCategoriesActivated();
model.addAttribute("catactive",categoryactive);
		model.addAttribute("title","Shop");
		List<MarqueProd>prodbrand=marqueService.getAllMarqueProdsActivated();
		model.addAttribute("prodbrand",prodbrand);
		return "productlbybrand";
	}
	@GetMapping("/product-details/{id_prod}")
	public String productdetails(@PathVariable("id_prod")Long id_prod,Model model, Principal principal,CustomUserDto userDto, HttpSession session)
	{ 	
		if(principal != null) {
			session.setAttribute("email", principal.getName());
			
					
		}else {
			session.removeAttribute("email");
		}try {
			CustomUser customUser=userService.findByEmail(principal.getName());
			ShoppingCart shoppingCart=customUser.getShoppingCart();
			if(shoppingCart != null) {
				session.setAttribute("totalitems", shoppingCart.getTotlaitem());

			}
		}catch(Exception e) {
			e.printStackTrace();
		}

List<Category> categoryactive=categoryService.getAllCategoriesActivated();
model.addAttribute("catactive",categoryactive);
		Product detailprod=productService.getproductbyid(id_prod);
		model.addAttribute("detailprod",detailprod);
		model.addAttribute("title","Shop");
		List<MarqueProd>prodbrand=marqueService.getAllMarqueProdsActivated();
		model.addAttribute("prodbrand",prodbrand);

		return "product-details";
	}
	@GetMapping("/bycategory/{id_cat}")
	public String bycategory(@PathVariable("id_cat") Long id_cat , Model model, Principal principal,CustomUserDto userDto, HttpSession session)
	{ 	
		if(principal != null) {
			session.setAttribute("email", principal.getName());
			
					
		}else {
			session.removeAttribute("email");
		}try {
			CustomUser customUser=userService.findByEmail(principal.getName());
			ShoppingCart shoppingCart=customUser.getShoppingCart();
			if(shoppingCart != null) {
				session.setAttribute("totalitems", shoppingCart.getTotlaitem());

			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		List<MarqueProd>prodbrand=marqueService.getAllMarqueProdsActivated();
		model.addAttribute("prodbrand",prodbrand);

List<Category> categoryactive=categoryService.getAllCategoriesActivated();
model.addAttribute("catactive",categoryactive);
		List<Product> productbycategory=productService.allproductBycategory(id_cat);
		model.addAttribute("title","Product by Category");
		model.addAttribute("prodcat",productbycategory);
	return "bycategory";
	}
	@GetMapping("/bybrand/{id_marq}")
	public String bybrand(@PathVariable("id_marq") Long id_marq,Model model, Principal principal,CustomUserDto userDto, HttpSession session)
	{ 	
		if(principal != null) {
			session.setAttribute("email", principal.getName());
			
					
		}else {
			session.removeAttribute("email");
		}try {
			CustomUser customUser=userService.findByEmail(principal.getName());
			ShoppingCart shoppingCart=customUser.getShoppingCart();
			if(shoppingCart != null) {
				session.setAttribute("totalitems", shoppingCart.getTotlaitem());

			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		List<MarqueProd>prodbrand=marqueService.getAllMarqueProdsActivated();
		model.addAttribute("prodbrand",prodbrand);
        List<Category> categoryactive=categoryService.getAllCategoriesActivated();
        model.addAttribute("catactive",categoryactive);
		List<Product> brands=productService.getAllProductBrand(id_marq);
		model.addAttribute("brands",brands);
		model.addAttribute("title","Product by Brand");

	return "bybrand";
	}
}
