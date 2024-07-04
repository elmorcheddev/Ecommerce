package com.ecommerce.admin.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.ecommerce.library.dto.AdminDto;
import com.ecommerce.library.dto.ProductDto;
import com.ecommerce.library.model.Admin;
import com.ecommerce.library.model.Category;
import com.ecommerce.library.model.MarqueProd;
import com.ecommerce.library.service.Adminservice;
import com.ecommerce.library.service.CategoryService;
import com.ecommerce.library.service.MarqueService;
import com.ecommerce.library.service.ProductService;

@Controller
public class loginController {
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private Adminservice adminservice;
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private MarqueService marqueService;
    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("title", "Login");
        return "login";
    }

    @GetMapping("/forgot-password")
    public String forgotPasswordForm(Model model) {
        model.addAttribute("title", " forgot password");
        return "forgot-password";
    }

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("title", "Register");

        model.addAttribute("adminDto", new AdminDto());
        return "register";
    }

    @GetMapping("/index")
    public String index(Model model, String username) {
        model.addAttribute("title", "index - dashboard admin ");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "redirect:/login";
        }
        List<MarqueProd>marqueProds=marqueService.findAllMarqueProds();
        List<ProductDto> products = productService.findAll();
        List<Category> categories=categoryService.findAllCategory();
        Admin admin=adminservice.findByUsername(username);
        model.addAttribute("admin",admin);
        model.addAttribute("sizeM",marqueProds.size());
        model.addAttribute("size",products.size());
        model.addAttribute("sizeCat",categories.size());
        return "index";

    }

    @PostMapping("register-new")
    public String addNewAdmin(@Valid @ModelAttribute("adminDto") AdminDto adminDto
            , BindingResult result, Model model, HttpSession session) {

        try {
            session.removeAttribute("message");
            if (result.hasErrors()) {
                model.addAttribute("adminDto", adminDto);
                result.toString();
                return "register";

            }
            String username = adminDto.getUsername();
            Admin admin = adminservice.findByUsername(username);
            if (admin != null) {
                model.addAttribute("adminDto", adminDto);
                model.addAttribute("emailError", " your email already exist");
                return "register";
            }
            if (adminDto.getPassword().equals(adminDto.getRepeatpassword())) {
                adminDto.setPassword(passwordEncoder.encode(adminDto.getPassword()));
                adminservice.save(adminDto);
                model.addAttribute("success", " you are register with success");
                model.addAttribute("adminDto", adminDto);
            } else {
                model.addAttribute("adminDto", adminDto);
                model.addAttribute("passwordError", "te password are not the same");
                return "register";
            }


        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("serverError", "there is error on the server please check again");
        }
        return "register";

    }
}