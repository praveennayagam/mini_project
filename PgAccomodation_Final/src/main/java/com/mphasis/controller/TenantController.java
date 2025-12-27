package com.mphasis.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.mphasis.model.Tenant;
import com.mphasis.service.TenantService;

@RestController
@RequestMapping("/tenant")
public class TenantController {

    private final TenantService tenantService;

    public TenantController(TenantService tenantService) {
        this.tenantService = tenantService;
    }

    @PostMapping("/{pgId}")
    public ResponseEntity<String> bookPG(@PathVariable Long pgId, @RequestBody Tenant tenant) {
        try {
            return ResponseEntity.ok(tenantService.bookPG(pgId, tenant));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
