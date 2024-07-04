package com.ecommerce.library.service.impl;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ecommerce.library.dto.ProductDto;
import com.ecommerce.library.model.MarqueProd;
import com.ecommerce.library.model.Product;
import com.ecommerce.library.repository.ProductRepository;
import com.ecommerce.library.service.ProductService;
import com.ecommerce.library.utils.ImageUpload;
import com.nimbusds.jose.util.Base64;
@Service
public class ProductServiceImpl implements ProductService {



	@Autowired
	private ProductRepository productRepository;
@Autowired
private ImageUpload imageUpload;
	@Override
	public List<ProductDto> findAll() {
		List<ProductDto> productlist=new ArrayList<>();
		List< Product> products= productRepository.findAll();
		for(Product product: products) {
			ProductDto productDto= new ProductDto();
			productDto.setId_prod(product.getId_prod());
			productDto.setName_prod(product.getName_prod());
			productDto.setCastPrice_prod(product.getCastPrice_prod());
			productDto.setSalePrice_prod(product.getSalePrice_prod());
			productDto.setDescription_prod(product.getDescription_prod());
			productDto.setQuantity(product.getQuantity());
			productDto.setCategory(product.getCategory());
			productDto.setMarqueProd(product.getMarqueProd());
			productDto.setImage(product.getImage());
			productDto.set_activate(product.is_activate());
			productDto.set_delete(product.is_delete());
			productlist.add(productDto);
		}
		return productlist;
	}

	@Override
	public ProductDto findById(Long id_prod) {
		
	Product product = productRepository.getById(id_prod);
	ProductDto productDto = new ProductDto();
	productDto.setId_prod(product.getId_prod());
	productDto.setName_prod(product.getName_prod());
	productDto.setCastPrice_prod(product.getCastPrice_prod());
	productDto.setSalePrice_prod(product.getSalePrice_prod());
	productDto.setDescription_prod(product.getDescription_prod());
	productDto.setQuantity(product.getQuantity());
	productDto.setCategory(product.getCategory());
	productDto.setMarqueProd(product.getMarqueProd());
	productDto.setImage(product.getImage());
	productDto.set_activate(product.is_activate());
	productDto.set_delete(product.is_delete());
	return productDto;
	}
	
	
	
	
	
	@Override
	public Product addProduct(MultipartFile imageprod , ProductDto productDto) {
		Product product= new Product();
      try {

				    if(imageprod==null) {
				    	product.setImage(null);
				    }else {
						if(imageUpload.imageUpload(imageprod)) {
							System.out.println("uploded image ");
						}
						product.setImage(java.util.Base64.getEncoder().encodeToString(imageprod.getBytes()));
	}
						product.setName_prod(productDto.getName_prod());
						product.setCastPrice_prod(productDto.getCastPrice_prod());
						product.setDescription_prod(productDto.getDescription_prod());
						product.setQuantity(productDto.getQuantity());
						product.setCategory(productDto.getCategory());
						product.setMarqueProd(productDto.getMarqueProd());
						product.set_activate(true);
						product.set_delete(false);
						return productRepository.save(product);
    } catch (Exception e) {
						e.printStackTrace();
						return null ;}
}

	@Override
	public Product updateProduct( ProductDto productDto,MultipartFile multipartFile) {
try {
	Product product=productRepository.getById(productDto.getId_prod());
	if(multipartFile== null) {
		product.setImage(product.getImage());
	}else {
		if(imageUpload.checkImaeExist(multipartFile)==false) {
		imageUpload.imageUpload(multipartFile);
	}
			product.setImage(java.util.Base64.getEncoder().encodeToString(multipartFile.getBytes()));
		
}
	product.setName_prod(productDto.getName_prod());
	product.setCastPrice_prod(productDto.getCastPrice_prod());
	product.setDescription_prod(productDto.getDescription_prod());
	product.setQuantity(productDto.getQuantity());
	product.setCategory(productDto.getCategory());
	product.setMarqueProd(productDto.getMarqueProd());
	
	return productRepository.save(product);
}
catch (Exception e) {
e.printStackTrace();
return null;
}
		
	}

	@Override
	public void deleteProduct(Long id_prod) {
     Product product = productRepository.getById(id_prod);
     product.set_activate(false);
     product.set_delete(true);
      productRepository.save(product);
	}

	@Override
	public void enableByid(Long id_prod) {
	    Product product = productRepository.getById(id_prod);
	     product.set_activate(true);
	     product.set_delete(false);
	      productRepository.save(product);		
	}
/*user*/
	@Override
	public List<Product> allproductactive() {
		// TODO Auto-generated method stub
		return productRepository.allproductactive();
	}

	@Override
	public Product getproductbyid(Long id_prod) {
		// TODO Auto-generated method stub
		return productRepository.getById(id_prod);
	}

	@Override
	public List<Product> allproductBycategory(Long id_cat) {
		// TODO Auto-generated method stub
		return productRepository.allproductBycategory(id_cat);
	}

	@Override
	public List<Product> getAllProductBrand(Long id_marq) {
		// TODO Auto-generated method stub
		return productRepository.allproductByBrand(id_marq);
	}



}
