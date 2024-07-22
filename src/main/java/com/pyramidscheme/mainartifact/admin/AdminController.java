package com.pyramidscheme.mainartifact.admin;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admins")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping
    public List<AdminModel> getAllAdmins() {
        return adminService.getAllAdmins();
    }

    @GetMapping("/{id}")
    public AdminModel getAdminById(@PathVariable Long id) {
        return adminService.getAdminById(id);
    }

    @PostMapping
    public AdminModel createAdmin(@RequestBody AdminModel adminDetails) {
        return adminService.saveAdmin(adminDetails);
    }

    @PutMapping("/{id}")
    public AdminModel updateAdmin(@PathVariable Long id, @RequestBody AdminModel adminDetails) {
        return adminService.updateAdmin(id, adminDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        adminService.deleteAdmin(id);
    }
}
