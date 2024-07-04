package com.ecommerce.admin.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ecommerce.library.dto.ProductDto;
import com.ecommerce.library.model.Category;
import com.ecommerce.library.model.MarqueProd;
import com.ecommerce.library.service.CategoryService;
import com.ecommerce.library.service.MarqueService;
import com.ecommerce.library.service.ProductService;

@Controller
public class ProductController {

	@Autowired
	private ProductService productService;
	@Autowired
	private CategoryService  categoryService;
	@Autowired
	private MarqueService marqueService;
	@GetMapping("/product")
	public String listProductPage(Model model ,Principal principal )
	{
		 if (principal == null) {
	            return "redirect:/login";
	        }
		List<ProductDto> listProducts=productService.findAll();

		model.addAttribute("products", listProducts);
		model.addAttribute("size", listProducts.size());
		model.addAttribute("title","Product List");


		return "product";
	}


	@GetMapping("/addproduct")
	public String addproductForm(Model  model , Principal principal)
	{
		 if (principal == null) {
	            return "redirect:/login";
	        }
		List<Category> categories = categoryService.getAllCategoriesActivated();
		List<MarqueProd>marqueProds = marqueService.getAllMarqueProdsActivated();
		model.addAttribute("categories",categories);
		model.addAttribute("marqueProds",marqueProds);

		model.addAttribute("newprod", new ProductDto());
		model.addAttribute("title","Add new Product");
		return "addproduct";
	}
	@PostMapping("/saveprod")
	public String addProduct(@ModelAttribute("newprod") ProductDto productDto,
									@RequestParam("imgprod") MultipartFile imageprod, RedirectAttributes redirectAttributes										) {

	try {
		productService.addProduct(imageprod, productDto);
		redirectAttributes.addFlashAttribute("success","product add with success");

	} catch (Exception e) {
    e.printStackTrace();
    redirectAttributes.addFlashAttribute("failed","failed to add product");
}
		return "redirect:/product";
	}
@GetMapping("/update_prod/{id_prod}")
public String updateProdForm(@PathVariable Long id_prod, Model model , Principal principal)
{
	if(principal ==null) {
		return "redirect:/login";
	}
	List<Category>categories=categoryService.findAllCategory();
	List<MarqueProd>marqueProds = marqueService.findAllMarqueProds();

	ProductDto productDto= productService.findById(id_prod);
	model.addAttribute("title","Update Product");
	model.addAttribute("marqueProds",marqueProds);

	model.addAttribute("categories",categories);
	model.addAttribute("prod",productDto);
	return "update_prod";
}
@PostMapping("/produpdate/{id_prod}")
public String updateProdAction(ProductDto productDto,
					RedirectAttributes redirectAttributes, @RequestParam("imgprod")   MultipartFile multipartFile)
{
	try {
		productService.updateProduct( productDto, multipartFile);
		redirectAttributes.addFlashAttribute("success"," Your product updated");
	} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("failed","product are not updated");
	}
	return "redirect:/product";
}

@RequestMapping(value="/enable-prod/{id_prod}" , method = { RequestMethod.PUT,RequestMethod.GET})
public String enableProduct(@PathVariable("id_prod")Long id_prod ,RedirectAttributes redirectAttributes)
{
	try {
		productService.enableByid(id_prod);
		redirectAttributes.addFlashAttribute("success","Product Enabled");

	} catch (Exception e) {
     e.printStackTrace();
     redirectAttributes.addFlashAttribute("failed","failed to enable product ");
	}
	return "redirect:/product";
}
@RequestMapping(value="/delete-prod/{id_prod}" , method = { RequestMethod.PUT,RequestMethod.GET})
public String deleteProduct(@PathVariable("id_prod")Long id_prod ,RedirectAttributes redirectAttributes)
{
	try {
		productService.deleteProduct(id_prod);
		redirectAttributes.addFlashAttribute("success","Product Disabled ");

	} catch (Exception e) {
e.printStackTrace();
redirectAttributes.addFlashAttribute("failed","failed to Disable product ");
	}
	return "redirect:/product";
}

}
