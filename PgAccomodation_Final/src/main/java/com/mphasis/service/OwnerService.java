package com.mphasis.service;



import java.util.List;
import com.mphasis.model.Owner;
import com.mphasis.model.PG;

public interface OwnerService {
    Owner addOwner(Owner owner);
    PG addPG(Long ownerId, PG pg);
    List<PG> getPGsByOwner(Long ownerId);
    PG setAvailability(Long pgId, boolean available);
    PG editPGDetails(Long ownerId, PG pgUpdate);
    void deletePG(Long pgId);
}
