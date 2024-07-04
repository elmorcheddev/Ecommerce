package com.ecommerce.library.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ecommerce.library.dto.ProductDto;
import com.ecommerce.library.model.Category;
import com.ecommerce.library.model.MarqueProd;
import com.ecommerce.library.model.Product;

@Service
public interface ProductService {
	/*admin*/
	///
List<ProductDto> findAll(); 
ProductDto findById(Long id_prod);
Product addProduct( MultipartFile imageprod , ProductDto productDto);
Product updateProduct(ProductDto productDto, MultipartFile multipartFile);
void deleteProduct(Long id_prod);
void enableByid(Long id_prod);

///
/*   User  */
///
List<Product> allproductactive();
Product getproductbyid(Long id_prod);
List<Product>allproductBycategory(Long id_cat);
List<Product> getAllProductBrand(Long id_marq);
}
