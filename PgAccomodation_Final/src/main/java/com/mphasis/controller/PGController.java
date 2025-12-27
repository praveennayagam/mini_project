package com.mphasis.controller;



import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.mphasis.model.PG;
import com.mphasis.model.Owner;
import com.mphasis.service.PGService;

@RestController
@RequestMapping("/pg")
public class PGController {

    private final PGService pgService;

    public PGController(PGService pgService) {
        this.pgService = pgService;
    }

    // Query by city name
    @GetMapping("/city/{city}")
    public ResponseEntity<List<PG>> getPGsByCity(@PathVariable String city) {
        List<PG> pgList = pgService.getAccommodationsByCity(city);
        return pgList.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(pgList);
    }

    // Query by city id (since cityId is a simple numeric field)
    @GetMapping("/city-id/{cityId}")
    public ResponseEntity<List<PG>> getPGsByCityId(@PathVariable Long cityId) {
        List<PG> pgList = pgService.getAccommodationsByCityId(cityId);
        return pgList.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(pgList);
    }

    // Query by locality
    @GetMapping("/locality/{locality}")
    public ResponseEntity<List<PG>> getPGsByLocality(@PathVariable String locality) {
        List<PG> pgList = pgService.getAccommodationsByLocality(locality);
        return pgList.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(pgList);
    }

    // Details by PG id
    @GetMapping("/details/{id}")
    public ResponseEntity<PG> getPGDetails(@PathVariable long id) {
        return ResponseEntity.ok(pgService.getAccommodationById(id));
    }

    // Owner details by PG id
    @GetMapping("/owner/{id}")
    public ResponseEntity<Owner> getOwnerByPGId(@PathVariable long id) {
        return ResponseEntity.ok(pgService.getOwnerByPGId(id));
    }
}
