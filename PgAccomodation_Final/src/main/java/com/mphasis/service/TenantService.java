package com.mphasis.service;



import com.mphasis.model.Tenant;

public interface TenantService {
    String bookPG(Long pgId, Tenant tenant);
}
