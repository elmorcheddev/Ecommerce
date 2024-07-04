package com.ecommerce.library.utils;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class ImageUpload {
private final String UPLOAD_FOLDER="E:\\spring boot application\\ecommerce\\admin\\src\\main\\resources\\static\\img\\product image";

public boolean imageUpload(MultipartFile imageprod)
{
	boolean isupload=false;
	try {
		Files.copy(imageprod.getInputStream(), 
				Paths.get(UPLOAD_FOLDER + File.separator ,
						imageprod.getOriginalFilename()),
				StandardCopyOption.REPLACE_EXISTING);
		isupload=true;
	} catch (Exception e) {
e.printStackTrace();	
}
	return isupload;
}
public boolean checkImaeExist(MultipartFile multipartFile) {
	boolean isexist=false;
try {
	File file = new File(UPLOAD_FOLDER + "\\" + multipartFile.getOriginalFilename());
	isexist = file.exists();
} catch (Exception e) {
     e.printStackTrace();}
return isexist;
}
}
