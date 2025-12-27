package com.mphasis.repository;



import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.mphasis.model.PG;

public interface PGRepository extends JpaRepository<PG, Long> {
    List<PG> findByCityAndAvailableTrue(String city);
    List<PG> findByCityIdAndAvailableTrue(Long cityId);
    List<PG> findByLocalityAndAvailableTrue(String locality);
    List<PG> findByOwner_OwnerId(Long ownerId);
}
