package com.mphasis.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.mphasis.model.PG;
import com.mphasis.model.Owner;
import com.mphasis.repository.PGRepository;

@Service
@Transactional
public class PGServiceImpl implements PGService {

    private final PGRepository pgRepository;

    public PGServiceImpl(PGRepository pgRepository) {
        this.pgRepository = pgRepository;
    }

    @Override
    public List<PG> getAccommodationsByCity(String city) {
        // ✅ use AvailableTrue instead of IsAvailableTrue
        return pgRepository.findByCityAndAvailableTrue(city);
    }

    @Override
    public List<PG> getAccommodationsByCityId(Long cityId) {
        return pgRepository.findByCityIdAndAvailableTrue(cityId);
    }

    @Override
    public List<PG> getAccommodationsByLocality(String locality) {
        return pgRepository.findByLocalityAndAvailableTrue(locality);
    }

    @Override
    public PG getAccommodationById(long id) {
        return pgRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("PG not found: " + id));
    }

    @Override
    public Owner getOwnerByPGId(long pgId) {
        PG pg = pgRepository.findById(pgId)
                .orElseThrow(() -> new RuntimeException("PG not found: " + pgId));
        return pg.getOwner();
    }
}
