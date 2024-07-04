package com.ecommerce.library.dto;

import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AdminDto {
    @Size(min = 3, max = 20, message = "invalid First Name use min 3 character and max 20")
    private String firstname;
    @Size(min = 3, max = 20, message = "invalide Last Name use min 3 character and max 20")
    private String lastname;

    private String username;
    @Size(min = 6, max = 50, message = "invalide Password use min 6 character and max 50")
    private String password;

    private String repeatpassword;


}
