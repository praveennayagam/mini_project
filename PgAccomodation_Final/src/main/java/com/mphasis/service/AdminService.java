package com.mphasis.service;



import java.util.List;
import com.mphasis.model.Admin;
import com.mphasis.model.PG;

public interface AdminService {
    Admin createAdmin(Admin admin);
    Admin getByUsername(String username);
    List<PG> getAllPGs();
    void deletePG(Long pgId);
    PG setPGAvailability(Long pgId, boolean available);
}
