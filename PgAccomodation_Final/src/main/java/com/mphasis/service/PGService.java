package com.mphasis.service;



import java.util.List;
import com.mphasis.model.PG;
import com.mphasis.model.Owner;

public interface PGService {
    List<PG> getAccommodationsByCity(String city);
    List<PG> getAccommodationsByCityId(Long cityId);
    List<PG> getAccommodationsByLocality(String locality);
    PG getAccommodationById(long id);
    Owner getOwnerByPGId(long pgId);
}
