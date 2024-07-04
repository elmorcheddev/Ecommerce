package com.ecommerce.admin.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ecommerce.library.model.Category;
import com.ecommerce.library.service.CategoryService;

@Controller
public class CategoryController {
    @Autowired
    private CategoryService categoryService;


    @GetMapping("/category")
    public String categoryPage(Model model, Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        }
        List<Category> categoryList = categoryService.findAllCategory();
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("size", categoryList.size());
        model.addAttribute("title", "Category");
    	model.addAttribute("newCat",new Category());

        return "category";
    }

    @PostMapping("/newcategory")
    public String addCategory(@ModelAttribute("newCat")Category category,
    		RedirectAttributes redirectAttributes) {
    	
        try {
            categoryService.saveCategory(category);
            redirectAttributes.addFlashAttribute("success", " add with success");
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute("failed", "Failed to add because duplicate name");

        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("failed", " error server");
        }
        return "redirect:/category";
    }

    @GetMapping("/category/edit/{id_cat}")
	public String editCategoryForm(@PathVariable Long id_cat, Model model) {
    	Category category= categoryService.getByIdCategory(id_cat);
    	model.addAttribute("title","Update Category");
    	model.addAttribute("category", category);

		return "updatecat";
	}

    @PostMapping("/update/{id_cat}")
    public String updateCategory(Category category , RedirectAttributes redirectAttributes)
    {
    	try {
			categoryService.updateCategory(category);
			redirectAttributes.addFlashAttribute("success","update with successfly");
		}
    	catch (DataIntegrityViolationException e)
    	{
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("failed","deplicate name !!");
		}
    	catch(Exception e)
    	{
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("failed","error server");
		}
    	return "redirect:/category";
    }

  @GetMapping("/delete/{id_cat}")
    public String deletecategory(@PathVariable Long id_cat,RedirectAttributes redirectAttributes) {
    	try {
    		categoryService.deleteCategory(id_cat);
    		redirectAttributes.addFlashAttribute("success" , "deleted !!");


		} catch (Exception e) {
				e.printStackTrace();
				redirectAttributes.addFlashAttribute("failed","deleted !");
		}
    	return"redirect:/category";

		}
  @GetMapping("/enable/{id_cat}")
  public String enablecategory(@PathVariable Long id_cat,RedirectAttributes redirectAttributes) {
  	try {
  		categoryService.enableCategory(id_cat);
  		redirectAttributes.addFlashAttribute("success","enabled !!");


		} catch (Exception e) {
				e.printStackTrace();
				redirectAttributes.addFlashAttribute("failed","not enabled !");
		}
  	return"redirect:/category";

		}
    }




