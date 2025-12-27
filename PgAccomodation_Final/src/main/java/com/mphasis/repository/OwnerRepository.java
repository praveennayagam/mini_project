package com.mphasis.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.mphasis.model.Owner;

public interface OwnerRepository extends JpaRepository<Owner, Long> {}
