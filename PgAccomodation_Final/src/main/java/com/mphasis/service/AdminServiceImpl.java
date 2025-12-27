package com.mphasis.service;



import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.mphasis.model.Admin;
import com.mphasis.model.PG;
import com.mphasis.repository.AdminRepository;
import com.mphasis.repository.PGRepository;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;
    private final PGRepository pgRepository;

    public AdminServiceImpl(AdminRepository adminRepository, PGRepository pgRepository) {
        this.adminRepository = adminRepository;
        this.pgRepository = pgRepository;
    }

    @Override
    public Admin createAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    @Override
    public Admin getByUsername(String username) {
        return adminRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Admin not found: " + username));
    }

    @Override
    public List<PG> getAllPGs() {
        return pgRepository.findAll();
    }

    @Override
    public void deletePG(Long pgId) {
        PG pg = pgRepository.findById(pgId)
                .orElseThrow(() -> new RuntimeException("PG not found: " + pgId));
        pgRepository.delete(pg);
    }

    @Override
    public PG setPGAvailability(Long pgId, boolean available) {
        PG pg = pgRepository.findById(pgId)
                .orElseThrow(() -> new RuntimeException("PG not found: " + pgId));
        pg.setAvailable(available);
        return pgRepository.save(pg);
    }
}
