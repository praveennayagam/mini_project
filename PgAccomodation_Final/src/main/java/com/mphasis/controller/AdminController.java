package com.mphasis.controller;


import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.mphasis.model.Admin;
import com.mphasis.model.PG;
import com.mphasis.service.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/create")
    public ResponseEntity<Admin> createAdmin(@RequestBody Admin admin) {
        return ResponseEntity.ok(adminService.createAdmin(admin));
    }

    @GetMapping("/{username}")
    public ResponseEntity<Admin> getAdmin(@PathVariable String username) {
        return ResponseEntity.ok(adminService.getByUsername(username));
    }

    @GetMapping("/pgs")
    public ResponseEntity<List<PG>> getAllPGs() {
        return ResponseEntity.ok(adminService.getAllPGs());
    }

    @DeleteMapping("/pgs/{pgId}")
    public ResponseEntity<String> deletePG(@PathVariable Long pgId) {
        adminService.deletePG(pgId);
        return ResponseEntity.ok("PG deleted by admin");
    }

    @PatchMapping("/pgs/{pgId}/availability")
    public ResponseEntity<PG> setAvailability(@PathVariable Long pgId, @RequestParam boolean available) {
        return ResponseEntity.ok(adminService.setPGAvailability(pgId, available));
    }
}
