package com.mphasis.controller;



import com.mphasis.model.Owner;
import com.mphasis.model.PG;
import com.mphasis.service.OwnerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/owner")
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @PostMapping("/add")
    public ResponseEntity<Owner> addOwner(@RequestBody Owner owner) {
        return ResponseEntity.ok(ownerService.addOwner(owner));
    }

    // Add PG for owner: pass ownerId as query param
    @PostMapping("/places/add")
    public ResponseEntity<PG> addPG(@RequestParam Long ownerId, @RequestBody PG pg) {
        return ResponseEntity.ok(ownerService.addPG(ownerId, pg));
    }

    // List PGs by owner
    @GetMapping("/places")
    public ResponseEntity<List<PG>> getPGsByOwner(@RequestParam Long ownerId) {
        return ResponseEntity.ok(ownerService.getPGsByOwner(ownerId));
    }

    // Set availability
    @PatchMapping("/places/{id}")
    public ResponseEntity<PG> setAvailability(@PathVariable Long id, @RequestParam boolean available) {
        return ResponseEntity.ok(ownerService.setAvailability(id, available));
    }

    // Edit PG details (ownerId as query param to authorize)
    @PutMapping("/places/edit")
    public ResponseEntity<PG> editPGDetails(@RequestParam Long ownerId, @RequestBody PG pg) {
        return ResponseEntity.ok(ownerService.editPGDetails(ownerId, pg));
    }

    // Delete PG
    @DeleteMapping("/places/delete/{pgId}")
    public ResponseEntity<String> deletePG(@PathVariable Long pgId) {
        ownerService.deletePG(pgId);
        return ResponseEntity.ok("PG deleted successfully");
    }
}
