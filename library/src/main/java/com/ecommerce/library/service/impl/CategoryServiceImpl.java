package com.ecommerce.library.service.impl;

import com.ecommerce.library.model.Category;
import com.ecommerce.library.repository.CategoryRepository;
import com.ecommerce.library.service.CategoryService;
import com.ecommerce.library.utils.ImageUpload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ImageUpload imageUpload;
    
    @Override
    public List<Category> findAllCategory() {
        return categoryRepository.findAll();
    }

    @Override
    public Category saveCategory(Category category) {
		Category categorysave = new Category(category.getName());	;
    	return categoryRepository.save(categorysave);
    }
    @Override
    public Category getByIdCategory(Long id_cat) {

        return categoryRepository.findById(id_cat).get();
    }

    @Override
    public Category updateCategory(Category category) {
    	Category categoryupdate =null;
    	try {
    		categoryupdate=categoryRepository.findById(category.getId_cat()).get();
    		categoryupdate.setName(category.getName());
    		categoryupdate.setIs_active(category.getIs_active());
    		categoryupdate.setIs_delete(category.getIs_active());
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
		return categoryRepository.save(categoryupdate);

    }

    @Override
    public void deleteCategory(Long id_cat) {
        Category categoryDelete = categoryRepository.getById(id_cat);
        categoryDelete.setIs_delete(true);
        categoryDelete.setIs_active(false);
        categoryRepository.save(categoryDelete);
    }

    @Override
    public void enableCategory(Long id_cat) {
        Category categoryEnable = categoryRepository.getById(id_cat);
        categoryEnable.setIs_active(true);
        categoryEnable.setIs_delete(false);
        categoryRepository.save(categoryEnable);
    }

	@Override
	public List<Category> getAllCategoriesActivated() {
		// TODO Auto-generated method stub
		return categoryRepository.getAllCategoriesActivated();
	}
}
