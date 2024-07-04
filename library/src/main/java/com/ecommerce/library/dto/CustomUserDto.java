package com.ecommerce.library.dto;

import java.sql.Date;

import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomUserDto {
	
    @Size(min = 3, max= 100, message = "invalid Name use minimal 3 character")
	private String 				firstname;
    @Size(min = 3, max= 100, message = "invalid Name use minimal 3 character")
	private String				lastname;
	private String				email;
	@Size(min = 8 , max= 100,  message = "invalid password use minimal 8 character")
	private String				password;
	private String 				repeatpassword;
	private String adresse;
	@Size(min = 8 ,  message = "invalid password use minimal 8 character")
	private String phone;
}
