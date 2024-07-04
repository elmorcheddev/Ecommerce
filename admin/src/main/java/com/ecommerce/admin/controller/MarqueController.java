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

import com.ecommerce.library.model.MarqueProd;
import com.ecommerce.library.service.MarqueService;

@Controller
public class MarqueController {
    @Autowired
    private MarqueService marqueService;

    @GetMapping("/marque")
    public String marquePage(Model model, Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        }
        List<MarqueProd> marqueProds = marqueService.findAllMarqueProds();
        model.addAttribute("marqueProds", marqueProds);
        model.addAttribute("size", marqueProds.size());
        model.addAttribute("title", "Marque");
    	model.addAttribute("brand", new MarqueProd());

        return "marque";
    }


    @PostMapping("/newmarque")
    public String addCategory(@ModelAttribute("brand")
                              MarqueProd marqueProd,@RequestParam("logo")MultipartFile logo,
                              RedirectAttributes redirectAttributes)
    {
    	
        try {
          marqueService.saveMarqueProd(logo,marqueProd);
          redirectAttributes.addFlashAttribute("success", " add with success");
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute("failed", "Failed to add because duplicate name");

            redirectAttributes.addAttribute("failed", "Failed to add because duplicate name");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("failed", " error server");
        }
        return "redirect:/marque";
    }

    @GetMapping("/marque/editmarque/{id_marq}")
	public String editCategoryForm(@PathVariable Long id_marq, Model model) {
    	model.addAttribute("title","Update Marque Product");
    	model.addAttribute("marque", marqueService.getByIdMarqueProd(id_marq));

		return "updatemarq";
	}

    @PostMapping("/update-marq")
    public String updateCategory(MarqueProd marqueProd , RedirectAttributes redirectAttributes)
    {
    	try {
			marqueService.updateMarqueProd(marqueProd);
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
    	return "redirect:/marque";
    }

  @GetMapping("/deletemarque/{id_marq}")
    public String deletecategory(@PathVariable Long id_marq,RedirectAttributes redirectAttributes) {
    	try {
marqueService.deleteMarque(id_marq);
redirectAttributes.addFlashAttribute("success" , "deleted !!");


		} catch (Exception e) {
				e.printStackTrace();
				redirectAttributes.addFlashAttribute("failed","deleted !");
		}
    	return"redirect:/marque";

		}
  @GetMapping("/enablemarq/{id_marq}")
  public String enablecategory(@PathVariable Long id_marq,RedirectAttributes redirectAttributes) {
  	try {
  		marqueService.enableMarqu(id_marq);
  		redirectAttributes.addFlashAttribute("success","enabled !!");


		} catch (Exception e) {
				e.printStackTrace();
				redirectAttributes.addFlashAttribute("failed","not enabled !");
		}
  	return"redirect:/marque";

		}
    }




