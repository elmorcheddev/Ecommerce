package com.ecommerce.library.service;

import java.util.List;

import com.ecommerce.library.dto.AdminDto;
import com.ecommerce.library.model.Admin;

public interface Adminservice {

    Admin findByUsername(String username);

    Admin save(AdminDto adminDto);
    
}
