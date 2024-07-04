package com.ecommerce.library.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ecommerce.library.model.MarqueProd;

public interface MarqueService {

	    List<MarqueProd> findAllMarqueProds();

	    MarqueProd saveMarqueProd(MultipartFile imagemarq, MarqueProd marqueProd);

	    MarqueProd getByIdMarqueProd(Long id_marq);

	    MarqueProd updateMarqueProd(MarqueProd marqueProd);

	    void deleteMarque(Long id_marq);

	    void enableMarqu(Long id_marq);
	    List<MarqueProd>getAllMarqueProdsActivated();
}
