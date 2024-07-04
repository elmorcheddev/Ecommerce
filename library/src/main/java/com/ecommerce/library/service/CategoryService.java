package com.ecommerce.library.service;

import com.ecommerce.library.model.Category;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface CategoryService {

    List<Category> findAllCategory();

    Category saveCategory(Category category);

    Category getByIdCategory(Long id_cat);

    Category updateCategory(Category category);

    void deleteCategory(Long id_cat);

    void enableCategory(Long id_cat);
    
    List<Category>getAllCategoriesActivated();	
}
