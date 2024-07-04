package com.ecommerce.user.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ecommerce.library.dto.CustomUserDto;
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
public class AuthenController {

@Autowired
private BCryptPasswordEncoder passwordEncoder;
	@Autowired
	private ProductService productService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private MarqueService marqueService;
	@Autowired
	private UserService userService;
	@GetMapping("/register")
	public String registerForm(Model model, Principal principal,CustomUserDto userDto, HttpSession session)
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
		model.addAttribute("title","Register");
		model.addAttribute("prodbrand",prodbrand);
		model.addAttribute("userDto", new CustomUserDto())	;

		return"register";
	}
	@GetMapping("/login")
	public String loginForm(Model model, Principal principal,CustomUserDto userDto, HttpSession session)
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
		model.addAttribute("prodbrand",prodbrand);
		model.addAttribute("title","Login");

		return"login";
	}
	
	@PostMapping("/register-process")
	public String registerProcess(@Valid @ModelAttribute("userDto") CustomUserDto userDto,
			BindingResult result, Model model ) {

		try {
			if(result.hasErrors())
			{
				model.addAttribute("userDto",userDto);
				return "register";
			}
			CustomUser user=userService.findByEmail(userDto.getEmail());
			if(user != null) {
				   model.addAttribute("emailError","Email already exist !");
				   model.addAttribute("userDto",userDto);
				   return "register";
			}
			if(userDto.getPassword().equals(userDto.getRepeatpassword())) {
                userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
                userService.saveUser(userDto);
				 model.addAttribute("userDto",userDto);
                model.addAttribute("success","Register with success !");
			}else {
				model.addAttribute("passwordError","password are not the same ! ");
				model.addAttribute("userDto",userDto);

				return "register";
			}

		} 
		catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("serverError"," server failed !");
				model.addAttribute("userDto",userDto);

		}
		return "register";
		
	}
	@GetMapping("/my-account")
	public String myaccount(Model model, Principal principal,CustomUserDto userDto, HttpSession session) {
		if(principal != null) {
			session.setAttribute("email", principal.getName());
			
					
		}else {
			session.removeAttribute("email");
		}
		try {
			CustomUser customUser=userService.findByEmail(principal.getName());
			ShoppingCart shoppingCart=customUser.getShoppingCart();
			model.addAttribute("customUser",customUser);

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
		model.addAttribute("prodbrand",prodbrand);
		model.addAttribute("title","My Account");
		return "my-account";
	}
	@PostMapping(value = "/completReg" )
	public String completReg(@ModelAttribute("customUser") CustomUser customUser,Model  model, Principal princiapl,RedirectAttributes attributes)
	{
		if(princiapl == null)
		{
			return "redirect:/login";
		}
		CustomUser customUserSave= userService.saveCustomUser(customUser);
		attributes.addFlashAttribute("customUser",customUserSave);
		return "redirect:/my-account";
	}
	 
}
