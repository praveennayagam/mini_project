package com.mphasis.service;



import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.mphasis.model.Owner;
import com.mphasis.model.PG;
import com.mphasis.repository.OwnerRepository;
import com.mphasis.repository.PGRepository;

@Service
@Transactional
public class OwnerServiceImpl implements OwnerService {

    private final OwnerRepository ownerRepository;
    private final PGRepository pgRepository;

    public OwnerServiceImpl(OwnerRepository ownerRepository, PGRepository pgRepository) {
        this.ownerRepository = ownerRepository;
        this.pgRepository = pgRepository;
    }

    @Override
    public Owner addOwner(Owner owner) {
        return ownerRepository.save(owner);
    }

    @Override
    public PG addPG(Long ownerId, PG pg) {
        Owner owner = ownerRepository.findById(ownerId)
                .orElseThrow(() -> new RuntimeException("Owner not found: " + ownerId));
        pg.setOwner(owner);
        return pgRepository.save(pg);
    }

    @Override
    public List<PG> getPGsByOwner(Long ownerId) {
        return pgRepository.findByOwner_OwnerId(ownerId);
    }

    @Override
    public PG setAvailability(Long pgId, boolean available) {
        PG pg = pgRepository.findById(pgId)
                .orElseThrow(() -> new RuntimeException("PG not found: " + pgId));
        pg.setAvailable(available);
        return pgRepository.save(pg);
    }

    @Override
    public PG editPGDetails(Long ownerId, PG pgUpdate) {
        PG pg = pgRepository.findById(pgUpdate.getPgId())
                .orElseThrow(() -> new RuntimeException("PG not found: " + pgUpdate.getPgId()));

        if (pg.getOwner() == null || !pg.getOwner().getOwnerId().equals(ownerId)) {
            throw new RuntimeException("PG does not belong to owner: " + ownerId);
        }

        pg.setName(pgUpdate.getName());
        pg.setArea(pgUpdate.getArea());
        pg.setNumberOfRooms(pgUpdate.getNumberOfRooms());
        pg.setAvailableRooms(pgUpdate.getAvailableRooms());
        pg.setPopularityCount(pgUpdate.getPopularityCount());
        pg.setLocality(pgUpdate.getLocality());
        pg.setCity(pgUpdate.getCity());
        pg.setCityId(pgUpdate.getCityId());
        pg.setRentAmount(pgUpdate.getRentAmount());
        pg.setAvailable(pgUpdate.isAvailable());

        return pgRepository.save(pg);
    }

    @Override
    public void deletePG(Long pgId) {
        PG pg = pgRepository.findById(pgId)
                .orElseThrow(() -> new RuntimeException("PG not found: " + pgId));
        pgRepository.delete(pg);
    }
}
