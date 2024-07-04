package com.ecommerce.library.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ecommerce.library.model.MarqueProd;
import com.ecommerce.library.repository.MarqueRepository;
import com.ecommerce.library.service.MarqueService;
import com.ecommerce.library.utils.ImageUpload;
@Service
public class MarqueServiceImpl implements MarqueService {

	
	@Autowired
	private MarqueRepository marqueRepository;
	@Autowired
	private ImageUpload imageUpload;
	@Override
	public List<MarqueProd> findAllMarqueProds() {
		return marqueRepository.findAll();
	}

	@Override
	public MarqueProd saveMarqueProd(MultipartFile imagemarq,MarqueProd marqueProd) {
		
		try {
			if(imagemarq == null) {
				marqueProd.setImage(null);
			}else {
				if(imageUpload.imageUpload(imagemarq)) 
				{
					System.out.println("uploded Logo  Brand ");
				}
				marqueProd.setImage(java.util.Base64.getEncoder().encodeToString(imagemarq.getBytes()));
				}
			marqueProd.setName_marque(marqueProd.getName_marque());
			marqueProd.setIsactive(true);
			marqueProd.setIsdelete(false);
			return marqueRepository.save(marqueProd);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
}       
	}

	@Override
	public MarqueProd getByIdMarqueProd(Long id_marq) {
		
		return marqueRepository.findById(id_marq).get();
	}

	@Override
	public MarqueProd updateMarqueProd(MarqueProd marqueProd) {
     MarqueProd prodUpdate =null;
try {
    prodUpdate = marqueRepository.findById(marqueProd.getId_marq()).get();
    prodUpdate.setName_marque(marqueProd.getName_marque());
    prodUpdate.setIsactive(marqueProd.isIsactive());
    prodUpdate.setIsdelete(marqueProd.isIsdelete());
	
} catch (Exception e) {
	e.printStackTrace();
}
		return marqueRepository.save(prodUpdate);
	}

	@Override
	public void deleteMarque(Long id_marq) {
	   MarqueProd marqueProd= marqueRepository.getById(id_marq);
	   marqueProd.setIsactive(false);
	   marqueProd.setIsdelete(true);
	   marqueRepository.save(marqueProd);
		
	}

	@Override
	public void enableMarqu(Long id_marq) {
		 MarqueProd marqueProd= marqueRepository.getById(id_marq);
		   marqueProd.setIsactive(true);
		   marqueProd.setIsdelete(false);
		   marqueRepository.save(marqueProd);		
	}

	@Override
	public List<MarqueProd> getAllMarqueProdsActivated() {

		return marqueRepository.getAllMarqueProdsActivated();
	}

}
