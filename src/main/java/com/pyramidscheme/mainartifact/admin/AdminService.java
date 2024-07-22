package com.pyramidscheme.mainartifact.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;

    public List<AdminModel> getAllAdmins() {
        return adminRepository.findAll();
    }

    public AdminModel getAdminById(Long id) {
        return adminRepository.findById(id).orElse(null);
    }

    public AdminModel saveAdmin(AdminModel product) {
        return adminRepository.save(product);
    }

    public AdminModel updateAdmin(Long id, AdminModel adminModelDetails) {
        AdminModel adminModel = adminRepository.findById(id).orElse(null);
        if (adminModel != null) {
            adminModel.setName(adminModelDetails.getName());
            adminModel.setSurname(adminModelDetails.getSurname());
            return adminRepository.save(adminModel);
        }
        return null;
    }

    public void deleteAdmin(Long id) {
        adminRepository.deleteById(id);
    }
}
