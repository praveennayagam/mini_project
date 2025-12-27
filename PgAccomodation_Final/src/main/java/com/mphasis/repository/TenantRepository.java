package com.mphasis.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.mphasis.model.Tenant;

public interface TenantRepository extends JpaRepository<Tenant, Long> {}
