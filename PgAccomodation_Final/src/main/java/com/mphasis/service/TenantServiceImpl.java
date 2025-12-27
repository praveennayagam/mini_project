package com.mphasis.service;



import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.mphasis.model.PG;
import com.mphasis.model.Tenant;
import com.mphasis.repository.PGRepository;
import com.mphasis.repository.TenantRepository;

@Service
@Transactional
public class TenantServiceImpl implements TenantService {

    private final PGRepository pgRepository;
    private final TenantRepository tenantRepository;

    public TenantServiceImpl(PGRepository pgRepository, TenantRepository tenantRepository) {
        this.pgRepository = pgRepository;
        this.tenantRepository = tenantRepository;
    }

    @Override
    public String bookPG(Long pgId, Tenant tenant) {
        PG pg = pgRepository.findById(pgId)
                .orElseThrow(() -> new RuntimeException("PG not found: " + pgId));

        if (!pg.isAvailable() || pg.getAvailableRooms() <= 0) {
            throw new RuntimeException("PG not available or no rooms left");
        }

        tenant.setPg(pg);
        tenantRepository.save(tenant);

        pg.setAvailableRooms(pg.getAvailableRooms() - 1);
        if (pg.getAvailableRooms() == 0) {
            pg.setAvailable(false);
        }
        pg.setPopularityCount(pg.getPopularityCount() + 1);
        pgRepository.save(pg);

        return "PG booked successfully for tenant: " + tenant.getName();
    }
}
